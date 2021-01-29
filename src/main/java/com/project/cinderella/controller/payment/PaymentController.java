package com.project.cinderella.controller.payment;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.cinderella.common.MessageData;
import com.project.cinderella.exception.CartException;
import com.project.cinderella.exception.LoginRequiredException;
import com.project.cinderella.model.domain.Cart;
import com.project.cinderella.model.domain.Member;
import com.project.cinderella.model.domain.OrderSummary;
import com.project.cinderella.model.domain.Receiver;
import com.project.cinderella.model.member.service.MemberService;
import com.project.cinderella.model.payment.service.PaymentService;
import com.project.cinderella.model.product.service.TopCategoryService;

@Controller
public class PaymentController {
   private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

   @Autowired
   private PaymentService paymentService;

   @Autowired
   private TopCategoryService topCategoryService;
   
   @Autowired
   private MemberService memberService;

   // 장바구니에 상품 담기 요청
   @RequestMapping(value = "/shop/cart/regist", method = RequestMethod.POST)
   @ResponseBody
   public MessageData registCart(Cart cart, HttpSession session) {
      if (session.getAttribute("member") == null) {
         throw new LoginRequiredException("로그인이 필요한 서비스입니다.");
      }

      Member member = (Member) session.getAttribute("member");

      logger.debug("product_id " + cart.getProduct_id());
      logger.debug("quantity " + cart.getQuantity());
      cart.setMember_id(member.getMember_id());
      paymentService.insert(cart);

      // MessageConverter 에 의해 VO는 JSON형태로 응답되어질 수 있다!!
      MessageData messageData = new MessageData();
      messageData.setResultCode(1);
      messageData.setMsg("장바구니에 상품이 담겼습니다");
      messageData.setUrl("/cinderella/shop/cart/list");

      return messageData;
   }

   // 장바구니 목록 요청
   @RequestMapping(value = "/shop/cart/list", method = RequestMethod.GET)
   public ModelAndView getCartList(HttpServletRequest request, HttpSession session) {
      if (session.getAttribute("member") == null) {
         throw new LoginRequiredException("로그인이 필요한 서비스입니다.");
      }
      Member member = (Member) session.getAttribute("member");
      List topList = topCategoryService.selectAll();
      List cartList = paymentService.selectCartList(member.getMember_id());

      ModelAndView mav = new ModelAndView();
      mav.addObject("topList", topList);
      mav.addObject("cartList", cartList);
      mav.setViewName("shop/cart/cart_list");
      return mav;
   }

   // 장바구니 삭제 요청
   @RequestMapping(value = "/shop/cart/del", method = RequestMethod.GET)
   public String delCart(HttpSession session) {
      // 장바구니를 삭제하기 위해서는, 로그인한 회원만 가능..
      if (session.getAttribute("member") == null) {
         throw new LoginRequiredException("로그인이 필요한 서비스입니다");
      }
      paymentService.delete((Member) session.getAttribute("member"));

      return "redirect:/shop/cart/list";
   }

   // 장바구니 목록 수정
   @RequestMapping(value = "/shop/cart/edit", method = RequestMethod.POST)
   public ModelAndView editCart(@RequestParam("cart_id") int[] cartArray, @RequestParam("quantity") int[] qArray) {
      // 넘겨받은 파라미터 출력하기!! cart_id, quantity
      logger.debug("cartArray length " + cartArray.length);

      List cartList = new ArrayList();
      for (int i = 0; i < cartArray.length; i++) {
         Cart cart = new Cart();
         cart.setCart_id(cartArray[i]);
         cart.setQuantity(qArray[i]);
         cartList.add(cart);
      }
      paymentService.update(cartList);

      // 수정되었다는 메시지를 보고싶다면.. message.jsp로 응답하자
      MessageData messageData = new MessageData();
      messageData.setResultCode(1);
      messageData.setMsg("장바구니가 수정되었습니다");
      messageData.setUrl("/cinderella/shop/cart/list");
      ModelAndView mav = new ModelAndView();
      mav.addObject("messageData", messageData);
      mav.setViewName("shop/error/message");

      return mav;
   }

   // 체크아웃 페이지 요청
   @GetMapping("/shop/payment/form")
   public ModelAndView payForm(HttpServletRequest request, HttpSession session) {
      ModelAndView mav = new ModelAndView();
      List topList = topCategoryService.selectAll();
      mav.addObject("topList", topList); // ModelAndView에서의 Model만 사용..

      // 결제수단 가져오기
      List paymethodList = paymentService.selectPaymethodList();
      mav.addObject("paymethodList", paymethodList);

      // 장바구니 정보도 가져오기
      Member member = (Member) session.getAttribute("member");
      List cartList = paymentService.selectCartList(member.getMember_id());
      mav.addObject("cartList", cartList);
      mav.setViewName("shop/payment/checkout");
      return mav;
   }

   // 결제요청 처리
   @RequestMapping(value = "/shop/payment/regist", method = RequestMethod.POST, produces = "text/html;charset=utf8")
   @ResponseBody
   public String pay(HttpSession session, OrderSummary orderSummary, Receiver receiver) {
      
      logger.debug("d"+orderSummary.getOrder_summary_id());
      logger.debug("d"+orderSummary.getTotal_pay());
      logger.debug("d"+orderSummary.getTotal_price());
      logger.debug("d"+orderSummary.getPaymethod_id());
       
      Member member = (Member) session.getAttribute("member");
      orderSummary.setMember_id(member.getMember_id()); // 회원 pk

      paymentService.registOrder(orderSummary, receiver);
      memberService.updateBuyCount(member, member.getMember_id());
      
      
      paymentService.delete((Member) session.getAttribute("member"));
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      sb.append("\"result\":1,");
      sb.append("\"msg\":\"결제 성공\"");
      sb.append("}");
      return sb.toString();
   }
   
   //주문목록 확인 - 관리자
   @RequestMapping(value = "/admin/product/order_list", method = RequestMethod.GET)
   public ModelAndView getOrderList() {
      ModelAndView mav = new ModelAndView("admin/product/order_list");
      List orderList = paymentService.selectOrderList();
      List memberList = memberService.selectAll();
      List orderstateList = paymentService.selectOrderStateList();
      mav.addObject("orderList", orderList);
      mav.addObject("memberList", memberList);
      mav.addObject("orderstateList", orderstateList);
      return mav;
   }
   
   
   

   // 장바구니와 관련된 예외처리 핸들러
   @ExceptionHandler(CartException.class)
   @ResponseBody
   public MessageData handleException(CartException e) {
      logger.debug("핸들러 동작함 " + e.getMessage());
      MessageData messageData = new MessageData();
      messageData.setResultCode(0);
      messageData.setMsg(e.getMessage());
      messageData.setUrl("/cinderella/shop/member/signin");

      return messageData;
   }
   
   
   
}
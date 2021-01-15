package com.project.cinderella.controller.payment;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
import com.project.cinderella.model.payment.service.PaymentService;
import com.project.cinderella.model.product.service.TopCategoryService;

@Controller
public class PaymentController {
	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private TopCategoryService topCategoryService;

	//ฤซฦฎต๎ทฯ
	@RequestMapping(value = "/shop/cart/regist", method = RequestMethod.POST)
	@ResponseBody
	public MessageData registCart(Cart cart, HttpSession session) {
		if (session.getAttribute("member") == null) {
			throw new LoginRequiredException("๋ก๊ทธ?ธ?ด ??? ?๋น์ค???ค.");
		}

		Member member = (Member) session.getAttribute("member");

		logger.debug("product_id " + cart.getProduct_id());
		logger.debug("quantity " + cart.getQuantity());
		cart.setMember_id(member.getMember_id());
		paymentService.insert(cart);

		// MessageConverter ? ??ด VO? JSON??๋ก? ??ต??ด์ง? ? ??ค!!
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("?ฅ๋ฐ๊ตฌ?? ???ด ?ด๊ฒผ์ต??ค");
		messageData.setUrl("/cinderella/shop/cart/list");

		return messageData;
	}

	// ?ฅ๋ฐ๊ตฌ? ๋ชฉ๋ก ?์ฒ?
	@RequestMapping(value = "/shop/cart/list", method = RequestMethod.GET)
	public ModelAndView getCartList(HttpServletRequest request, HttpSession session) {
		if (session.getAttribute("member") == null) {
			throw new LoginRequiredException("๋ก๊ทธ?ธ?ด ??? ?๋น์ค???ค.");
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

	// ?ฅ๋ฐ๊ตฌ? ?ญ?  ?์ฒ?
	@RequestMapping(value = "/shop/cart/del", method = RequestMethod.GET)
	public String delCart(HttpSession session) {
		// ?ฅ๋ฐ๊ตฌ?๋ฅ? ?ญ? ?๊ธ? ??ด??, ๋ก๊ทธ?ธ? ??๋ง? ๊ฐ??ฅ..
		if (session.getAttribute("member") == null) {
			throw new LoginRequiredException("๋ก๊ทธ?ธ?ด ??? ?๋น์ค???ค");
		}
		paymentService.delete((Member) session.getAttribute("member"));

		return "redirect:/shop/cart/list";
	}

	// ?ฅ๋ฐ๊ตฌ? ๋ชฉ๋ก ?? 
	@RequestMapping(value = "/shop/cart/edit", method = RequestMethod.POST)
	public ModelAndView editCart(@RequestParam("cart_id") int[] cartArray, @RequestParam("quantity") int[] qArray) {
		// ?๊ฒจ๋ฐ?? ??ผ๋ฏธํฐ ์ถ๋ ฅ?๊ธ?!! cart_id, quantity
		logger.debug("cartArray length " + cartArray.length);

		List cartList = new ArrayList();
		for (int i = 0; i < cartArray.length; i++) {
			Cart cart = new Cart();
			cart.setCart_id(cartArray[i]);
			cart.setQuantity(qArray[i]);
			cartList.add(cart);
		}
		paymentService.update(cartList);

		// ?? ???ค? ๋ฉ์์ง?๋ฅ? ๋ณด๊ณ ?ถ?ค๋ฉ?.. message.jsp๋ก? ??ต??
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("?ฅ๋ฐ๊ตฌ?๊ฐ? ?? ???ต??ค");
		messageData.setUrl("/cinderella/shop/cart/list");
		ModelAndView mav = new ModelAndView();
		mav.addObject("messageData", messageData);
		mav.setViewName("shop/error/message");

		return mav;
	}

	// ์ฒดํฌ?? ??ด์ง? ?์ฒ?
	@GetMapping("/shop/payment/form")
	public String payForm(Model model, HttpSession session) {
		List topList = topCategoryService.selectAll();
		model.addAttribute("topList", topList); // ModelAndView??? Model๋ง? ?ฌ?ฉ..

		// ๊ฒฐ์ ??จ ๊ฐ?? ธ?ค๊ธ?
		List paymethodList = paymentService.selectPaymethodList();
		model.addAttribute("paymethodList", paymethodList);

		// ?ฅ๋ฐ๊ตฌ? ? ๋ณด๋ ๊ฐ?? ธ?ค๊ธ?
		Member member = (Member) session.getAttribute("member");
		List cartList = paymentService.selectCartList(member.getMember_id());
		model.addAttribute("cartList", cartList);

		return "shop/payment/checkout";
	}

	
	@PostMapping("/shop/payment/regist")
	public String pay(HttpSession session, OrderSummary orderSummary, Receiver receiver) {
		logger.debug("๋ฐ์ ?ฌ? ?ด๋ฆ? " + receiver.getReceiver_name());
		logger.debug("๋ฐ์ ?ฌ? ?ฐ?ฝ์ฒ? " + receiver.getReceiver_phone());
		logger.debug("๋ฐ์ ?ฌ? ์ฃผ์ " + receiver.getReceiver_addr());
		logger.debug("๊ฒฐ์ ๋ฐฉ๋ฒ?? " + orderSummary.getPaymethod_id());
		logger.debug("total_price " + orderSummary.getTotal_price());
		logger.debug("total_pay " + orderSummary.getTotal_pay());
		Member member = (Member) session.getAttribute("member");
		orderSummary.setMember_id(member.getMember_id()); // ?? pk

		paymentService.registOrder(orderSummary, receiver);

		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"result\":1,");
		sb.append("\"msg\":\"๊ฒฐ์  ?ฑ๊ณ?\"");
		sb.append("}");
		return sb.toString();
	}

	// ?ฅ๋ฐ๊ตฌ??? ๊ด?? จ? ??ธ์ฒ๋ฆฌ ?ธ?ค?ฌ
	@ExceptionHandler(CartException.class)
	@ResponseBody
	public MessageData handleException(CartException e) {
		logger.debug("?ธ?ค?ฌ ???จ " + e.getMessage());
		MessageData messageData = new MessageData();
		messageData.setResultCode(0);
		messageData.setMsg(e.getMessage());

		return messageData;
	}
}

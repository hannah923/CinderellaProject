package com.project.cinderella.controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.cinderella.common.MessageData;
import com.project.cinderella.exception.MailSendException;
import com.project.cinderella.exception.MemberNotFoundException;
import com.project.cinderella.exception.MemberRegistException;
import com.project.cinderella.model.domain.Member;
import com.project.cinderella.model.member.service.MemberService;
import com.project.cinderella.model.preview.service.PhotoReviewService;
import com.project.cinderella.model.product.service.ProductService;
import com.project.cinderella.model.product.service.TopCategoryService;

@Controller
public class MemberController {
	//private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private PhotoReviewService photoReviewService;

	@Autowired
	private TopCategoryService topCategoryService;

	@Autowired
	private ProductService productService;

	// 회원가입폼 요청
	@RequestMapping(value = "/shop/member/registForm", method = RequestMethod.GET)
	public ModelAndView getRegistForm() {
		List topList = topCategoryService.selectAll();
		ModelAndView mav = new ModelAndView("shop/member/signup");
		mav.addObject("topList", topList); // 담기
		return mav;
	}

	// 회원가입 요청 처리
	@RequestMapping(value = "/shop/member/regist", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String regist(Member member) {
		/*
		 * logger.debug("아이디 " + member.getUser_id()); logger.debug("이름 " +
		 * member.getName()); logger.debug("비번 " + member.getPassword());
		 * logger.debug("이메일id " + member.getEmail_id()); logger.debug("이메일server " +
		 * member.getEmail_server()); logger.debug("우편번호 " + member.getZipcode());
		 * logger.debug("주소 " + member.getAddr()); logger.debug("주문횟수 " +
		 * member.getBuy_count()); logger.debug("멤버십등급" + member.getMembership_id());
		 */

		memberService.regist(member);
//에러업구영 이거 왜자꾸뜨지 소식이 없네용...
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append(" \"result\":1, ");
		sb.append(" \"msg\":\"회원가입 성공\"");
		sb.append("}");

		return sb.toString();
}
	
	//회원정보 수정
	@RequestMapping(value = "/shop/member/edit", method = RequestMethod.POST)
	@ResponseBody
	public void editMemberInfo(Member member,HttpSession session) {
		System.out.println("수정컨트롤러 일시작");
		memberService.update(member);
		System.out.println("수정컨트롤러끝");
		
		session.invalidate();
	}
	
	//회원탈퇴
	@RequestMapping(value = "/shop/member/delete",method = RequestMethod.POST)
	public void deleteMember(Member member,HttpSession session) {
		memberService.delete(member);
		session.invalidate();
		
	}

	// 로그인화면 요청
	@RequestMapping(value = "/shop/member/loginForm", method = RequestMethod.GET)
	public ModelAndView getLoginForm() {
		List topList = topCategoryService.selectAll();
		ModelAndView mav = new ModelAndView("shop/member/signin");
		mav.addObject("topList", topList); // 담기

		return mav;
	}

	// 로그인 요청 처리
	@RequestMapping(value = "/shop/member/login", method = RequestMethod.POST)
	public String login(Member member, HttpServletRequest request) {
		// db에 존재여부 확인
		Member obj = memberService.select(member);
		System.out.println(obj);
		// 존재 O : 세션에 회원정보 담아두기

		HttpSession session = request.getSession();
		session.setAttribute("member", obj); // 현재 클라이언트 요청과 연계된 세션에 보관해 놓는다

		return "redirect:/";
	}

	// 로그아웃 요청 처리
	@RequestMapping(value = "/shop/member/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request) {
		request.getSession().invalidate(); // 세션 무효화, 이시점부터 담겨진 데이터가 다 무효가 된다
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("로그아웃 되었습니다");
		messageData.setUrl("/cinderella/");

		ModelAndView mav = new ModelAndView("shop/error/message");
		mav.addObject("messageData", messageData);
		return mav;
	}
	//쇼핑몰 사용자정보창
	@RequestMapping(value = "/shop/member/userinfo",method = RequestMethod.GET)
	public ModelAndView getMember() {
		List topList = topCategoryService.selectAll();
		ModelAndView mav = new ModelAndView();
		mav.addObject("topList", topList);
		mav.setViewName("/shop/member/userinfo");
		return mav;
	}
	//쇼핑몰 내가 올린 포토리뷰 보기
	@RequestMapping(value = "/shop/member/mylog",method = RequestMethod.GET)
	public ModelAndView getPhotoReviewList(String user_id) {
		List topList = topCategoryService.selectAll();
		ModelAndView mav = new ModelAndView();
		System.out.println("user_id:"+user_id);
		List photoReviewList = photoReviewService.selectForMylog(user_id);
		mav.addObject("photoReviewList",photoReviewList);
		mav.addObject("topList", topList);
		mav.setViewName("shop/member/mylog");
		return mav;
	}

	// 관리자모드에서 회원목록보기
	@RequestMapping(value = "/admin/member/member_list", method = RequestMethod.GET)
	public ModelAndView getProductList() {
		ModelAndView mav = new ModelAndView("admin/member/member_list");
		List memberList = memberService.selectAll();
		mav.addObject("memberList", memberList);
		return mav;
	}

	// 예외 핸들러 2가지 처리
	@ExceptionHandler(MemberRegistException.class)
	public ModelAndView handleException(MemberRegistException e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", e.getMessage()); // 사용자가 보게될 에러 메시지
		mav.setViewName("shop/error/result");
		return mav;
	}

	@ExceptionHandler(MailSendException.class)
	public ModelAndView handleException(MailSendException e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", e.getMessage()); // 사용자가 보게될 에러 메시지
		mav.setViewName("shop/error/result");
		return mav;
	}

	@ExceptionHandler(MemberNotFoundException.class)
	public ModelAndView handleException(MemberNotFoundException e) {

		ModelAndView mav = new ModelAndView();

		List topList = topCategoryService.selectAll();
		mav.addObject("topList", topList); // 담기
		mav.addObject("msg", e.getMessage());
		mav.setViewName("shop/error/result");
		return mav;
	}

}
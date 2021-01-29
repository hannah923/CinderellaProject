package com.project.cinderella.controller.preview;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.project.cinderella.common.FileManager;
import com.project.cinderella.model.domain.Comments;
import com.project.cinderella.model.domain.Member;
import com.project.cinderella.model.domain.PhotoReview;
import com.project.cinderella.model.domain.Product;
import com.project.cinderella.model.member.service.MemberService;
import com.project.cinderella.model.preview.service.PhotoReviewService;
import com.project.cinderella.model.product.service.CommentsService;
import com.project.cinderella.model.product.service.ProductService;
import com.project.cinderella.model.product.service.TopCategoryService;

@Controller
public class PhotoReviewController implements ServletContextAware{
	private static final Logger logger=LoggerFactory.getLogger(PhotoReviewController.class);
	@Autowired
	private FileManager filemanager;
	
	@Autowired
	private PhotoReviewService photoReviewService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private TopCategoryService topCategoryService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CommentsService commentsService;

	private ServletContext servletContext;
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		//이 타이밍을 놓치지말고, 실제 물리적 경로를 FileManager 에 대입해놓자!!!

		filemanager.setSavepreviewBasicDir(servletContext.getRealPath(filemanager.getSavepreviewBasicDir()));
		logger.debug(filemanager.getSavepreviewBasicDir());
		
	}
	
	
	//관리자모드에서 회원 포토리뷰목록 보기
    @RequestMapping(value = "/admin/member/member_photoreviewlist", method = RequestMethod.GET)
    public ModelAndView getAdminPhotoReviewList() {
      ModelAndView mav = new ModelAndView("admin/member/member_photoreviewlist");
       List photoReviewList = photoReviewService.selectAll();
       mav.addObject("photoReviewList", photoReviewList);
       return mav;
    }
    //관리자모드에서 포토리뷰디테일보기
    @RequestMapping(value = "/admin/member/member_photoreviewdetail", method =  RequestMethod.GET)
    public ModelAndView getAdminPhotoReviewDetail(int photoreview_id) {
    	
    	logger.debug("photorevierw_id=",photoreview_id);
    	PhotoReview photoReview = photoReviewService.select(photoreview_id);
    	ModelAndView mav = new ModelAndView("admin/member/member_photoreviewdetail");
    	mav.addObject("photoReview", photoReview);
    	return mav;
    }
    
    //관리자 포토리뷰 디테일에서 메일 발송하기
    @RequestMapping(value = "/admin/member/shot_email", method = RequestMethod.POST)
    public String getAdminShotEmail(@RequestParam("user_id")String user_id) {
    	System.out.println("user_id:"+user_id);
    	memberService.shotmail(user_id);
    	return "redirect:/admin/member/member_photoreviewlist";
    }
	/********************shop************************************************************************/
	
		@RequestMapping(value = "/shop/photoreview/registform", method = RequestMethod.GET)
		public ModelAndView registForm(Member member, HttpServletRequest request) {
			List topList = topCategoryService.selectAll();
			logger.debug("레지폼띄울께");
			/*
			 * Member obj = memberService.select(member); // 존재 O : 세션에 회원정보 담아두기
			 * HttpSession session = request.getSession();
			 */
			ModelAndView mav = new ModelAndView();
			//session.setAttribute("member", obj);
			mav.addObject("topList", topList);
			mav.setViewName("shop/photoreview/regist_form");
			return mav;
		}
		//등록
		@RequestMapping(value = "/shop/photoreview/regist", method = RequestMethod.POST, produces ="text/html;charset=utf8")
		@ResponseBody
		public String reviewRegist(PhotoReview photoReview) {
			System.out.println("photoReview="+photoReview);
			
			photoReviewService.regist(filemanager, photoReview);
			StringBuilder sb = new StringBuilder();
			sb.append("{");
			sb.append("\"result\":1,");
			sb.append("\"msg\":\"리뷰등록 성공\"");
			sb.append("}");
			return sb.toString();
		}
		//삭제
		@RequestMapping(value = "/shop/photoreview/delete" , method = RequestMethod.GET)
		@ResponseBody
		public List delPhotoReview(int photoreView_id) {
			photoReviewService.delete(photoreView_id);
			List photoReviewList = photoReviewService.selectAll();
			return  photoReviewList;
		}
		//mylog에서 삭제
		@RequestMapping(value = "/shop/photoreview/deleteandshowbyid" , method = RequestMethod.GET)
		@ResponseBody
		public ModelAndView delMyLogPhotoReview(int photoreView_id,String user_id) {
			ModelAndView mav = new ModelAndView();
			List topList = topCategoryService.selectAll();
			
			photoReviewService.delete(photoreView_id);
			
			mav.setViewName("redirect:/shop/member/mylog?user_id="+user_id);
			return mav;
	
		}
		//카드목록
		@RequestMapping(value = "/shop/photoreview/list", method = RequestMethod.GET)
		public ModelAndView getPhotoReviewList() {
			List topList = topCategoryService.selectAll();
			List photoReviewList = photoReviewService.selectAll();
			ModelAndView mav = new ModelAndView();
			mav.addObject("topList", topList);
			mav.addObject("photoReviewList", photoReviewList);

			mav.setViewName("shop/photoreview/list");
			return mav;
		}
	
		
}

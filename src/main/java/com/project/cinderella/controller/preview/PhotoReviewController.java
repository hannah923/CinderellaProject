package com.project.cinderella.controller.preview;

import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.project.cinderella.common.FileManager;
import com.project.cinderella.model.domain.PhotoReview;
import com.project.cinderella.model.preview.service.PhotoReviewService;

@Controller
public class PhotoReviewController implements ServletContextAware{
	private static final Logger logger=LoggerFactory.getLogger(PhotoReviewController.class);
	@Autowired
	private FileManager filemanager;
	
	@Autowired
	private PhotoReviewService photoReviewService;
	
	private ServletContext servletContext;
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		//�� Ÿ�̹��� ��ġ������, ���� ������ ��θ� FileManager �� �����س���!!!

		filemanager.setSavepreviewBasicDir(servletContext.getRealPath(filemanager.getSavepreviewBasicDir()));
		logger.debug(filemanager.getSavepreviewBasicDir());
		
	}
	
	
		@RequestMapping(value = "/shop/RegistReview", method = RequestMethod.GET)
		public String registReview() {
			return "shop/RegistReview";
		}
		//���
		@RequestMapping(value = "/shop/review/regist", method = RequestMethod.POST, produces ="text/html;charset=utf8")
		@ResponseBody
		public String reviewRegist(PhotoReview photoReview) {
			photoReviewService.regist(filemanager, photoReview);
			StringBuilder sb = new StringBuilder();
			sb.append("{");
			sb.append("\"result\":1,");
			sb.append("\"msg\":\"��ǰ��� ����\"");
			sb.append("}");
			return sb.toString();
		}
		//����
		@RequestMapping(value = "/shop/review/del" , method = RequestMethod.GET)
		public String delPhotoReview(int photoreview_id) {
			photoReviewService.delete(photoreview_id);
			return "redirect:/shop/photoreview";
		}
		//ī����
		@RequestMapping(value = "/shop/photoreview", method = RequestMethod.GET)
		public ModelAndView getPhotoReviewList() {
			ModelAndView mav = new ModelAndView("shop/photoreview");
			List photoReviewList = photoReviewService.selectAll();
			mav.addObject("photoReviewList", photoReviewList);
			return mav;
		}
		
}

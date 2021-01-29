package com.project.cinderella.controller.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.project.cinderella.controller.product.ProductController;
import com.project.cinderella.model.domain.Member;
import com.project.cinderella.model.preview.service.PhotoReviewService;
import com.project.cinderella.model.product.service.ProductService;
import com.project.cinderella.model.product.service.TopCategoryService;

@Controller
public class MainController {
   private static final Logger logger = LoggerFactory.getLogger(MainController.class);
   
   @Autowired
   private TopCategoryService topCategoryService;
   
   @Autowired
   private ProductService productService;
   
   @Autowired
   private PhotoReviewService photoReviewService; 

   @RequestMapping(value="/", method=RequestMethod.GET)
   public ModelAndView main(HttpServletRequest request, HttpSession session) {
      
      Member member =(Member)session.getAttribute("member");
      ModelAndView mav = new ModelAndView();
      //카테고리들 가져오기
      List topList = topCategoryService.selectAll();
      List productListByHit = productService.selectAllByHit();
      List productListByNew = productService.selectAll();
      List photoReviewList = photoReviewService.selectAll();
      mav.addObject("topList", topList);
      mav.addObject("productListByHit", productListByHit);
      mav.addObject("photoReviewList", photoReviewList);
      mav.addObject("productListByNew", productListByNew);
      mav.setViewName("index"); //메인 페이지
      return mav;
   }
}
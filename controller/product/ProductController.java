package com.project.cinderella.controller.product;

import java.util.List;

import javax.servlet.ServletContext;
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
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.project.cinderella.common.FileManager;
import com.project.cinderella.exception.ProductRegistException;
import com.project.cinderella.model.domain.Comments;
import com.project.cinderella.model.domain.Gender;
import com.project.cinderella.model.domain.Member;
import com.project.cinderella.model.domain.PhotoReview;
import com.project.cinderella.model.domain.Product;
import com.project.cinderella.model.domain.Psize;
import com.project.cinderella.model.domain.SubCategory;
import com.project.cinderella.model.domain.Tag;
import com.project.cinderella.model.preview.service.PhotoReviewService;
import com.project.cinderella.model.product.service.CommentsService;
import com.project.cinderella.model.product.service.ProductService;
import com.project.cinderella.model.product.service.SubCategoryService;
import com.project.cinderella.model.product.service.TopCategoryService;

@Controller
public class ProductController implements ServletContextAware {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private TopCategoryService topCategoryService;

	@Autowired
	private SubCategoryService subCategoryService;

	@Autowired
	private ProductService productService;

	@Autowired
	private CommentsService commentsService;

	@Autowired
	private PhotoReviewService photoReviewService;

	@Autowired
	private FileManager fileManager;
	

	// servletContext를 쓰는 이유?
	// ㄴ getRealPath() 사용하기 위해
	private ServletContext servletContext;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		// 이 타이밍을 놓치지말고, 실제 물리적 경로를 FileManager에 대입
		fileManager.setSaveBasicDir(servletContext.getRealPath(fileManager.getSaveBasicDir()));
		fileManager.setSaveAddonDir(servletContext.getRealPath(fileManager.getSaveAddonDir()));

		logger.debug(fileManager.getSaveBasicDir());
	}

	// 상품목록
	 @RequestMapping(value = "/admin/product/list", method = RequestMethod.GET)
	   public ModelAndView getProductList() {
	      ModelAndView mav = new ModelAndView("admin/product/product_list");
	      List productList = productService.selectAll();
	      mav.addObject("productList", productList);
	      return mav;
	   }


	// 상품등록 폼
	@RequestMapping(value = "/admin/product/registform")
	public String registForm() {
		return "admin/product/regist_form";
	}

	  // 상위카테고리 가져오기 - 관리자용
	   @RequestMapping(value = "/admin/product/registform", method = RequestMethod.GET)
	   public ModelAndView getTopList() {
	      // 3단계: 로직 객체에 일시킨다
	      List topList = topCategoryService.selectAll();

	      // 4단계: 저장
	      ModelAndView mav = new ModelAndView();
	      mav.addObject("topList", topList);
	      mav.setViewName("admin/product/regist_form");

	      return mav;
	   }


	   // 하위카테고리 가져오기 - 관리자용
	   @RequestMapping(value = "/admin/product/sublist", method = RequestMethod.GET)
	   @ResponseBody
	   public List getSubList(int topcategory_id) {
	      List<SubCategory> subList = subCategoryService.selectAllById(topcategory_id);
	      System.out.println(subList);
	      return subList;
	   }


	   // 상품 등록
	   @RequestMapping(value = "/admin/product/regist", method = RequestMethod.POST, produces = "text/html;charset=utf8")
	   @ResponseBody
	   public String registProduct(Product product) {
	      /*
	       * logger.debug("하위카테고리 "+product.getSubcategory_id());
	       * logger.debug("상품명 "+product.getProduct_name());
	       * logger.debug("가격 "+product.getPrice());
	       * logger.debug("브랜드 "+product.getBrand());
	       * logger.debug("상세내용 "+product.getDetail());
	       * logger.debug("업로드 이미지명 "+product.getRepImg().getOriginalFilename());
	       * 
	       * for(int i=0;i<product.getAddImg().length;i++) {
	       * logger.debug(product.getAddImg()[i].getOriginalFilename()); }
	       */

	      for (Psize psize : product.getPsize()) {
	         logger.debug(psize.getFit());
	      }
	      for (Tag tag : product.getPtag()) {
	         logger.debug(tag.getTag_name());
	      }

	      for (Gender gender : product.getPgender()) {
	         logger.debug(gender.getGender_name());
	      }

	      productService.regist(fileManager, product); // 상품등록
	      /*
	       * //파일명이 생성되었으므로, 저장 //실제 물리적 경로를 얻어오려면, servletContext가 보유한 getRealPath() 메서드가
	       * 필요하다 try { //File.separator는 디렉토리에 맞게 window는 / 로 자동으로 해줌
	       * product.getRepImg().transferTo(new
	       * File(fileManager.getSaveBasicDir()+File.separator+basicImg)); //저장 } catch
	       * (IllegalStateException e) { e.printStackTrace(); } catch (IOException e) {
	       * e.printStackTrace(); }
	       */

	      /*
	       * for(int i=0;i<product.getFit().length;i++) { String fit =
	       * product.getFit()[i]; logger.debug("지원 사이즈는  "+fit); }
	       */

	      StringBuilder sb = new StringBuilder();
	      sb.append("{");
	      sb.append("\"result\":1,");
	      sb.append("\"msg\":\"상품등록 성공\"");
	      sb.append("}");

	      return sb.toString();
	   }

	   // 한 건 가져오기
	   @RequestMapping(value = "/admin/product/detail", method = RequestMethod.GET)
	   public ModelAndView select(int product_id) {
	      logger.debug("product_id : " + product_id);
	    
	      Product product = productService.select(product_id);
	     
	      ModelAndView mav = new ModelAndView("admin/product/detail");
	      mav.addObject("product", product);
	 
	      return mav;
	   }

	   // 수정하기
	   @RequestMapping(value = "/admin/product/edit", method = RequestMethod.POST)
	   public String update(Product product) {
	      productService.update(product);
	      return "redirect:/admin/product/list";
	   }

	   // 삭제하기
	   @RequestMapping(value = "/admin/product/delete", method = RequestMethod.POST)
	   public String delete(int product_id) {
	      logger.debug("product_id는?" + product_id);
	         //commentsService.delete(product_id);
	         productService.delete(product_id);
	      return "redirect:/admin/product/list";
	   }

	   // 예외처리
	   // 위의 메서드 중에서 하나라도 예외가 발생하면, 아래의 핸들러가 동작
	   @ExceptionHandler(ProductRegistException.class)
	   @ResponseBody
	   public String handleException(ProductRegistException e) {
	      StringBuilder sb = new StringBuilder();
	      sb.append("{");
	      sb.append("\"result\":0");
	      sb.append("\"msg\":\"" + e.getMessage() + "\"");
	      sb.append("}");
	      return sb.toString();
	   }




	/*
	 * *********************************************************************** 프론트
	 * 요청 처리
	 ************************************************************************/
	   // TopCategory의 전체 상품목록 요청 처리
	   @RequestMapping(value = "/shop/product/list", method = RequestMethod.GET)
	   public ModelAndView getShopProductList(int topcategory_id) {
	      List topList = topCategoryService.selectAll();
	      // List subList = subCategoryService.selectAllById(topcategory_id);

	      // logger.debug("ㅇㅇㅇ "+subcategory_id);
	      List productList = productService.selectByTopcategoryId(topcategory_id);

	      

	      ModelAndView mav = new ModelAndView();
	      mav.addObject("topcategory_id", topcategory_id);
	      mav.addObject("topList", topList);
	      mav.addObject("productList", productList);

	      mav.setViewName("shop/product/list");
	      return mav;
	   }



	   // SubCategory의 전체 상품목록 요청 처리
	   @RequestMapping(value = "/shop/product/sublist", method = RequestMethod.GET)
	   public ModelAndView getShopProductListBySub(int subcategory_id) {
	      List topList = topCategoryService.selectAll();
	      List productList = productService.selectBySubcategoryId(subcategory_id);

	      ModelAndView mav = new ModelAndView();
	      mav.addObject("topList", topList);
	      mav.addObject("productList", productList);

	      mav.setViewName("shop/product/list");
	      return mav;
	   }



	   // 상품 상세보기 요청 처리
	   @RequestMapping(value = "/shop/product/detail", method = RequestMethod.GET)
	   public ModelAndView getShopProductDetail(int product_id,String product_name) {
	      List topList = topCategoryService.selectAll();
	      productService.updateHit(product_id);
	      List<PhotoReview> photoReviewList = photoReviewService.selectForProductDetail(product_name);
	      Product product = productService.select(product_id); // 상품 1건 가져오기
	      List<Comments> commentsList = commentsService.selectById(product_id);

	      ModelAndView mav = new ModelAndView();
	      mav.addObject("topList", topList);
	      mav.addObject("product", product);
	      mav.addObject("photoReviewList", photoReviewList);
	      mav.addObject("commentsList", commentsList);
	      return mav;
	   }
	
	   


	   // 사이즈별 상품목록 요청 처리
	   @RequestMapping(value = "/shop/product/size", method = RequestMethod.GET)
	   public ModelAndView getShopProductSize(String size,HttpServletRequest request) {
	      List topList = topCategoryService.selectAll();
	      List productListBySize = productService.selectBySize(size);

	      ModelAndView mav = new ModelAndView();
	      mav.addObject("topList", topList);
	      mav.addObject("productListBySize", productListBySize);
	      mav.setViewName("shop/product/list");
	      return mav;
	   }

	   /* 댓글 처리 */
	   // 상품 상세보기 댓글 등록요청
	   @RequestMapping(value = "/shop/product/comment_regist", method = RequestMethod.POST, produces = "text/html;charset=utf8")
	   @ResponseBody
	   public String setComment(Comments comments, HttpSession session) {
	      commentsService.insert(comments);
	      Member member =(Member)session.getAttribute("member");

	      StringBuilder sb = new StringBuilder();
	      sb.append("{");
	      sb.append("\"result\":1,");
	      sb.append("\"msg\":\"댓글등록 성공\"");
	      sb.append("}");

	      return sb.toString();
	   }

	   // 댓글 각각 삭제하기
	   @RequestMapping(value = "/shop/product/comment_delete", method = RequestMethod.GET)
	   public String deleteComment(int comment_id) {
	      commentsService.deleteById(comment_id);
	      
	      List topList = topCategoryService.selectAll();

	      return "redirect:/";
	   }

	   
	   /*
	    * //상품에 대한 댓글들 전체 삭제하기
	    * 
	    * @RequestMapping(value = "/shop/product/comment_deleteAll", method =
	    * RequestMethod.POST) public String deleteCommentAll(int product_id) {
	    * commentsService.delete(product_id);
	    * 
	    * StringBuilder sb = new StringBuilder(); sb.append("{");
	    * sb.append("\"result\":1,"); sb.append("\"msg\":\"댓글삭제 성공\""); sb.append("}");
	    * 
	    * return "/admin/product/delete"; }
	    */

	}
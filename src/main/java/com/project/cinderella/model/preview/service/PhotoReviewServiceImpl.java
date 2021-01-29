package com.project.cinderella.model.preview.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cinderella.common.FileManager;
import com.project.cinderella.model.domain.PhotoReview;
import com.project.cinderella.model.preview.repository.PhotoReviewDAO;
import com.project.cinderella.model.product.repository.ProductDAO;

@Service
public class PhotoReviewServiceImpl implements PhotoReviewService{
	private static final Logger logger=LoggerFactory.getLogger(PhotoReviewServiceImpl.class);

	@Autowired
	private PhotoReviewDAO photoReviewDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	
	@Override
	public List selectAll() {
		
		return photoReviewDAO.selectAll();
	}

	@Override
	public PhotoReview select(int photoreview_id) {
		
		return photoReviewDAO.select(photoreview_id);
	}

	@Override
	public void regist(FileManager fileManager, PhotoReview photoReview) {
		String ext=fileManager.getExtend(photoReview.getRepImg().getOriginalFilename());
		int product_id = productDAO.selectProductId(photoReview.getProduct_name());
		Date today = new Date();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String regdate = format1.format(today).toString();
		System.out.println("#21photoReview="+photoReview);
		photoReview.setRegdate(regdate);
		photoReview.setProduct_id(product_id);
		photoReview.setFilename(ext);
		photoReviewDAO.insert(photoReview);
		System.out.println("#2photoReview="+photoReview);
		System.out.println("photoReview.getPhotoreview_id()="+photoReview.getPhotoreview_id());
		String basicImg = photoReview.getPhotoreview_id()+"."+ext;
		fileManager.saveFile(fileManager.getSavepreviewBasicDir()+File.separator+basicImg,photoReview.getRepImg());
		//logger.debug("시발 이미지 경로다 개객갸:",fileManager.saveFile(fileManager.getSavepreviewBasicDir()+File.separator+basicImg,photoReview.getRepImg()););
		
	}
	

	@Override
	public void delete(int photoreview_id) {
		photoReviewDAO.delete(photoreview_id);
	}

	@Override
	public List selectForProductDetail(String product_name) {
		
		return photoReviewDAO.selectForProductDetail(product_name);
	}

	@Override
	public List selectForMylog(String user_id) {
		
		return photoReviewDAO.selectForMylog(user_id);
	}

}

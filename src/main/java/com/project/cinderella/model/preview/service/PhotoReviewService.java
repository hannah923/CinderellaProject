package com.project.cinderella.model.preview.service;

import java.util.List; 

import org.springframework.stereotype.Service;

import com.project.cinderella.common.FileManager;
import com.project.cinderella.model.domain.PhotoReview;

@Service
public interface PhotoReviewService {
	public List selectAll();
	public List selectForProductDetail(String product_name);
	public List selectForMylog(String user_id);
	
	public PhotoReview select(int photoreview_id); 
	public void regist(FileManager fileManager,PhotoReview photoReview);
	public void delete(int photoreview_id);
}

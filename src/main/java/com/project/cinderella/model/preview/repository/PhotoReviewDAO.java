package com.project.cinderella.model.preview.repository;
import java.util.List;

import com.project.cinderella.model.domain.PhotoReview;

public interface PhotoReviewDAO {
	public List selectAll();
	public List selectForProductDetail(String product_name);
	public List selectForMylog(String user_id);
	public PhotoReview select(int photoreview_id);
	public void insert(PhotoReview photoReview);
	
	public void delete(int photoreview_id);
	

}

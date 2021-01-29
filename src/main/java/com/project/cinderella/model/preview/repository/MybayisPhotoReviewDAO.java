package com.project.cinderella.model.preview.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.cinderella.model.domain.PhotoReview;

@Repository
public class MybayisPhotoReviewDAO implements PhotoReviewDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List selectAll() {
		
		return sqlSessionTemplate.selectList("PhotoReview.selectAll"); 
	}

	@Override
	public PhotoReview select(int photoreview_id) {
	
		return sqlSessionTemplate.selectOne("PhotoReview.select", photoreview_id);
	}
	
	@Override
	public List selectForProductDetail(String product_name) {
		return sqlSessionTemplate.selectList("PhotoReview.selectforproductdetail", product_name);
	}

	@Override
	public void insert(PhotoReview photoReview) {
		
		sqlSessionTemplate.insert("PhotoReview.insert", photoReview);
		
	}

	@Override
	public void delete(int photoreview_id) {
		sqlSessionTemplate.delete("PhotoReview.delete",photoreview_id);
		
	}

	@Override
	public List selectForMylog(String user_id) {
		
		return sqlSessionTemplate.selectList("PhotoReview.selectForMylog",user_id);
	}


}

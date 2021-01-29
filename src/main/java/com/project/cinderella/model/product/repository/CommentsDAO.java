package com.project.cinderella.model.product.repository;

import java.util.List;

import com.project.cinderella.model.domain.Comments;

public interface CommentsDAO {
   public List selectById(int product_id);
   public void insert(Comments comments);
   public void update(Comments comments);
   public void deleteById(int comment_id);
   public void delete(int product_id);
}
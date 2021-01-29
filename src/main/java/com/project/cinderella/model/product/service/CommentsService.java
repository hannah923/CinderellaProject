package com.project.cinderella.model.product.service;

import java.util.List;

import com.project.cinderella.model.domain.Comments;

public interface CommentsService {
   public List selectById(int product_id);
   public void insert(Comments comments);
   public void update(Comments comments);
   public void delete(int product_id);
   public void deleteById(int comments_id);
}
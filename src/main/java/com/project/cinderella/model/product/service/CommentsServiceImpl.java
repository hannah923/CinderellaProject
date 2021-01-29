package com.project.cinderella.model.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cinderella.exception.CommentsRegistException;
import com.project.cinderella.model.domain.Comments;
import com.project.cinderella.model.product.repository.CommentsDAO;

@Service
public class CommentsServiceImpl implements CommentsService{

   @Autowired
   private CommentsDAO commentsDAO;
   
   @Override
   public List selectById(int product_id) {
      return commentsDAO.selectById(product_id);
   }

   @Override
   public void insert(Comments comments) throws CommentsRegistException {
      commentsDAO.insert(comments);
   }

   @Override
   public void update(Comments comments) throws CommentsRegistException  {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void delete(int product_id) throws CommentsRegistException  {
      commentsDAO.delete(product_id);
      
   }

   @Override
   public void deleteById(int comments_id) throws CommentsRegistException   {
      commentsDAO.deleteById(comments_id);
      
   }


   

}
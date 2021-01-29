package com.project.cinderella.model.product.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.cinderella.exception.CommentsRegistException;
import com.project.cinderella.model.domain.Comments;

@Repository
public class MybatisCommentDAO implements CommentsDAO {
   private static final Logger logger = LoggerFactory.getLogger(MybatisCommentDAO.class);
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   @Override
   public List selectById(int product_id) {
      return sqlSessionTemplate.selectList("Comments.selectById", product_id);
   }

   @Override
   public void insert(Comments comments) throws CommentsRegistException {
      int result = sqlSessionTemplate.insert("Comments.insert", comments);
      if(result==0) {
         throw new CommentsRegistException("댓글등록 실패");
      }
   }

   @Override
   public void update(Comments comments) throws CommentsRegistException {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void delete(int product_id) throws CommentsRegistException {
      int result = sqlSessionTemplate.delete("Comments.delete", product_id);
      if(result==0) {
         logger.debug("CommentsRegistException");
         throw new CommentsRegistException("댓글삭제 실패");
      }
   }

   @Override
   public void deleteById(int comment_id) throws CommentsRegistException {
      sqlSessionTemplate.delete("Comments.deleteById", comment_id);
   }


}
package com.project.cinderella.model.payment.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.cinderella.exception.OrderSummaryRegistException;
import com.project.cinderella.model.domain.OrderSummary;

@Repository
public class MybatisOrderStateDAO implements OrderStateDAO{
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   

   @Override
   public List selectAll() {
      return sqlSessionTemplate.selectList("OrderState.selectAll");
      
   }



   
   
}
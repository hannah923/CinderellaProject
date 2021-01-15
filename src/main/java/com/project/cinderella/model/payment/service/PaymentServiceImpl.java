package com.project.cinderella.model.payment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cinderella.exception.CartException;
import com.project.cinderella.model.domain.Cart;
import com.project.cinderella.model.domain.Member;
import com.project.cinderella.model.domain.OrderSummary;
import com.project.cinderella.model.domain.Receiver;
import com.project.cinderella.model.payment.repository.CartDAO;
import com.project.cinderella.model.payment.repository.OrderSummaryDAO;
import com.project.cinderella.model.payment.repository.PaymethodDAO;
import com.project.cinderella.model.payment.repository.ReceiverDAO;


@Service
public class PaymentServiceImpl implements PaymentService{
   @Autowired
   private CartDAO cartDAO;
   
   
   @Autowired
   private PaymethodDAO paymethodDAO;

   
   //μ£Όλ¬Έκ΄?? ¨ 3κ°?μ§? DAO 
   @Autowired
   private OrderSummaryDAO orderSummaryDAO;
   
   @Autowired
   private ReceiverDAO receiverDAO;
   
   /*
    * @Autowired private OrderDetailDAO orderDetailDAO;
    */
   
   
   @Override
   public List selectCartList() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public List selectCartList(int member_id) {
      return cartDAO.selectAll(member_id);
   }

   @Override
   public Cart selectCart(int cart_id) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public void insert(Cart cart) throws CartException{
      cartDAO.duplicateCheck(cart);
      cartDAO.insert(cart);
   }

   @Override
   public void update(List<Cart> cartList) throws CartException{
      //?? κ°??λ§νΌ ??  ?μ²? 
      for(Cart cart : cartList) {
         cartDAO.update(cart);
      }
   }

   @Override
   public void delete(Cart cart) {
      // TODO Auto-generated method stub
      
   }

   public void delete(Member member) throws CartException{
      cartDAO.delete(member);
   }
   
   @Override
   public List selectPaymethodList() {
      return paymethodDAO.selectAll();
   }
   
   //μ£Όλ¬Έ ?±λ‘? 
   @Override
   public void registOrder(OrderSummary orderSummary, Receiver receiver) {
      //μ£Όλ¬Έ??½ ?±λ‘? 
      orderSummaryDAO.insert(orderSummary);
      //μ£Όλ¬Έ ??½?΄ ?±λ‘λ ?΄?, orderSummary VO?? mybatis? selectkey? ??΄ order_summary_id κ°?  μ±μ? Έ ??€..
      //?°?Ό? μ·¨λ? μ£Όλ¬Έλ²νΈλ₯? λ°λ?¬?, ??Έ? ?£?΄μ€μΌ ?¨.
      
      //λ°λ?¬? ? λ³? ?±λ‘?
      receiver.setOrder_summary_id(orderSummary.getOrder_summary_id()); //μ£Όλ¬Έλ²νΈ ? ?¬!!
      receiverDAO.insert(receiver);
      
      //μ£Όλ¬Έ??Έ ?±λ‘?
      //?₯λ°κ΅¬?λ₯? μ‘°ν??¬ OrderDetail VO μ²λ¦¬ 
      //?₯λ°κ΅¬? κ°?? Έ?€κΈ? 
      
      
   }
   
   
}
package com.project.cinderella.model.payment.service;

import java.util.List;

import com.project.cinderella.model.domain.Cart;
import com.project.cinderella.model.domain.Member;
import com.project.cinderella.model.domain.OrderSummary;
import com.project.cinderella.model.domain.Receiver;

public interface PaymentService {
   // 장바구니 관련 업무
   public List selectCartList();// 회원 구분없이 모든 데이터 가져오기

   public List selectCartList(int member_id);// 특정 회원의 장바구니 내역

   public Cart selectCart(int cart_id);

   public void insert(Cart cart);

   public void update(List<Cart> cartList); // 일괄 수정

   public void delete(Cart cart); // pk에 속한 데이터 삭제할예정

   public void delete(Member member); // 회원에 속한 데이터 삭제할예정

   public void delete(int product_id); // 회원에 속한 데이터 삭제할예정

   // 결제 업무
   public List selectPaymethodList();
   
   public List selectOrderList(); //주문목록 가져오기
   public List selectOrderStateList(); //주문목록 가져오기

   // public void registOrder(OrderSummary orderSummary);//트랜잭션 처리가 요구되는 메서드...
   public void registOrder(OrderSummary orderSummary, Receiver receiver);// 트랜잭션 처리가 요구되는 메서드...
}
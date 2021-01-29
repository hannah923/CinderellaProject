package com.project.cinderella.model.payment.repository;

import java.util.List;

import com.project.cinderella.model.domain.Cart;
import com.project.cinderella.model.domain.Member;

public interface CartDAO {
   public List selectAll(); //회원 구분 없이 모든 데이터 가져오기
   public List selectAll(int member_id); //특정 회원의 장바구니 내역
   public Cart select(int cart_id);
   public void duplicateCheck(Cart cart); //장바구니 중복상품 여부 체크
   public void insert(Cart cart); 
   public void update(Cart cart);
   public void deleteOne(int cart_id); //PK를 이용한 삭제
   public void deleteCart(int product_id); //상품이 삭제될때 그 상품만 카트에서 삭제
   public void delete(Member member); //회원에 속한 데이터 삭제할 예정
}
package com.project.cinderella.model.payment.service;

import java.util.List;

import com.project.cinderella.model.domain.Cart;
import com.project.cinderella.model.domain.Member;
import com.project.cinderella.model.domain.OrderSummary;
import com.project.cinderella.model.domain.Receiver;


public interface PaymentService {
	//?��바구?�� �??�� ?���?
	public List selectCartList();//?��?�� 구분?��?�� 모든 ?��?��?�� �??��?���? 
	public List selectCartList(int member_id);//?��?�� ?��?��?�� ?��바구?�� ?��?��
	public Cart selectCart(int cart_id);
	public void insert(Cart cart);
	public void update(List<Cart> cartList); //?���? ?��?��
	public void delete(Cart cart); //pk?�� ?��?�� ?��?��?�� ?��?��?��?��?��
	public void delete(Member member); //?��?��?�� ?��?�� ?��?��?�� ?��?��?��?��?��
	
	//결제 ?���?
	public List selectPaymethodList();
	public void registOrder(OrderSummary orderSummary, Receiver receiver);//?��?��?��?�� 처리�? ?��구되?�� 메서?��...
}

package com.project.cinderella.model.payment.service;

import java.util.List;

import com.project.cinderella.model.domain.Cart;
import com.project.cinderella.model.domain.Member;
import com.project.cinderella.model.domain.OrderSummary;
import com.project.cinderella.model.domain.Receiver;


public interface PaymentService {
	//?₯λ°κ΅¬? κ΄?? ¨ ?λ¬?
	public List selectCartList();//?? κ΅¬λΆ??΄ λͺ¨λ  ?°?΄?° κ°?? Έ?€κΈ? 
	public List selectCartList(int member_id);//?Ή?  ??? ?₯λ°κ΅¬? ?΄?­
	public Cart selectCart(int cart_id);
	public void insert(Cart cart);
	public void update(List<Cart> cartList); //?Όκ΄? ?? 
	public void delete(Cart cart); //pk? ?? ?°?΄?° ?­? ? ?? 
	public void delete(Member member); //??? ?? ?°?΄?° ?­? ? ?? 
	
	//κ²°μ  ?λ¬?
	public List selectPaymethodList();
	public void registOrder(OrderSummary orderSummary, Receiver receiver);//?Έ??­? μ²λ¦¬κ°? ?κ΅¬λ? λ©μ?...
}

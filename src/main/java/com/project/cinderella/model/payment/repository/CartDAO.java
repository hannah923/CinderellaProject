package com.project.cinderella.model.payment.repository;

import java.util.List;

import com.project.cinderella.model.domain.Cart;
import com.project.cinderella.model.domain.Member;

public interface CartDAO {
	public List selectAll(); //?? κ΅¬λΆ ??΄ λͺ¨λ  ?°?΄?° κ°?? Έ?€κΈ?
	public List selectAll(int member_id); //?Ή?  ??? ?₯λ°κ΅¬? ?΄?­
	public Cart select(int cart_id);
	public void duplicateCheck(Cart cart); //?₯λ°κ΅¬? μ€λ³΅?? ?¬λΆ? μ²΄ν¬
	public void insert(Cart cart); 
	public void update(Cart cart);
	public void delete(Cart cart); //PKλ₯? ?΄?©? ?­? 
	public void delete(Member member); //??? ?? ?°?΄?° ?­? ?  ?? 
}

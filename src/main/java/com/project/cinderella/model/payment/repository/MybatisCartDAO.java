package com.project.cinderella.model.payment.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.cinderella.exception.CartException;
import com.project.cinderella.model.domain.Cart;
import com.project.cinderella.model.domain.Member;

@Repository
public class MybatisCartDAO implements CartDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List selectAll(int member_id) {
		return sqlSessionTemplate.selectList("Cart.selectAll", member_id);
	}

	@Override
	public Cart select(int cart_id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void duplicateCheck(Cart cart) throws CartException{
		List list = sqlSessionTemplate.selectList("Cart.duplicateCheck", cart);
		
		if(list.size()>0) { //?���? ?��겨진 ?��?��?�� ?��?��?�� ?���?...
			throw new CartException("?��바구?��?�� ?���? ?��겨진 ?��?��?��?��?��");
		}
	}
	
	@Override
	public void insert(Cart cart) throws CartException{
		int result=sqlSessionTemplate.insert("Cart.insert", cart);
		if(result==0) {
			throw new CartException("?��바구?��?�� ?���? 못했?��?��?��.");
		}
	}

	@Override
	public void update(Cart cart) throws CartException{
		int result = sqlSessionTemplate.update("Cart.update", cart);
		if(result==0) {
			throw new CartException("?��바구?�� ?��?�� ?��?��");
		}
		
	}

	@Override
	public void delete(Cart cart) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(Member member) throws CartException{
		int result=sqlSessionTemplate.delete("Cart.delete", member.getMember_id());
		if(result==0) {
			throw new CartException("?��바구?�� ?��?�� ?��?��");
		}
	}
	
	
}
package com.project.cinderella.model.member.repository;

import java.util.List;

import com.project.cinderella.model.domain.Member;

public interface MemberDAO {
	  public List selectAll();
	   public Member select(Member member);
//방금 에러떴는데 저거 문제일까여?
	   /* public List selectMember(); */ 
	   public void insert(Member member);
	   public void update(Member member);
	   public void delete(Member member);
	   public Member selectbyuser_id(String user_id);
	   public void updateBuyCount(Member member, int member_id);


}

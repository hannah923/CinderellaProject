package com.project.cinderella.model.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cinderella.common.MailSender;
import com.project.cinderella.common.SecureManager;
import com.project.cinderella.exception.MemberNotFoundException;
import com.project.cinderella.model.domain.Member;
import com.project.cinderella.model.member.repository.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService{
   
   
   @Autowired
   private MemberDAO memberDAO;
   
   //암호화 객체 
      @Autowired
      private SecureManager secureManager;
      
      //메일발송 객체
      @Autowired
      private MailSender mailSender;
   
   @Override
   public List selectAll() {
      return memberDAO.selectAll();
   }

   
   /*
    * @Override public List selectMember() { return memberDAO.selectMember(); }
    */
    
   @Override
   public Member select(Member member) throws MemberNotFoundException {
      //유저가 전송한 파라미터비번을 해시값으로 변환하여 아래의 메서드 호출 
            String hash = secureManager.getSecureData(member.getPassword()); 
            member.setPassword(hash); //VO에 해시값 대입!!
            Member obj = memberDAO.select(member);
            return obj;
   }

   @Override
   public void regist(Member member) {
      //암호화 처리 
      
        String secureData = secureManager.getSecureData(member.getPassword());
        member.setPassword(secureData); //변환시켜 다시 VO에 대입
        System.out.println("서비스일시작");       
            memberDAO.insert(member);
            System.out.println("서비스일끝");
            String name=member.getName();
            String addr=member.getAddr();
            String email = member.getEmail_id()+"@"+member.getEmail_server();
            mailSender.send(email , name+"님 maleshop 가입축하", "많이 이용해주세요");
   }
      

   @Override
   public void update(Member member) {
      
	   String secureData = secureManager.getSecureData(member.getPassword());
       member.setPassword(secureData); //변환시켜 다시 VO에 대입
       System.out.println("서비스일시작");
       memberDAO.update(member);
       System.out.println("서비스일끝");
       
       
       
   }

   @Override
   public void delete(Member member) {
      memberDAO.delete(member);
      
   }


@Override
public void shotmail(String user_id) {
	Member member =memberDAO.selectbyuser_id(user_id);
	
	String name=member.getName();
	String email =  member.getEmail_id()+"@"+member.getEmail_server();
	mailSender.send(email, name+"님 이벤트 당첨(10000원 할인권)을 진심으로 축하드립니다!", "이벤트코드 : qweqwe12");
	
}


@Override
public void updateBuyCount(Member member, int member_id) {
	memberDAO.updateBuyCount(member, member_id);
	
}

}
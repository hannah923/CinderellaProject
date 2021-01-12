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
   
   //��ȣȭ ��ü 
      @Autowired
      private SecureManager secureManager;
      
      //���Ϲ߼� ��ü
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
      //������ ������ �Ķ���ͺ���� �ؽð����� ��ȯ�Ͽ� �Ʒ��� �޼��� ȣ�� 
            String hash = secureManager.getSecureData(member.getPassword()); 
            member.setPassword(hash); //VO�� �ؽð� ����!!
            Member obj = memberDAO.select(member);
            return obj;
   }

   @Override
   public void regist(Member member) {
      //��ȣȭ ó�� 
      
        String secureData = secureManager.getSecureData(member.getPassword());
        member.setPassword(secureData); //��ȯ���� �ٽ� VO�� ����
                   
            memberDAO.insert(member);
            
            String name=member.getName();
            String addr=member.getAddr();
            String email = member.getEmail_id()+"@"+member.getEmail_server();
      
            
            
            mailSender.send(email , name+"�� maleshop ��������", "���� �̿����ּ���");
   }

   @Override
   public void update(Member member) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void delete(Member member) {
      // TODO Auto-generated method stub
      
   }

}
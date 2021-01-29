/*
 * paging ó���� ���뼺�� ���� Ŭ���� ����
 * */
package com.project.cinderella.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.Data;

@Data
public class Pager {
   private int totalRecord; //�� ���ڵ� �� 
   private int pageSize = 10; //�������� ������ ���ڵ� �� 
   private int totalPage;
   private int blockSize=10; //���� ������ ������ ��
   private int currentPage = 1; //���� ������
   private int firstPage;
   private int lastPage;
   private int curPos;
   private int num ;
   
   
   //����� ���� �ʱ�ȭ
   public void init(HttpServletRequest request, List list) {
      totalRecord = list.size();
      totalPage = (int)Math.ceil((float)totalRecord/pageSize);
      //�������� ������ ��쿣, �� ���õ� �������� ��ü
      if(request.getParameter("currentPage")!=null) {
         currentPage = Integer.parseInt(request.getParameter("currentPage"));
      }
      firstPage = currentPage - (currentPage-1) % blockSize;
      lastPage = firstPage + (blockSize -1);
      curPos = (currentPage - 1) * pageSize;//�������� List�������� ���� index
      num = totalRecord - curPos; //�������� ���� ��ȣ
   }

}
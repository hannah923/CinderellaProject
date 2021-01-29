/*
 * paging Ã³ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ë¼ºï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ Å¬ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
 * */
package com.project.cinderella.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.Data;

@Data
public class Pager {
   private int totalRecord; //ÃÑ ·¹ÄÚµå ¼ö 
   private int pageSize = 10; //ÆäÀÌÁö´ç º¸¿©Áú ·¹ÄÚµå ¼ö 
   private int totalPage;
   private int blockSize=10; //ºí·°´ç º¸¿©Áú ÆäÀÌÁö ¼ö
   private int currentPage = 1; //ÇöÀç ÆäÀÌÁö
   private int firstPage;
   private int lastPage;
   private int curPos;
   private int num ;
   
   
   //¼±¾ğµÈ º¯¼ö ÃÊ±âÈ­
   public void init(HttpServletRequest request, List list) {
      totalRecord = list.size();
      totalPage = (int)Math.ceil((float)totalRecord/pageSize);
      //ÆäÀÌÁö¸¦ ¼±ÅÃÇÑ °æ¿ì¿£, ±× ¼±ÅÃµÈ ÆäÀÌÁö·Î ´ëÃ¼
      if(request.getParameter("currentPage")!=null) {
         currentPage = Integer.parseInt(request.getParameter("currentPage"));
      }
      firstPage = currentPage - (currentPage-1) % blockSize;
      lastPage = firstPage + (blockSize -1);
      curPos = (currentPage - 1) * pageSize;//ÆäÀÌÁö´ç List³»¿¡¼­ÀÇ ½ÃÀÛ index
      num = totalRecord - curPos; //ÆäÀÌÁö´ç ½ÃÀÛ ¹øÈ£
   }

}
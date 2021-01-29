<%@ page contentType="text/html; charset=utf-8"%>
<div class="navbar">
  <a href="/cinderella/admin">Home</a>
  <a href="/cinderella/admin/product/list">상품관리</a>
  <a href="/cinderella/admin/product/order_list">주문관리</a>
  
  
   <div class="dropdown">
  <button class="dropbtn">회원관리
      <i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-content">
      <a href="/cinderella/admin/member/member_list">회원목록</a>
      <a href="/cinderella/admin/member/member_photoreviewlist">포토리뷰</a>
      <a href="#">Link 3</a>
    </div>
  </div> 
 
  <div class="dropdown">
    <button class="dropbtn">고객센터
      <i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-content">
      <a href="#">Link 1</a>
      <a href="#">Link 2</a>
      <a href="#">Link 3</a>
    </div>
  </div> 
</div>
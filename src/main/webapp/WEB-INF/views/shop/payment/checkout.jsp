<%@page import="com.project.cinderella.common.Formatter"%>
<%@page import="com.project.cinderella.model.domain.Member"%>
<%@page import="com.project.cinderella.model.domain.Paymethod"%>
<%@page import="com.project.cinderella.model.domain.Cart"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%
   List<Cart> cartList = (List)request.getAttribute("cartList");
   List<Paymethod> paymethodList = (List)request.getAttribute("paymethodList");
   
   
   //장바구니로부터, 상품 가액 계산
   int totalPrice=0;
   int totalPay = 0;
   for(Cart cart : cartList){
      totalPrice += (cart.getQuantity()*cart.getPrice());
   }
   totalPay= totalPrice+2500;
%>
<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Male_Fashion Template">
    <meta name="keywords" content="Male_Fashion, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Male-Fashion | Template</title>
    <%@ include file="../inc/header.jsp" %>
  

   <script>
   function setData(ch){
      var form = document.querySelector("#checkout-form");
      if(ch.checked) {//체크를 했다면, 주문자 정보를 받는자 정보에 대입
         form.receiver_name.value = form.member_name.value;
         form.receiver_phone.value = form.member_phone.value;
         form.receiver_addr.value = form.member_addr.value;
         
      }else { //받는 사람 정보를 다시 원상 복구 (초기화)
         form.receiver_name.value="";
         form.receiver_phone.value="";
         form.receiver_addr.value="";
      }
   }
   
  

  
   function order(){
       var formData = $("#checkout-form").serialize(); //전부 문자열화 시킨다!!
      
       $.ajax({
           url:"/cinderella/shop/payment/regist",
           type:"post",
           data:formData,
           success:function(){
        	 alert("주문이 완료 되었습니다 메인페이지로 이동합니다");
             location.href="/cinderella/";
           }
        });
       
    } 
  
   </script>
   
</head>
<body>
   <%@ include file="../inc/top.jsp" %>
     <%Member member2=(Member)session.getAttribute("member"); %>
    <!-- Header Section End -->

    <!-- Breadcrumb Section Begin -->
    <section class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__text">
                        <h4>Check Out</h4>
                        <div class="breadcrumb__links">
                            <a href="/cinderella/">Home</a>
                            <a href="/cinderella/shop/cart/list">ShoppingCart</a>
                            <span>Check Out</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->

    <!-- Checkout Section Begin -->
    <section class="checkout spad">
        <div class="container">
            <div class="checkout__form">
                <form id="checkout-form">
                    <div class="row">
                        <div class="col-lg-8 col-md-6">
                            <h6 class="coupon__code"><span class="icon_tag_alt"></span> Have a coupon? <a href="#">Click
                            here</a> to enter your code</h6>
                            <h6 class="checkout__title">Billing Details</h6>
                            
                            <!-- <form id="checkout-form"> -->
                            <input type="hidden" name="total_price" value="<%=totalPrice %>">
                            <input type="hidden" name="total_pay" value="<%=totalPay%>">
                            
                            
                            <div class="row">
                                <div class="col-lg-6">
                                    <div class="checkout__input">
                                        <p>주문고객 명 <span>*</span></p>
                                        <input type="text" id="member_name" value="<%=member.getName() %>">
                                    </div>
                                </div>
                            </div>
                            <div class="checkout__input">
                                <p>연락처 <span>*</span></p>
                                <input type="text" class="form-control" id="member_phone" value="010-1234-5678" required>
                            </div>
                            <div class="checkout__input">
                                <p>주소<span>*</span></p>
                                <input type="text" class="checkout__input__add" id="member_zipcode" value="<%=member.getZipcode() %>">
                                <input type="text" id="member_addr" value="<%=member.getAddr()%>">
                            </div>
                            <div class="checkout__input">
                                <p>이메일 <span>*</span></p>
                                <input type="text" value="<%=member.getEmail_id()%>@<%=member.getEmail_server()%>">
                            </div>
                            
                            <div class="checkout__input__checkbox">
                                <label for="diff-acc">
                                     받는 사람과 동일
                                    <input type="checkbox" id="diff-acc" class="customCheck1"  onClick="setData(this)">
                                    <span class="checkmark"></span>
                                </label>
                            </div>
                            
                            <div class="row">
                                <div class="col-lg-6">
                                    <div class="checkout__input">
                                        <p>받으실 고객 명<span>*</span></p>
                                        <input type="text" name="receiver_name" id="receiver_name" value="">
                                    </div>
                                </div>
                            </div>
                            <div class="checkout__input">
                                <p>연락처 <span>*</span></p>
                                <input type="text" class="form-control" name="receiver_phone" value="" required>
                            </div>
                            <div class="checkout__input">
                                <p>주소<span>*</span></p>
                                <input type="text" class="checkout__input__add" name="receiver_addr" value="">
                            </div>
                            
                             <div class="checkout__input">
                                <p>결제방법 선택<span>*</span></p>
                                 <select  name="paymethod_id">
                                    <%for(Paymethod paymethod : paymethodList) { %>
                                         <option value="<%= paymethod.getPaymethod_id()%>"><%=paymethod.getMethod() %></option>
                                    <%} %>
                                   </select>
                                   
                                 
                            </div>
                              
                        </div>
                        
                        
                        
                        <div class="col-lg-4 col-md-6">
                            <div class="checkout__order">
                                <h4 class="order__title">Your order</h4>
                                <div class="checkout__order__products">Product <span>Total</span></div>
                                <ul class="checkout__total__products">
                                   <%int i=0;%>
                                   <%for(Cart cart : cartList){ %>
                                             <li><%=++i%> > <%=cart.getProduct_name() %> * <%=cart.getQuantity()%>개 <span><%=Formatter.getCurrency(cart.getPrice()*cart.getQuantity()) %></span></li>
                                    <%} %>
                                </ul>
                                <ul class="checkout__total__all">
                                    <li>Delivery charge <span>2,500</span></li>
                                    <li name="total_pay">Total <span><%=Formatter.getCurrency(totalPrice+2500) %></span></li>
                                </ul>
                                 
                                <div class="checkout__input__checkbox">
                                    <label for="acc-or">
                                        Create an account?
                                        <input type="checkbox" id="acc-or">
                                        <span class="checkmark"></span>
                                    </label>
                                </div>
                                <p>Lorem ipsum dolor sit amet, consectetur adip elit, sed do eiusmod tempor incididunt
                                ut labore et dolore magna aliqua.</p>
                                <div class="checkout__input__checkbox">
                                    <label for="payment">
                                        Check Payment
                                        <input type="checkbox" id="payment">
                                        <span class="checkmark"></span>
                                    </label>
                                </div>
                                <div class="checkout__input__checkbox">
                                    <label for="paypal">
                                        Paypal
                                        <input type="checkbox" id="paypal">
                                        <span class="checkmark"></span>
                                    </label>
                                </div>
                                 <input type="button" onClick="order()" class="site-btn">PLACE ORDER</button>
                               
                            </div>
                        </div>
                   
                
                 </div>
                 </form>
            </div>
        </div>
    </section>
    <!-- Checkout Section End -->
   <%@ include file="../inc/footer.jsp" %>
<%@ include file="../inc/bottom.jsp" %>
</body>

</html>
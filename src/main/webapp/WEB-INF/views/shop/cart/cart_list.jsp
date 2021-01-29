<%@page import="java.util.List"%>
<%@page import="com.project.cinderella.model.domain.Member"%>
<%@page import="com.project.cinderella.common.Formatter"%>
<%@page import="com.project.cinderella.model.domain.Cart"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%
	List<Cart> cartList = (List)request.getAttribute("cartList");
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
	function delCart(){
		if(confirm("장바구니 전체 삭제하시겠습니까?")){
			location.href="/cinderella/shop/cart/del";
		}	
	}
	
	function editCart(){
		if(confirm("주문 수량을 변경하시겠어요?")){
			$("#cart-form").attr({
				action:"/cinderella/shop/cart/edit",
				method:"post"
			});
			$("#cart-form").submit();
		}	
	}
	function checkoutForm(){
		location.href="/cinderella/shop/payment/form";	
	}
	</script>
</head>

<body>
<%@ include file="../inc/top.jsp" %>


    <!-- Shopping Cart Section Begin -->
    <section class="shopping-cart spad">
        <div class="container">
        	<form id="cart-form">
            <div class="row">
                <div class="col-lg-8">
                    <div class="shopping__cart__table">
                        <table>
                            <thead>
                                <tr>
                                    <th>Product</th>
                                    <th>Quantity</th>
                                    <th>Total</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                            	<%int sum=0; //합계 %>
                                <%for(Cart cart : cartList){ %>
                                <tr>
                                    <td class="product__cart__item">
                                        <div class="product__cart__item__pic"  width="10px" height="10px">
                                            <img src="/cinderella/resources/data/basic/<%=cart.getProduct_id() %>.<%=cart.getFilename()%>" width="80px" height="80px" alt="Product">
                                        </div>
                                        <div class="product__cart__item__text">
                                            <h6><%=cart.getProduct_name() %></h6>
                                            <h5><%=Formatter.getCurrency(cart.getPrice()) %></h5>
                                            <h5><%=cart.getPpsize()%></h5>
                                        </div>
                                    </td>
                                    <td class="quantity__item">
                                        <div class="quantity">
                                            <div class="pro-qty-2">
                                                <input type="hidden" name="cart_id" value="<%=cart.getCart_id()%>">
                                                <input type="number" name="quantity" class="qty-text" id="qty" step="1" min="1" max="99"  value="0">
                                            </div>
                                        </div>
                                    </td>
                                    <%
                                       sum = sum + (cart.getPrice()*cart.getQuantity());
                                    %>
                                    <td class="cart__price"><%=Formatter.getCurrency(cart.getPrice()*cart.getQuantity()) %></td>
                                    <%-- <td class="cart__close"><i class="fa fa-close" onClick="delCart()" value="<%=cart.getCart_id()%>" name="cart_id"></i></td> --%>
                                </tr>
                                <%} %>
                            </tbody>
                        </table>
                    </div>
                    </form>
                    <div class="row">
                        <div class="col-lg-6 col-md-6 col-sm-6">
                            <div class="continue__btn">
                               <a href="javascript:editCart()"><i class="fa fa-spinner"></i> Update cart</a>
                            </div>
                        </div>
                        
                        <div class="col-lg-6 col-md-6 col-sm-6">
                            <div class="continue__btn update__btn">
                                <a href="javascript:delCart()"><i class="fa fa-spinner"></i> Clear cart</a>
                            </div>
                        </div> 
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="cart__discount">
                        <h6>Discount Membership</h6>
                        <form action="#">
                            <input type="text" placeholder="Coupon code"> 
                           <%--  <%for(Cart cart : cartList){ %>
                            <%int member_id = cart.getMember_id(); %>
                            <label>회원님의 멤버십 등급은 <%=member_id%></label>
                            <%} %> --%>
                            <button type="submit">Apply</button>
                        </form>
                    </div>
                    <div class="cart__total">
                        <h6>Cart total</h6>
                        <ul>
                            <li>Delivery charge <span>2,500</span></li>
                            <li>Total <span><%=Formatter.getCurrency(sum+2500) %></span></li>
                        </ul>
                        <a href="javascript:checkoutForm()" class="primary-btn">Proceed to checkout</a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Shopping Cart Section End -->

   <%@ include file="../inc/footer.jsp" %>
   <%@ include file="../inc/bottom.jsp" %>
   </body>
</html>
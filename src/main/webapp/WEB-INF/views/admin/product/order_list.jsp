<%@page import="com.project.cinderella.model.domain.OrderState"%>
<%@page import="com.project.cinderella.model.domain.Member"%>
<%@page import="com.project.cinderella.model.domain.OrderSummary"%>
<%@page import="com.project.cinderella.model.domain.Product"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%
   List<OrderSummary> orderList =(List)request.getAttribute("orderList");
   List<Member> memberList =(List)request.getAttribute("memberList");
   List<OrderState> orderstateList =(List)request.getAttribute("orderstateList");
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="../inc/header.jsp" %>
<script>
/* $(function(){
   $("button").click(function(){
      location.href="/cinderella/admin/product/registform"; //글쓰기 폼 요청
   })
}) */
</script>
</head>
<body>
<%@ include file="../inc/main_navi.jsp" %>

   <h3>주문목록</h3>
   <p>
   <table>
      <tr>
         <th>회원번호</th>
         <th>결제가격</th>
         <th>주문날짜</th>
         <th>주문상태</th>
      </tr>
      <%for(int i=0;i<orderList.size();i++){%>
      <%OrderSummary ordersummary = orderList.get(i); %>
      <%Member member = memberList.get(ordersummary.getMember_id()-1); %>
      <%OrderState orderstate = orderstateList.get(ordersummary.getOrder_state_id()-1); %>
      <tr>
         <td><%=member.getUser_id() %></td>
         <td><%=ordersummary.getTotal_pay() %></td>
         <td><%=ordersummary.getOrderdate() %></td>
         <td><%=orderstate.getState() %></td>
         
         
      </tr>
      <%}%>
   
   </table>
   </p>


</body>
</html>
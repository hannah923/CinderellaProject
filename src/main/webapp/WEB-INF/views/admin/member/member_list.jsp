<%@page import="com.project.cinderella.model.domain.Member"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%
    List<Member> memberList =(List)request.getAttribute("memberList"); 
   
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="../inc/header.jsp" %>
<script>
/* $(function(){
   $("button").click(function(){
      location.href="/cinderella/admin/product/registform"; //글쓰기 폼 요청
   })
}) */
</script>
</head>
<body>
<%@include file="../inc/main_navi.jsp" %>

   <h3>회원목록</h3>
   <p>
   <table>
      <tr>
         <th>UserId</th>
         <th>Name</th>
         <th>Buy Count</th>
         
     
    </tr>
      <%for(int i=0;i<memberList.size();i++){ %>
      <%Member member = memberList.get(i); %>   
      <tr>
         <td><%=member.getUser_id()%></td>
         <td><%=member.getName()%></td>
         <td><%=member.getBuy_count()%></td>
        
      </tr>
      <%} %> 
      
   </table>
   </p>


</body>
</html>
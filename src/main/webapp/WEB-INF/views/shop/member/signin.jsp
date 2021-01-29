<%@page import="com.project.cinderella.model.domain.TopCategory"%>
<%@page import="java.util.List"%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="../inc/header.jsp"%>
<%@ include file="../inc/top.jsp"%>
<!DOCTYPE html>
<html lang="zxx">
<head>
<%@ include file="../inc/header.jsp"%>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>eCommerce HTML-5 Template </title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="manifest" href="site.webmanifest">
    <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">
</head>

<script>
function login(){
   $("#loginForm").attr({
      action:"/cinderella/shop/member/login",
      method:"post"
   });      
   
   $("#loginForm").submit();
   
}
</script>   
<style>
</style>   
<body>

    <!-- Breadcrumb Section Begin -->
   <section class="breadcrumb-option">
      <div class="container">
         <div class="row">
            <div class="col-lg-12">
               <div class="breadcrumb__text" >
                  <h4>Log In</h4>
               </div>
            </div>
         </div>
      </div>
   </section>
   <!-- Breadcrumb Section End -->

    <!--================login_part Area =================-->
    <section class="login_part section_padding " style="text-align:center" width="100px">
        <div class="container">
            <div class="row align-items-center">
               <!--  <div class="col-lg-6 col-md-6">
                    <div class="login_part_text text-center">
                        <div class="login_part_text_iner">
                            <h2>New to our Shop?</h2>
                            <p>There are advances being made in science and technology
                                everyday, and a good example of this is the</p>
                            <a href="#" class="btn_3">Create an Account</a>
                        </div>
                    </div>
                </div> -->
                <div class="col-lg-6 _col-md-6"  style="margin:0 auto">
                    <div class="login_part_form">
                        <div class="login_part_form_iner">
                                <div id="loader" style="margin:auto"></div>
                           <form id="loginForm" style="text-align:center">
                           
                          
                           
                            <!-- <form class="row contact_form" action="#" method="post" novalidate="novalidate"> -->
                               <table style="margin:auto">
                               <tr>
                               <td colspan="2">
                                <h6 class="coupon__code">
                        <span class="icon_tag_alt"></span> 아직 회원이 아니신가요? <a href="/cinderella/shop/member/registForm"> 회원가입 하러가기
                          </a> 
                     </h6>
                     </td>
                               </tr>
                               
                               <tr>
                                <div class="col-md-12 form-group p_star">
                                  <td><h5>  ID : </h5></td> 
                                  <td class="signin_td"> <input type="text" name="user_id" placeholder="아이디를 입력하세요" required></td>
                                </div>
                                </tr>
                                <tr>
                                <div class="col-md-12 form-group p_star">
                                  <td> <h5> PASSWORD :  &nbsp;</h5></td>
                                  <td class="signin_td"><input type="password" name="password" placeholder="비밀번호를 입력하세요" required></td>
                                </div>
                                </tr>
                              <tr>
                              <td class="signin_td" colspan="2">
                              <!--   <div class="col-md-12 form-group">
                                    <div class="creat_account d-flex align-items-center">
                                       
                                    </div> -->
                                    <input type="button" value="Login" onClick="login()"  style="margin-top:20px">
                              <!--   </div> -->
                                </td>
                                <tr>
                                </table>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!--================login_part end =================-->

    <%@ include file="../inc/footer.jsp"%>
<%@ include file="../inc/bottom.jsp" %>


</body>
    
</html>
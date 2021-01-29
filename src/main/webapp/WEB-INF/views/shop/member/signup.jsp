<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="../inc/header.jsp"%>

<%@ include file="../inc/top.jsp"%>
<!DOCTYPE html>
<html lang="zxx">

<head>
<meta charset="UTF-8">
<meta name="description" content="Male_Fashion Template">
<meta name="keywords" content="Male_Fashion, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Male-Fashion | Template</title>

<!-- Google Font -->
<

   <script>
   $(function(){
      //회원가입 처리 
      $("input[type='button']").click(function(){
         
         regist();
      });   
   });

   //요청이 완료되는 시점에 프로그래스바를 감춘다!!
   function regist(){
      //로딩바 시작
      //$("#loader").addClass("loader"); //class 동적 적용
      
      //form 태그의 파라미터들을 전송할수있는 상태로 둬야  data키값에 form 자체를 넣을 수 있다.
      var formData = $("#member_form").serialize(); //전부 문자열화 시킨다!!
      //alert(formData);
      
      $.ajax({
         url:"/cinderella/shop/member/regist", 
         type:"post",
         data:formData,
         success:function(responseData){
            //서버로부터 완료 응답을 받으면 로딩바 효과를 중단!!
            //$("#loader").removeClass("loader"); //class 동적 제거
            var json = JSON.parse(responseData);
            if(json.result==1){
               alert(json.msg);
               location.href="/cinderella/"; //추후 로그인 페이지로 보낼예정
            }else{
               alert(json.msg);
            }
         }
      });
   }
   </script>   
</head>
<body>

   <!-- Header Section End -->

   <!-- Breadcrumb Section Begin -->
   <section class="breadcrumb-option">
      <div class="container">
         <div class="row">
            <div class="col-lg-12">
               <div class="breadcrumb__text">
                  <h4>Sign Up</h4>
                  
               </div>
            </div>
         </div>
      </div>
   </section>
   <!-- Breadcrumb Section End -->

   <!-- Checkout Section Begin -->
   <section class="checkout spad">
      <div class="container">
      <div id="loader" style="margin:auto"></div>
      <form id="member_form">
         <div class="checkout__form">
            <form action="#">
               <div class="row">
                  <div class="col-lg-8 col-md-6">
                     <h6 class="coupon__code">
                        <span class="icon_tag_alt"></span> Have a coupon? <a href="#">Click
                           here</a> to enter your code
                     </h6>
                     <h6 class="checkout__title">회원가입</h6>
            <!-- <div class="container">  -->  
         
         
                  
                     <div class="checkout__input">
                        <p>
                           아이디<span>*</span>
                        </p>
                        <input type="text" name="user_id" placeholder="Your ID..">
                     </div>
                     <div class="checkout__input">
                        <p>
                           이름<span>*</span>
                        </p>
                        <input type="text" name="name" placeholder="Your name..">
                     </div>
                     <div class="checkout__input">
                        <p>
                           비밀번호<span>*</span>
                        </p>
                         <input type="password" name="password"
                           placeholder="Your password.."> 
                     </div>

                     <!-- 이메일공간 -->
                     <div class="row">
                        <div class="col-lg-6">
                           <div class="checkout__input">
                              <p>
                                 이메일<span>*</span>
                              </p>
                              <input type="text" name="email_id" placeholder="이메일 아이디">
                           </div>
                        </div>
                        <div class="col-lg-6">
                           <div class="checkout__input">
                              <p>
                                 <span><br></span>
                              </p>
                             <select name="email_server">
                              <option value="gmail.com">gmail.com</option>
                              <option value="daum.net">daum.net</option>
                              <option value="naver.com">naver.com</option>
                         </select>
                           </div>
                        </div>
                     </div>


                     <div class="checkout__input">
                        <p>
                           우편번호<span>*</span>
                        </p>
                        <input type="text" name="zipcode" placeholder="우편번호">
                     </div>
                     <div class="checkout__input">
                        <p>
                           주소<span>*</span>
                        </p>
                        <input type="text" name="addr" placeholder="주소입력">
                     </div>
                     <div class="checkout__input">
                        <p></p>
                        <input type="button" value="가입">
                     </div>
                  </form>   
                  </div>      
                  </div>
                  
                  
               
               </div>
         </div>   
         </form>
      </div>
   </section>
   <!-- Checkout Section End -->

   <%@ include file="../inc/footer.jsp"%>
   <%@ include file="../inc/bottom.jsp" %>

</body>

</html>
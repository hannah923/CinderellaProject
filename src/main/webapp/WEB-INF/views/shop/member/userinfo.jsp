<%@page import="java.io.Console"%>
<%@page import="com.project.cinderella.model.domain.Member"%>
<%@ page contentType="text/html; charset=utf-8"%>

	

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
    <style >
		     textarea {
		  width: 100%;
		  padding: 12px;
		  border: 1px solid #ccc;
		  border-radius: 4px;
		  box-sizing: border-box;
		  margin-top: 6px;
		  margin-bottom: 16px;
		  resize: vertical;
		}
		/*드래드 관련 */
		#dragArea{
			width:100%;
			height:300px;
			overflow:scroll;
			border:1px solid #ccc;
		}
		.dragBorder{
			background:#ffffff;
		}
		.box{
			width:100px;
			float:left;
			padding:5px;
		}
		.box > img{
			width:100%; 
		}
		.close{
			color:red;
			cursor:pointer;
		}
    </style>
    <script>
    $(function(){
        //회원수정 처리 
        $($("input[type='button']")[0]).click(function(){
           
           edit();
        });
        $($("input[type='button']")[1]).click(function(){
            if(confirm("정말로 탈퇴하시겠습니까?")){
            	del();
            	location.href="/cinderella/";
            }
            	
         });
     });

     //요청이 완료되는 시점에 프로그래스바를 감춘다!!
     function edit(){
        //로딩바 시작
        //$("#loader").addClass("loader"); //class 동적 적용
        
        //form 태그의 파라미터들을 전송할수있는 상태로 둬야  data키값에 form 자체를 넣을 수 있다.
        var formData = $("#member_form").serialize(); //전부 문자열화 시킨다!!
        //alert(formData);
        $.ajax({
           url:"/cinderella/shop/member/edit", 
           type:"post",
           data:formData,
           success:function(){
            	alert("세션이 끊겼습니다 변경하신 정보로 재로그인이 필요합니다");
            	location.href="/cinderella/shop/member/logout";
           }
        });
        
            	
            	
     }
     function del(){
    	 var formData = $("#member_form").serialize(); //전부 문자열화 시킨다!!
    	 $.ajax({
             url:"/cinderella/shop/member/delete", 
             type:"post",
             data:formData,
             success:function(){
              	alert("세션이 끊겼습니다 메인 페이지로 이동합니다");
              	location.href="/cinderella/";
             }
          });
       
      }
   
    </script>
	

</head>

<body>
<%@ include file="../inc/top.jsp" %>
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
            
                  <div class="col-lg-8 col-md-6">
                     
                     <h6 class="checkout__title">회원정보</h6>
            <!-- <div class="container">  -->  
         
         
                  	<div class="checkout__input">
                        <p>
                           회원번호<span>*</span>
                        </p>
                        <input type="text" name="member_id" value="<%=member.getMember_id()%>">
                     </div>
                     <div class="checkout__input">
                        <p>
                           아이디<span>*</span>
                        </p>
                        <input type="text" name="user_id" value="<%=member.getUser_id()%>">
                     </div>
                     <div class="checkout__input">
                        <p>
                           이름<span>*</span>
                        </p>
                        <input type="text" name="name" value="<%=member.getName()%>">
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
                              <input type="text" name="email_id" value="<%=member.getEmail_id()%>">
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
                        <input type="text" name="zipcode" value="<%=member.getZipcode()%>">
                     </div>
                     <div class="checkout__input">
                        <p>
                           주소<span>*</span>
                        </p>
                        <input type="text" name="addr" value="<%=member.getAddr()%>">
                     </div>
                     <div class="checkout__input">
                        <p></p>
                        <input type="button" value="수정">
                        <input type="button" value="탈퇴">
                     </div>
                  </form>   
                  </div>    
                  </form>  
                  </div>
            
   </section>
   <!-- Checkout Section End -->

   <%@ include file="../inc/footer.jsp"%>
   <%@ include file="../inc/bottom.jsp" %>

</body>

</html>
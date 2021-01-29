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

   
	
	//상품 등록
	function regist(){
		 /*비동기 전송시, 기존의 form을 이용할수 있을까?  yes!!*/
		var formData = new FormData($("form")[0]);//<form>태그와는 틀리다..전송할때 파라미터들을 담을수있지만 이 자체가
														//폼태그는 아니다!!
		
		
		/*비동기 업로드*/
	$.ajax({
		url:"/cinderella/shop/photoreview/regist",
		data:formData,
		contentType:false, /* false일 경우 multipart/form-data*/
		processData:false, /* false일 경우 query-string으로 전송하지 않음*/
		type:"post",
		success:function(responseData){
			var json =JSON.parse(responseData);//string->json으로 파싱
			if(json.result==1){
				alert(json.msg);
				location.href="/cinderella/shop/photoreview/list";
			}else{
				alert(json.msg);
			}
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
                        <h4>Regist Review</h4>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->

    <!-- Checkout Section Begin -->

	<section class="checkout spad">
		<div style="position: relative; left: 1200px; top: 20px; cursor: pointer;">
			<a onClick="regist()" class="primary-btn">Regist <span
				class="arrow_right"> </span>
			</a>
		</div>

		<!--  <input type="button" class="preview-regist-btn" value="Regist" onClick="regist()">
     <input type="button" value="regist" onClick="regist()">   -->
		<div class="container">
			<div class="checkout__form">
				<form id="registfrom" action="#">
					<div class="row">
						<div class="col-lg-8 col-md-6">
							<h6 class="checkout__title">Registing Details</h6>
							<div class="row">
								<div class="col-lg-6">
									<div class="checkout__input">
										<p>
											User_id<span>*</span>
										</p>
										<%-- <%Member member = (Member)session.getAttribute("member"); %> --%>
										
										<input type="text" name=user_id  value="<%=member.getUser_id()%>">
									</div>
								</div>
								<div class="col-lg-6">
									<div class="checkout__input">
										<p>
											Product_name<span>*</span>
										</p>
										<input type="text" name=product_name>
									</div>
								</div>
								<div class="col-lg-6">
									<div class="checkout__input">
										<p>
											Height<span>*</span>
										</p>
										<input type="text" name="height" placeholder="(cm)">
									</div>
								</div>
								<div class="col-lg-6">
									<div class="checkout__input">
										<p>
											Wearing_size<span>*</span>
										</p>
										<input type="text" name="wearing_size" placeholder="XS~XL">
									</div>
								</div>
							</div>
							<div class="checkout__input">
								<p>
									Body_type<span>*</span>
								</p>
								<input type="text" name="body_type" placeholder="tall,thin,Lower body obesity..">
							</div>
							<div class="checkout__input">
								<p>
									Brand<span>*</span>
								</p>
								<input type="text" name="brand">
							</div>
							<div class="checkout__input">
								<p>
									Review<span>*</span>
								</p>
								<textarea id="review" name="review" placeholder="Review.."
									style="height: 200px"></textarea>
							</div>
							<p>
								mainImg: <input type="file" name="repImg">
							</p>

						</div>
					</div>

				</form>
			</div>
		</div>
	</section>
	<!-- Checkout Section End -->

	<!-- Footer Section Begin -->
    <%@ include file="../inc/footer.jsp" %>
    <!-- Footer Section End -->

   <%@ include file="../inc/bottom.jsp" %>
</body>

</html>
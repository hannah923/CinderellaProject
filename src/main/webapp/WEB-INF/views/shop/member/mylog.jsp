<%@page import="com.project.cinderella.model.domain.PhotoReview"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%
	List<PhotoReview> photoReviewList = (List)request.getAttribute("photoReviewList");
%>
<!DOCTYPE html>
<html>

<head>

	<meta charset="UTF-8">
    <meta name="description" content="Male_Fashion Template">
    <meta name="keywords" content="Male_Fashion, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Male-Fashion | Template</title>
	<%@ include file="../inc/header.jsp" %>
	
    <!-- Google Font -->
    <style>
	/* The Modal (background) */
	.searchModal {
		display: none; /* Hidden by default */
		position: fixed; /* Stay in place */
		z-index: 10; /* Sit on top */
		left: 0;
		top: 0;
		width: 100%;
		height: 100%;
		overflow: auto; /* Enable scroll if needed */
		background-color: rgb(0,0,0); /* Fallback color */
		background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
	}
	/* Modal Content/Box */
	.search-modal-content {
		background-color: #fefefe;
		margin: 15% auto; /* 15% from the top and centered */
		padding: 20px;
		border: 1px solid #888;
		width: 380px; /* Could be more or less, depending on screen size */
		height:215px;
		
	}
	.photoreviewmain{
		width:30%;
		
		float: left;
	}
	.listimg{
		width: 50%;
		height: 9%;
	
	}
</style>
</head>

<body>
	<%@ include file="../inc/top.jsp" %>
	
	
 	<input type="hidden" id ="user_id"  value= "<%=member.getUser_id() %>"/>
    <!-- Header Section End -->

    <!-- Breadcrumb Section Begin -->
    <section class="breadcrumb-blog set-bg" data-setbg="/cinderella/resources/img/breadcrumb-bg.jpg">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h2>My Photo Review</h2>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->

    <!-- Blog Section Begin -->
						    <%if(session.getAttribute("member")==null){ //세션에 담겨진 데이터가 없다면%>
							<div style="position:relative; left: 200px; top: 20px;"><a href="/cinderella/shop/member/loginForm" class="primary-btn" >Login for Regist
							<span class="arrow_right"></span></a></div>
                             <%}else{ %>
                               <div style="position:relative; left: 200px; top: 20px;"><a href="/cinderella/shop/photoreview/list" class="primary-btn" >Go to Photo Review
							<span class="arrow_right"></span></a></div>
                                <%} %>

		
		<!-- ------------------------modal start-------------------------- -->
	<div id="modal" class="searchModal">
		<div class="search-modal-content" >
		<div class="allowimg">
			<div class="photoreviewmain">
				<img alt="empty"  class="modalimg" width="116" height="153" >
			</div>
			<div class="notallowimg">
				<div id="photoreview1">
					<h3 id="product_name">MODAL</h3>
				</div>
				<div class="photoreview2">
					<h5 class="product_height">Modal창 테스트입니다.</h5>
				</div>
				<div class="photoreview3">
					<h5 class="product_brand">Modal창 테스트입니다.</h5>
				</div>
				<div class="photoreview4" style="position: relative; ">
					<h6 class="product_review" style="position: relative; overflow: scroll;">Modal창 테스트입니다.</h6>
				</div>
				<div id="photoreview5" style="display: none;">
					<h7 id="product_id">MODAL</h7>
				</div>
			</div>
		</div>
		<!-- <hr style="position: absolute; top:100px;"> -->
		<div style="position:absolute; padding-left: 10px;">
				<div  style=" cursor: pointer; background-color: #DDDDDD; text-align: center; float: left; margin-right: 15px; margin-left:100px;"
					onclick="showDetail()">
					<span style="font-size: 13pt;"><div class="primary-btn" >제품보기</div></span>
				</div>
				
				<div  style=" cursor: pointer; background-color: #DDDDDD; text-align: center; float: left;"
					>
					<span style="font-size: 13pt;"><div class="primary-btn" onClick="closeModal();">닫기</div></span>
				</div>
		</div>
			
			<!-- 
			<div
				style="cursor: pointer; background-color: #DDDDDD; text-align: center; padding-bottom: 10px; padding-top: 10px;"
				onClick="closeModal();">
				<span style="font-size: 13pt;">
				</span>
			</div>
			 -->
		</div>
	</div>

		<!-- ------------------------modal end-------------------------- -->
	<section class="blog spad">
        <div class="container">
            <div id="container_rv" class="row">
            <%for(int i=0;i<photoReviewList.size();i++){ %>
            <%PhotoReview photoReview = photoReviewList.get(i);%>
           
                <div class="col-lg-4 col-md-6 col-sm-6">
                    <div class="blog__item">
                       <div class="blog__item__pic" data-setbg="/cinderella/resources/data/previewbasic/<%=photoReview.getPhotoreview_id() %>.<%=photoReview.getFilename()%>" style='background-image: url("/cinderella/resources/data/previewbasic/<%=photoReview.getPhotoreview_id() %>.<%=photoReview.getFilename()%>"); background-size: 360px 270px;'></div>
						<div class="blog__item__text">
                            <span><img src="/cinderella/resources/img/icon/calendar.png" alt="" id="date-result"><%=photoReview.getRegdate() %></span>
                            <h5><%=photoReview.getProduct_name()%></h5>
                            <%if(photoReview.getReview().length()<10){ %>
                              <h5><%=photoReview.getReview()%>...</h5>
                              <%}else{ %>
                            <h5><%=photoReview.getReview().substring(0,10)%>...</h5>
                            <%} %>
                            <div class="readMore" style="float: left; margin-right: 30px; cursor:pointer;" >
                            <a  data-target="#modal"
                              data-photoreview_id="<%=photoReview.getPhotoreview_id()%>"
                   			  data-product_name="<%=photoReview.getProduct_name()%>" 
                   			  data-product_id="<%=photoReview.getProduct_id()%>" 
                              data-height="<%=photoReview.getHeight() %>" 
                              data-brand="<%=photoReview.getBrand() %>"  
                              data-review="<%=photoReview.getReview() %>"
                              data-filename="<%=photoReview.getFilename() %>">Read More</a>
                        </div>
                        	 <%if(session.getAttribute("member")!=null){ //세션에 담겨진 데이터가 있다면%>
                        	 	<%if(member.getUser_id().equals(photoReview.getUser_id())){ %>
                        			<div id="<%=photoReview.getPhotoreview_id() %>" class = "delX" style="float: left; cursor:pointer;">X</div>
                        		<%} %>
                        	<%} %>
                        </div>
                    </div>
                </div>
                <%} %>
            </div>
        </div>
    </section>
    <!-- Blog Section End -->

    <!-- Footer Section Begin -->
    <%@ include file="../inc/footer.jsp" %>
    <!-- Footer Section End -->

       <%@ include file="../inc/bottom.jsp" %>
       <script>
 $(function(){
    		$(".readMore").on("click", function(e){
    			
    			e.preventDefault();
    		 	$("#modal").show();
    		 	
    			var obj = e.target;
    			console.log(obj);
    			
    		 	var photoreview_id=$(obj).data("photoreview_id");
    		 	var product_id=$(obj).data("product_id");
    			var product_name=$(obj).data("product_name");
    			var height=$(obj).data("height");
    			var brand=$(obj).data("brand");
    			var review=$(obj).data("review");
    			var filename=$(obj).data("filename");
    			
//     			console.log(photoreview_id);
//     			console.log(product_id);
//     			console.log(product_name);
//     			console.log(height);
//     			console.log(brand);
//     			console.log(review);
//     			console.log(filename); 
    			//퀵뷰창의 상품 정보에 출력 
    			
    			//이미지
    			$(".photoreviewmain img").attr({
    					src:"/cinderella/resources/data/previewbasic/"+photoreview_id+"."+filename
		});
		//제품명
		
		$("#photoreview1 #product_name").html(product_name);
		$("#photoreview5 #product_id").html(product_id);
		$(".photoreview2 .product_height").html("height_"+height);
		$(".photoreview3 .product_brand").html("brand_"+brand);
		$(".photoreview4 .product_review").html("review:"+review);
	});
	$('.delX').click(function(){
		del(this.id,$('#user_id').val());
	});
});
	
	function showDetail(){
		
		 var product_name = document.getElementById('product_name').innerText;
		 var product_id= document.getElementById('product_id').innerText;
		 location.href="/cinderella/shop/product/detail?product_id="+product_id+"&product_name="+product_name;
		  
	}
	
	function closeModal() {
		 
		$('.searchModal').hide();
	}
	function del(id,user_id){
		location.href="/cinderella/shop/photoreview/deleteandshowbyid?photoreView_id="+id+"&user_id="+user_id;
		<%-- location.href="/cinderella/shop/member/mylog?user_id="+<%=member.getUser_id()%>; --%>
	}
	<%--  function del(id) {
			url:"/cinderella/shop/photoreview/delete?photoreView_id="+id
		 $.ajax({
			success : function() {
				
				location.href="/cinderella/shop/member/mylog?user_id="+<%=member.getUser_id()%>;
				//alert("삭제 완료");
				/* console.log(data);
				$('#container_rv').empty();
				var tag = '';
				for(var i =0; i < data.length; i++){
					data[i].photoreview_id
				
					tag += '<div class="col-lg-4 col-md-6 col-sm-6">';
                    tag+='<div class="blog__item">';
               
                    tag+=  '<div class="blog__item__pic" data-setbg="/cinderella/resources/data/previewbasic/'+data[i].photoreview_id+'.'+data[i].filename+'"';
                    tag+= 'style=\'background-image: url("/cinderella/resources/data/previewbasic/'+data[i].photoreview_id+'.'+data[i].filename+'"); background-size: 360px 270px;\'></div>';
                    	
                    tag+='<div class="blog__item__text">';
                    tag+='<span><img src="/cinderella/resources/img/icon/calendar.png" alt="" id="date-result">'+data[i].regdate+'</span>';
                    tag+='<h5>'+data[i].product_name+'</h5>';
                    	if(data[i].review.length<10){
                            tag+='<h5>'+data[i].review+'...</h5>';
                              }else{
                    tag+='<h5>'+data[i].review.substring(0,10)+'...</h5>';
                            } 
                    tag+='<div class=\"readMore\" style="float: left; margin-right: 30px; cursor:pointer;" >';
                    tag+='<a  data-target=\"#modal\"';
                         	tag +="data-photoreview_id="+data[i].photoreview_id;
                         	tag+="data-product_name="+data[i].product_name; 
                         	tag+="data-product_id="+data[i].product_id; 
                         	tag+="data-height="+data[i].height; 
                         	tag+="data-brand="+data[i].brand;  
                         	tag+="data-review="+data[i].review;
                         	tag+="data-filename="+data[i].filename+">Read More</a>";
                         	tag+='</div>';
                        	tag+="<div id="+data[i].photoreview_id+" class = \'delX\' style='float: left; cursor:pointer;'>X</div>";
                        	tag+="</div>";
                        tag+="</div>";
                    tag+="</div>";
				}
				console.log(tag);
				$('#container_rv').html(tag); */
			},
			error : function(){
				alert('삭제중 에러가 발생하였습니다.');
			}   
		});  
	}  --%>
	
</script>
</body>

</html>
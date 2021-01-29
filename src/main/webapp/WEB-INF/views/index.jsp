<%@page import="com.project.cinderella.common.Formatter"%>
<%@page import="com.project.cinderella.model.domain.Product"%>

<%@page import="com.project.cinderella.model.domain.PhotoReview"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%
	List<PhotoReview> photoReviewList = (List)request.getAttribute("photoReviewList");
	List<Product> productListByHit  = (List)request.getAttribute("productListByHit");
	List<Product> productListByNew  = (List)request.getAttribute("productListByNew");

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

    <%@ include file="./shop/inc/header.jsp" %>
</head>

<body>
    <%@ include file="./shop/inc/top.jsp" %>
    <!-- Header Section End -->

    <!-- Hero Section Begin -->
    <section class="hero">
        <div class="hero__slider owl-carousel">
            <div class="hero__items set-bg" data-setbg="/cinderella/resources/img/hero/hero-1.jpg">
                <div class="container">
                    <div class="row">
                        <div class="col-xl-5 col-lg-7 col-md-8">
                            <div class="hero__text">
                                <h6>Hot Event</h6>
                                <h2>포토리뷰 작성하고 이벤트 참여하기</h2>
                                <p>포토리뷰를 작성하시면 추첨을 통해 할인권이 발송됩니다</p>
                                <a  href="/cinderella/shop/photoreview/list" class="primary-btn">포토리뷰 작성하러 가기<span class="arrow_right"></span></a>
                                <div class="hero__social">
                                    <a href="#"><i class="fa fa-facebook"></i></a>
                                    <a href="#"><i class="fa fa-twitter"></i></a>
                                    <a href="#"><i class="fa fa-pinterest"></i></a>
                                    <a href="#"><i class="fa fa-instagram"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="hero__items set-bg" data-setbg="/cinderella/resources/img/hero/hero-2.jpg">
                <div class="container">
                    <div class="row">
                        <div class="col-xl-5 col-lg-7 col-md-8">
                            <div class="hero__text">
                                <h6>Summer Collection</h6>
                                <h2>Fall - Winter Collections 2030</h2>
                                <p>A specialist label creating luxury essentials. Ethically crafted with an unwavering
                                commitment to exceptional quality.</p>
                                <a href="#" class="primary-btn">Shop now <span class="arrow_right"></span></a>
                                <div class="hero__social">
                                    <a href="#"><i class="fa fa-facebook"></i></a>
                                    <a href="#"><i class="fa fa-twitter"></i></a>
                                    <a href="#"><i class="fa fa-pinterest"></i></a>
                                    <a href="#"><i class="fa fa-instagram"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Hero Section End -->

    <!-- Banner Section Begin -->
    <section class="banner spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-7 offset-lg-4">
                    <div class="banner__item">
                        <div class="banner__item__pic">
                            <img src="/cinderella/resources/img/banner/banner-1.jpg" alt="">
                        </div>
                        <div class="banner__item__text">
                            <h2>Clothing Collections 2030</h2>
                            <a href="#">Shop now</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-5">
                    <div class="banner__item banner__item--middle">
                        <div class="banner__item__pic">
                            <img src="/cinderella/resources/img/banner/banner-2.jpg" alt="">
                        </div>
                        <div class="banner__item__text">
                            <h2>Accessories</h2>
                            <a href="#">Shop now</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-7">
                    <div class="banner__item banner__item--last">
                        <div class="banner__item__pic">
                            <img src="/cinderella/resources/img/banner/banner-3.jpg" alt="">
                        </div>
                        <div class="banner__item__text">
                            <h2>Shoes Spring 2030</h2>
                            <a href="#">Shop now</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Banner Section End -->

    <!-- Product Section Begin -->
    <section class="product spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <ul class="filter__controls">
                     <!--    <li class="active" data-filter="*">Most Viewed</li> -->
                        <li class="acrive" data-filter=".hot-sales">Most Viewed</li>
                        <li data-filter=".new-arrivals">New Arrivals</li>
                    </ul>
                </div>
            </div>
            
                  <!-- 새로 등록된 상품 리스트 - New Arrivals -->
            <div class="row product__filter">
                 <%for(int i=0; i<3; i++){ %>
                 <%Product product = productListByNew.get(i); %>
                <div class="col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix new-arrivals">
                    <div class="product__item">
                        <div class="product__item__pic set-bg" >
                           <img src="/cinderella/resources/data/basic/<%=product.getProduct_id()%>.<%=product.getFilename()%>" width="350px" height="260px">
                            <ul class="product__hover">
                                <li><a href="#"><img src="/cinderella/resources/img/icon/heart.png" alt=""></a></li>
                                <li><a href="#"><img src="/cinderella/resources/img/icon/compare.png" alt=""> <span>Compare</span></a> </li>
                                <li><a href="/cinderella/shop/product/detail?product_id=<%=product.getProduct_id()%>"><img src="/cinderella/resources/img/icon/search.png" alt=""></a><span>detail</span></li>
                            </ul>
                        </div>
                        <div class="product__item__text">
                            <h6><%=product.getProduct_name() %>  (<%=product.getHit() %>)</h6>
                            <a href="/cinderella/shop/product/detail?product_id=<%=product.getProduct_id()%>" class="add-cart">+ Add To Cart</a>
                            <div class="rating">
                                <i class="fa fa-star-o"></i>
                                <i class="fa fa-star-o"></i>
                                <i class="fa fa-star-o"></i>
                                <i class="fa fa-star-o"></i>
                                <i class="fa fa-star-o"></i>
                            </div>
                            <h5><%=Formatter.getCurrency(product.getPrice())%></h5>
                            <div class="product__color__select">
                                <label for="pc-4">
                                    <input type="radio" id="pc-4">
                                </label>
                                <label class="active black" for="pc-5">
                                    <input type="radio" id="pc-5">
                                </label>
                                <label class="grey" for="pc-6">
                                    <input type="radio" id="pc-6">
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
              <%} %>
             
                
            
            <!-- 조회수 상품 리스트 - Most Viewed -->
            		<%for(int i=0; i<3; i++){ %>
                 <%Product product = productListByHit.get(i); %>
                <div class="col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix hot-sales">
                    <div class="product__item">
                        <div class="product__item__pic set-bg" >
                           <img src="/cinderella/resources/data/basic/<%=product.getProduct_id()%>.<%=product.getFilename()%>" width="350px" height="260px">
                            <ul class="product__hover">
                                <li><a href="#"><img src="/cinderella/resources/img/icon/heart.png" alt=""></a></li>
                                <li><a href="#"><img src="/cinderella/resources/img/icon/compare.png" alt=""> <span>Compare</span></a> </li>
                                <li><a href="/cinderella/shop/product/detail?product_id=<%=product.getProduct_id()%>"><img src="/cinderella/resources/img/icon/search.png" alt=""></a><span>detail</span></li>
                            </ul>
                        </div>
                        <div class="product__item__text">
                            <h6><%=product.getProduct_name() %>  (<%=product.getHit() %>)</h6>
                            <a href="/cinderella/shop/product/detail?product_id=<%=product.getProduct_id()%>" class="add-cart">+ Add To Cart</a>
                            <div class="rating">
                                <i class="fa fa-star-o"></i>
                                <i class="fa fa-star-o"></i>
                                <i class="fa fa-star-o"></i>
                                <i class="fa fa-star-o"></i>
                                <i class="fa fa-star-o"></i>
                            </div>
                            <h5><%=Formatter.getCurrency(product.getPrice())%></h5>
                            <div class="product__color__select">
                                <label for="pc-4">
                                    <input type="radio" id="pc-4">
                                </label>
                                <label class="active black" for="pc-5">
                                    <input type="radio" id="pc-5">
                                </label>
                                <label class="grey" for="pc-6">
                                    <input type="radio" id="pc-6">
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
              	<%} %>
             	</div>
            </div>
        </section>
    <!-- Product Section End -->


    <!-- Instagram Section Begin -->
    <section class="instagram spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-8">
                    <div class="instagram__pic">
                        <div class="instagram__pic__item set-bg" data-setbg="/cinderella/resources/img/instagram/instagram-1.jpg"></div>
                        <div class="instagram__pic__item set-bg" data-setbg="/cinderella/resources/img/instagram/instagram-2.jpg"></div>
                        <div class="instagram__pic__item set-bg" data-setbg="/cinderella/resources/img/instagram/instagram-3.jpg"></div>
                        <div class="instagram__pic__item set-bg" data-setbg="/cinderella/resources/img/instagram/instagram-4.jpg"></div>
                        <div class="instagram__pic__item set-bg" data-setbg="/cinderella/resources/img/instagram/instagram-5.jpg"></div>
                        <div class="instagram__pic__item set-bg" data-setbg="/cinderella/resources/img/instagram/instagram-6.jpg"></div>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="instagram__text">
                        <h2>Instagram</h2>
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut
                        labore et dolore magna aliqua.</p>
                        <h3>#Male_Fashion</h3>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Instagram Section End -->

    <!-- Latest Blog Section Begin -->
    <section class="latest spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title">
                        <span>Latest News</span>
                        <h2>Photo Reviews</h2>
                    </div>
                </div>
            </div>
               <!--포토리뷰출력  -->
            <div class="row">
               <%if(photoReviewList.size()>=3){ %>
					<%for (int i = 0; i < 3; i++) {%>
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
                          </div>
                   		</div>
                    </div>
                </div>
					<%}%>
				<%}else{ %>
					<%for (int i = photoReviewList.size()-1; i >= 0; i--) {%>
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
                          </div>
                   		</div>
                    </div>
                </div>
						<%}%>
				<%} %>
            </div>
				<!--포토리뷰출력  -->
        </div>
    </section>
    <!-- Latest Blog Section End -->

    <!-- Footer Section Begin -->
   <%@ include file="./shop/inc/footer.jsp" %>
    <!-- Footer Section End -->

    <!-- Search Begin -->
    <div class="search-model">
        <div class="h-100 d-flex align-items-center justify-content-center">
            <div class="search-close-switch">+</div>
            <form class="search-model-form">
                <input type="text" id="search-input" placeholder="Search here.....">
            </form>
        </div>
    </div>
    <!-- Search End -->

    <!-- Js Plugins -->
    <script src="/cinderella/resources/js/jquery-3.3.1.min.js"></script>
    <script src="/cinderella/resources/js/bootstrap.min.js"></script>
    <script src="/cinderella/resources/js/jquery.nice-select.min.js"></script>
    <script src="/cinderella/resources/js/jquery.nicescroll.min.js"></script>
    <script src="/cinderella/resources/js/jquery.magnific-popup.min.js"></script>
    <script src="/cinderella/resources/js/jquery.countdown.min.js"></script>
    <script src="/cinderella/resources/js/jquery.slicknav.js"></script>
    <script src="/cinderella/resources/js/mixitup.min.js"></script>
    <script src="/cinderella/resources/js/owl.carousel.min.js"></script>
    <script src="/cinderella/resources/js/main.js"></script>
</body>

</html>
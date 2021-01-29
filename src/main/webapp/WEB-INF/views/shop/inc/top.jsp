<%@page import="com.project.cinderella.model.domain.Member"%>
<%@page import="com.project.cinderella.model.domain.SubCategory"%>
<%@page import="com.project.cinderella.model.domain.TopCategory"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%
   List<TopCategory> topList = (List)request.getAttribute("topList");
   Member member = (Member)session.getAttribute("member");
%>
    <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>

    <!-- Offcanvas Menu Begin -->
    <div class="offcanvas-menu-overlay"></div>
    <div class="offcanvas-menu-wrapper">
        <div class="offcanvas__option">
            <div class="offcanvas__links">
                <a href="#">Sign in</a>
                <a href="#">FAQs</a>
            </div>
            <div class="offcanvas__top__hover">
                <span>Usd <i class="arrow_carrot-down"></i></span>
                <ul>
                    <li>USD</li>
                    <li>EUR</li>
                    <li>USD</li>
                </ul>
            </div>
        </div>
        <div class="offcanvas__nav__option">
            <a href="#" class="search-switch"><img src="/cinderella/resources/img/icon/search.png" alt=""></a>
            <a href="#"><img src="/cinderella/resources/img/icon/heart.png" alt=""></a>
            <a href="#"><img src="/cinderella/resources/img/icon/cart.png" alt=""> <span>0</span></a>
            <div class="price">$0.00</div>
        </div>
        <div id="mobile-menu-wrap"></div>
        <div class="offcanvas__text">
            <%if(session.getAttribute("member")==null){ //세션에 담겨진 데이터가 없다면%>
                  <p>로그인이 필요합니다.</p>
            <%} else {%>
                  <p><%=member.getName() %> 님 환영합니다.</p>
                  
            <%} %>
        </div>
    </div>
    <!-- Offcanvas Menu End -->

    <!-- Header Section Begin -->
    <header class="header">
        <div class="header__top">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6 col-md-7">
                        <div class="header__top__left">
                             <%if(session.getAttribute("member")==null){ //세션에 담겨진 데이터가 없다면%>
                              <p>로그인이 필요합니다.</p>
                        <%} else {%>
                              <p><%=member.getName() %> 님 환영합니다.</p>
                              
                        <%} %>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-5">
                        <div class="header__top__right">
                            <div class="header__top__links">
                                
                                <%if(session.getAttribute("member")==null){ //세션에 담겨진 데이터가 없다면%>
                                <a href="/cinderella/shop/member/registForm">Sign Up</a>
                                  <a href="/cinderella/shop/member/loginForm">Sign In</a>
                                <%}else{ %>
                                   <a href="/cinderella/shop/member/logout">Sign Out</a>
                                <%} %>

                             
                            </div>
                            <%if(session.getAttribute("member")!=null){ //세션에 담겨진 데이터가 있다면%>
                            <div class="header__top__hover">
                                <span>MyPage <i class="arrow_carrot-down"></i></span>
                                <ul>
                                    <li><a href="/cinderella/shop/member/userinfo">User info</a></li>
                                   <li><a href="/cinderella/shop/member/mylog?user_id=<%=member.getUser_id()%>">MyLog</a></li>
                                 </ul>
                            </div>
                            <%}%>
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-3">
                    <div class="header__logo">
                        <a href="/cinderella/"><img src="/cinderella/resources/img/MainLogo.png" height="50px" alt=""></a>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6">
                    <nav class="header__menu mobile-menu">
                        <ul>
                            <li class="active"><a href="/cinderella/">Home</a></li>
                            <li><a href="#">Product</a>
                               <ul class="dropdown">
                                  <%for(int i=0; i<topList.size(); i++){ %>
                                  <%TopCategory topCategory = topList.get(i); %>
                                     <li><a href="/cinderella/shop/product/list?topcategory_id=<%=topCategory.getTopcategory_id()%>"><%=topCategory.getTopcategory_name()%></a></li>

                                    <%} %> 
                               </ul> 
                            </li>
                            <%if(session.getAttribute("member")==null){ //세션에 담겨진 데이터가 없다면%>
                                <li><a href="javascript:alert('로그인이 필요한 서비스입니다')" >Shopping Cart</a></li>
                            <%} else{%>
                                <li><a href="/cinderella/shop/cart/list">Shopping Cart</a></li>
                            <%} %>
                            <li><a href="/cinderella/shop/photoreview/list">PhotoReview</a></li>
                        </ul>
                    </nav>
                </div>
                <div class="col-lg-3 col-md-3">
                    <div class="header__nav__option">
                        <a href="#" class="search-switch"><img src="/cinderella/resources/img/icon/search.png" alt=""></a>
                        <a href="#"><img src="/cinderella/resources/img/icon/heart.png" alt=""></a>
                        <a href="#"><img src="/cinderella/resources/img/icon/cart.png" alt=""> <span>0</span></a>
                        <div class="price">$0.00</div>
                    </div>
                </div>
            </div>
            <div class="canvas__open"><i class="fa fa-bars"></i></div>
        </div>
    </header>
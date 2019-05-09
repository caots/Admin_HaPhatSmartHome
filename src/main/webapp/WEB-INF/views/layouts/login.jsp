<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../library/taglib.jsp" %>
<link href="https://image.bksoftwarevn.com/data/public/resources/img/haphatsmarthome/logo-title.png" rel="shortcut icon">

<html lang="en">
<head>
    <title>login</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <%--===================================CSS =======================================--%>
    <%@include file="../../library/library_css.jsp" %>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <link type="text/css" href="resources/css/login.css " rel="stylesheet">

</head>
<div class="img-background" style="background-image: url(resources/img/login/imageLogin.png)">
    <div class="container">
        <div class="row">
            <div class="col-8 col-sm-7 col-md-6 col-lg-5 img-logo">
                    <img src="resources/img/login/bksoftwareLogo.png" alt="" class="img-fluid">
            </div> <!--END_BACKGROUND-->
        </div>
        <div class="row">
            <div class="col-7 col-sm-6 col-md-5 col-lg-4 form-login">
                <div class="title" style="color: #ffff00">LOGIN HP</div>

                    <div class="from-input">
                        <i class="fas fa-user-circle"></i>
                        <input type="text" id="username" class="input-login" name="username" placeholder="username">
                    </div><!--END_FROM_INPUT-->
                    <div class="from-input">
                        <i class="fas fa-lock"></i>
                        <input type="password" id="password" class="input-login" name="password" placeholder="password">
                    </div><!--END_FROM_INPUT-->
                    <a href="" class="forgot">Forgot Password?</a>
                    <div class="btn-login">
                        <button name="submit" type="submit" class="btn">LOGIN</button>
                    </div>
            </div>
        </div>
    </div>
</div>
<body>
<!----------------------Script------------------------------------ -->
<%@include file="../../library/library_js.jsp" %>
</body>
<script src="resources/js/login.js"></script>

</html>


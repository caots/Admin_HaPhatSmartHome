<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../library/taglib.jsp" %>
<html lang="en">
<head>
    <title><tiles:getAsString name="title"/></title>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="resources/js/ajax/common.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<%--=================================== CSS =======================================--%>
    <%@include file="../../library/library_css.jsp" %>
    <link href="https://image.bksoftwarevn.com/data/public/resources/img/haphatsmarthome/logo-title.png" rel="shortcut icon">
</head>
<body>

<div class="package">

    <tiles:insertAttribute name="menu"/>

    <div class="col-7 col-sm-8 col-md-9 col-lg-9 col-xl-10 display-main">

        <tiles:insertAttribute name="header"/>


        <div class="main set-height">

            <div class="container body-main ">
                <tiles:insertAttribute name="body"/>
            </div>
            <!-- FOOTER -->
            <div id="footer">
                BKSoftware - 2019
            </div>

        </div>

    </div>
</div>

<!----------------------Script------------------------------------ -->
<%@include file="../../library/library_js.jsp" %>
</body>
</html>

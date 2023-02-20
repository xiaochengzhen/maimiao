<%--
  Created by IntelliJ IDEA.
  User: zengjin
  Date: 2020-6-22
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<% request.setAttribute("basePath",request.getContextPath()); %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>登录页面——国际化</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/floating-labels/">

    <!-- Bootstrap core CSS -->
    <link href="${basePath}/assets/dist/css/bootstrap.css" rel="stylesheet">

    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>
    <!-- Custom styles for this template -->
    <link href="${basePath}/assets/dist/css/floating-labels.css" rel="stylesheet">
</head>
<body>
<form:form class="form-signin" action="${basePath}/login" method="post" modelAttribute="user">
    <div class="text-center mb-4">
        <img class="mb-4" src="${basePath}/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
        <h1 class="h3 mb-3 font-weight-normal"><spring:message code="login"></spring:message></h1>
        <p><spring:message code="welcome"></spring:message></p>
        <div class="row">
            <a class="col-md-6" href="${basePath}/i18n/zh_CN">中文</a>
            <a class="col-md-6" href="${basePath}/i18n/en_US">English</a>
        </div>

        <div class="row">
            <a class="col-md-6" href="${basePath}/i18n/?locale=zh_CN">中文_拦截器</a>
            <a class="col-md-6" href="${basePath}/i18n/?locale=en_US">English_拦截器</a>
        </div>
    </div>

    <div class="form-label-group">
        <input type="input" id="inputEmail" name="birthday" class="form-control" placeholder="<spring:message code="birthday"></spring:message>" autofocus>
        <label for="inputEmail"><spring:message code="birthday"></spring:message></label>
        <form:errors path="birthday"></form:errors>
    </div>
    <div class="form-label-group">
        <input type="input" id="inputEmail" name="email" class="form-control" placeholder="<spring:message code="email"></spring:message>" autofocus>
        <label for="inputEmail"><spring:message code="email"></spring:message></label>
        <form:errors path="email"></form:errors>
    </div>

    <div class="form-label-group">
        <input type="password"id="inputPassword" name="password" class="form-control" placeholder="<spring:message code="password"></spring:message>" >
        <label for="inputPassword"><spring:message code="password"></spring:message></label>
        <form:errors path="password"></form:errors>
    </div>

    <div class="checkbox mb-3">
        <label>
            <input type="checkbox" value="remember-me"> <spring:message code="remember_me"></spring:message>
        </label>
    </div>
    ${errMsg}
    <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="login"></spring:message></button>
    <p class="mt-5 mb-3 text-muted text-center">&copy; 2017-2020</p>
</form:form>
</body>
</html>

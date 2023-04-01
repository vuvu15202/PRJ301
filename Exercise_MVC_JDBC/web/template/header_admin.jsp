<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Index</title>
    <%
        String path = request.getContextPath();
    %>
        
        <link href="<%=path%>/css/style.css" rel="stylesheet"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
</head>
<body>
    <div id="container">
        <div id="header">
            <div id="logo-admin">
                Ecommerce Admin
            </div>
            <div id="banner-admin">
                <ul>
                    <li><a href="<%=path%>/account/login">SignOut</a></li>
                </ul>
            </div>
        </div>
        <div id="content">
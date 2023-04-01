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
    </head>
    <body>
        <div id="container">
            <div id="header">
                <div id="logo">
                    <a href="<%=path%>/index?isDefault=1"><img src="<%=path%>/img/logo.png"/></a>
                </div>
                <div id="product-title-2" style="width: 55%;">
                                <form action="index" method="POST">
                                    <input value="${sampleSession1}" id="search" type="text" name="txtSearch1" placeholder="Enter product name to search"/>
                                    <input type="submit" value="Search"/>
                                </form>
                </div>
                <div id="banner">
                    <ul>
                        <li><a href="<%=path%>/cart">Cart: ${numberOfItemInCart}</a></li>
                        
                        <c:choose>
                            <c:when test="${AccSession==null}">
                                <li><a href="<%=path%>/account/signin">SignIn</a></li>
                                <li><a href="<%=path%>/account/signup">SignUp</a></li>
                            </c:when> 
                             
                            <c:otherwise>
                                <li><a href="<%=path%>/account/profile">Profile</a></li>
                                <li><a href="<%=path%>/account/signin">SignOut</a></li>
                            </c:otherwise>   
                        </c:choose>
   
                    </ul>
                </div>
            </div>
            <div id="content">
<%@include file="template/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="form">
                <div id="form-title">
                    <span style="color: red; font-size: 30px;">Delivery Information</span>
                    
                </div>
                    <c:if test = "${Fail != null}"> <div style="text-align: center;color: red;">${msgFail}</div> </c:if>
                    <c:if test = "${msgExisted != null}"> <div style="text-align: center;color: red;">${msgExisted}</div> </c:if>
                    
                <div id="form-content">
                    <form action="orderAction" method="POST">
                        <c:set var="temp" value="${odTemplate}"></c:set>
                        <label>Ship Name: <span style="color: red;">*</span></label><br/>
                        <input type="text" name="txtShipName" value="${temp.getShipName()}"><br/>
                        <c:if test="${msgCompanyName!=null}"> <span class="msg-error">${msgCompanyName}</span><br/> </c:if>
                        
                        <label>Ship Address: <span style="color: red;">*</span></label><br/>
                        <input type="text" name="txtShipAddress" value="${temp.getShipAddress()}"><br/>
                        <c:if test="${msgContactName!=null}"> <span class="msg-error">${msgContactName}</span><br/> </c:if>
                        
                        <label>Ship City: <span style="color: red;">*</span></label><br/>
                        <input type="text" name="txtShipCity" value="${temp.getShipCity()}"><br/>
                        <c:if test="${msgContactTitle!=null}"> <span class="msg-error">${msgContactTitle}</span><br/> </c:if>
                        
                        <label>Ship Postal Code: <span style="color: red;">*</span></label><br/>
                        <input type="text" name="txtShipPostalCode" value="${temp.getShipPostalCode()}"><br/>
                        <c:if test="${msgAddress!=null}"> <span class="msg-error">${msgAddress}</span><br/> </c:if>
                        
                        <label>Ship Country: <span style="color: red;">*</span></label><br/>
                        <input type="text" name="txtShipCountry" value="${temp.getShipCountry()}"><br/>
                        <c:if test="${msgEmail!=null}"> <span class="msg-error">${msgEmail}</span><br/> </c:if>
                        
                        <input type="submit" value="Order" style="margin-bottom: 30px;"/>
                    </form>
                </div>
            </div>
<%@include file="template/footer.jsp" %>





<!--
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div style="display: flex; justify-content: center; margin-top: 50px;">
            <c:set var="temp" value="${odTemplate}"></c:set>

            <form action="orderAction" method="POST" style="width: 25%;">
                Ship Name: <input type="text" name="txtShipName" value="${temp.getShipName()}"> </br>
                Ship Address: <input type="text" name="txtShipAddress" value="${temp.getShipAddress()}"> </br>
                Ship City: <input type="text" name="txtShipCity" value="${temp.getShipCity()}"> </br>
                Ship Postal Code: <input type="text" name="txtShipPostalCode" value="${temp.getShipPostalCode()}"> </br>
                Ship Country: <input type="text" name="txtShipCountry" value="${temp.getShipCountry()}"> </br>
                <input type="submit" value="Order">
            </form>
        </div>
    </body>
</html>
-->
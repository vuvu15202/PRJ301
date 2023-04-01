<%@include file="template/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="form">
    <h3 style="padding: 20px;">Forgot your account password?</h3>
    <div style="padding: 0px 20px 10px;">
        Please enter the email address registered with us to create a new password. We will send an email to the email address provided and require verification before we can generate a new password
    </div>
    <div id="form-content">
        <form action="../account/forgot" method="post">
            <label>Enter your registered email address<span style="color: red;">*</span></label><br/>
            <input type="text" id="email" name="txtEmail"/><br/>
            
            <c:if test="${msg!=null}"><div style="color: red; text-align: left;">${msg}</div></c:if>
            
            <input type="hidden" id="pass" value="<c:out value="${newPass}"/>">
            <input type="submit" onclick="changePass()" value="GET PASSWORD" style="margin-bottom: 30px;"/><br/>
        </form>
    </div>
</div>

<%@include file="template/footer.jsp" %>
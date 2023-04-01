<%@include file="template/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="form">
    <div id="form-title">
        <span><a href="<%=path%>/account/signup">SIGN UP</a></span>
        <span><a href="<%=path%>/account/signin" style="color: red;">SIGN IN</a></span>
    </div>
    <div id="form-content">
        <div style="color:red; text-align: left">
            <c:if test = "${msg != null}"> <div>${msg}</div> </c:if>
            <c:if test = "${msgChange != null}"> <div style="text-align: center;s">new Password: ${msgChange}</div> </c:if>
            
           
                    
            <!-- comment  <%
            if(request.getAttribute("msg")!=null)
                out.print(request.getAttribute("msg"));
            %> -->
        </div>
        <form action="<%=path%>/account/signin" method="post">
            <label>Email<span style="color: red;">*</span></label><br/>
            <input type="text" name="txtEmail"/><br/>
            
            <span class="msg-error"><c:if test = "${msgEmail != null}"> <div>${msgEmail}</div> </c:if></span>
            <br/>
            <label>Password<span style="color: red;">*</span></label><br/>
            <input type="password" name="txtPass"/><br/>
            
            <span class="msg-error"> <c:if test = "${msgPass != null}"> <div>${msgPass}</div> </c:if></span>
            <br/>
            <div><a href="<%=request.getContextPath()%>/account/forgot">Forgot password?</a></div>
            <input type="submit" value="SIGN IN"/><br/>
            <input type="button" value="FACEBOOK LOGIN" style="background-color: #3b5998;"/><br/>
            <input type="button" value="ZALO LOGIN" style="background-color: #009dff;margin-bottom: 30px;"/>
        </form>
    </div>
</div>

<%@include file="template/footer.jsp" %>
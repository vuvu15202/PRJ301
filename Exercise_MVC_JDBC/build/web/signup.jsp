<%@include file="template/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="form">
                <div id="form-title">
                    <span><a href="<%=request.getContextPath()%>/account/signup" style="color: red;">SIGN UP</a></span>
                    <span><a href="<%=request.getContextPath()%>/account/signin">SIGN IN</a></span>
                </div>
                    <c:if test = "${Fail != null}"> <div style="text-align: center;color: red;">${msgFail}</div> </c:if>
                    <c:if test = "${msgExisted != null}"> <div style="text-align: center;color: red;">${msgExisted}</div> </c:if>
                    
                <div id="form-content">
                    <form action="../account/signup" method="post">
                        <label>Company name<span style="color: red;">*</span></label><br/>
                        <input type="text" name="txtCompanyName" value="${txtCompanyName}"/><br/>
                        <c:if test="${msgCompanyName!=null}"> <span class="msg-error">${msgCompanyName}</span><br/> </c:if>
                        
                        <label>Contact name<span style="color: red;">*</span></label><br/>
                        <input type="text" name="txtContactName"  value="${txtContactName}"/><br/>
                        <c:if test="${msgContactName!=null}"> <span class="msg-error">${msgContactName}</span><br/> </c:if>
                        
                        <label>Contact title<span style="color: red;">*</span></label><br/>
                        <input type="text" name="txtContactTitle"  value="${txtContactTitle}"/><br/>
                        <c:if test="${msgContactTitle!=null}"> <span class="msg-error">${msgContactTitle}</span><br/> </c:if>
                        
                        <label>Address<span style="color: red;">*</span></label><br/>
                        <input type="text" name="txtAddress"  value="${txtAddress}"/><br/>
                        <c:if test="${msgAddress!=null}"> <span class="msg-error">${msgAddress}</span><br/> </c:if>
                        
                        <label>Email<span style="color: red;">*</span></label><br/>
                        <input type="text" name="txtEmail"  value="${txtEmail}"/><br/>
                        <c:if test="${msgEmail!=null}"> <span class="msg-error">${msgEmail}</span><br/> </c:if>
                        
                        <label>Password<span style="color: red;">*</span></label><br/>
                        <input type="password" name="txtPass"  value="${txtPass}"/><br/>
                        <c:if test="${msgPass!=null}"> <span class="msg-error">${msgPass}</span><br/> </c:if>
                        
                        <label>Re-Password<span style="color: red;">*</span></label><br/>
                        <input type="password" name="txtRePass"  value="${txtRePass}"/><br/>
                        <c:if test = "${msgRePass != null}"> <span style="color: red;">${msgRePass}</span> </c:if>
                        
                        <div></div>
                        <input type="submit" value="SIGN UP" style="margin-bottom: 30px;"/>
                    </form>
                </div>
            </div>
<%@include file="template/footer.jsp" %>



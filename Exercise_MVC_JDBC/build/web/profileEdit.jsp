<%@include file="template/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="content-left">
    <form action="../account/profile" method="POST">
        <h3 style="font-weight: normal;">Welcome, ${cusSession.getContactName()}</h3>
        <h3>Account Management</h3>
        <ul>
            <a href="<%=path%>/account/profile"><li>Personal information</li></a>
        </ul>
        <h3>My order</h3>
        <ul>
            <a href="../profile1"><li>All orders</li></a>
            <a href="#"><li>Canceled order</li></a>
        </ul>
</div>

<div id="content-right">
    <div class="path">Personal information</b></div>
    <div class="content-main">
        <div id="profile-content">

            <div class="profile-content-col">
                <div>Company name(*): <br/><input type="text" name="txtCompanyName" value="${cusSession.getCompanyName()}"></div>
                    <c:if test="${msgCompanyName!=null}">${msgCompanyName}</c:if>
                <div>Contact name(*): <br/><input type="text" name="txtContactName" value="${cusSession.getContactName()}"></div>
                    <c:if test="${msgContactName!=null}">${msgContactName}</c:if>
                    <div>
                        <input onclick="editProfile()" type="submit" value="Save"/>
                    <c:if test="${msgExisted!=null}"><div style="color: green;">${msgExisted}</div></c:if> 
                    </div>
                </div>
                <div class="profile-content-col">
                    <div>Contact title(*): <br/><input type="text" name="txtContactTitle" value="${cusSession.getContactTitle()}"></div>
                    <c:if test="${msgContactName!=null}">${msgContactName}</c:if>
                <div>Address(*): <br/><input type="text" name="txtAddress" value="${cusSession.getAddress()}"></div>
                    <c:if test="${msgAdress!=null}">${msgAdress}</c:if>
                </div>
                <div class="profile-content-col">
                    <div>Email(*): <br/><input type="text" name="txtEmail" value="${AccSession.getEmail()}" readonly="true"></div>
                    <c:if test="${msgEmail!=null}">${msgEmail}</c:if>
                    <div>Password(*): <br/><input type="password" name="txtOldPass"  ></div>
                    <c:if test="${msgOldPass!=null}">${msgOldPass}</c:if>
                    <div>New Password: <br/><input type="password" name="txtPass"  ></div>
                    <c:if test="${msgPass!=null}">${msgPass}</c:if>
                    <div>Re Password <br/><input type="password" name="txtRePass"  ></div>
                    <c:if test="${msgRePass!=null}">${msgRePass}</c:if>
                </div>


            </div>
        </div>
    </div>
</form> 

<%@include file="template/footer.jsp"%>
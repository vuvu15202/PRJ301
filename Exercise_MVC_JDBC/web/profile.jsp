<%@include file="template/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="content-left">
    <h3 style="font-weight: normal;">Welcome, ${cusSession.getContactName()}</h3>
    <h3>Account Management</h3>
    <ul>
        <a href="<%=path%>/account/profile"><li>Personal information</li></a>
    </ul>
    <h3>My order</h3>
    <ul>
        <a href="../account/profile1"><li>All orders</li></a>
        <a href="<%=path%>/account/profile2"><li>Canceled order</li></a>
    </ul>
</div>
<div id="content-right">
    <div class="path">Personal information</b></div>
    <div class="content-main">
        <div id="profile-content">
            <div class="profile-content-col">
                <div>Company name: <br/>${cusSession.getCompanyName()}</div>
                <div>Contact name: <br/>${cusSession.getContactName()}</div>
                <div>
                    <input onclick="editProfile()" type="submit" value="Edit info"/> 
                    <c:if test="${msg!=null}"><div style="color: green;">${msg}</div></c:if> 
                    </div>
                </div>
                <div class="profile-content-col">
                    <div>Company title: <br/>${cusSession.getContactTitle()}</div>
                <div>Address: <br/>${cusSession.getAddress()}</div>
            </div>
            <div class="profile-content-col">
                <div>Email: <br/>${AccSession.getEmail()}</div>
            </div>
        </div>
    </div>
</div>

<%@include file="template/footer.jsp"%>
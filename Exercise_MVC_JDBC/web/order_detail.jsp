<%@include file="template/header_admin.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="content-left">
    <ul>
        <a href="dashboard.html"><li>Dashboard</li></a>
        <a href="<%=path%>/orderManage_admin"><li>Orders</li></a>
        <a href="<%=path%>/productManage_admin"><li>Products</li></a>
        <a href="#"><li>Customers</li></a>
    </ul>
</div>
<div id="content-right">
    <div class="path-admin">ORDER DETAIL</b></div>
    <div class="content-main">
        <div id="content-main-dashboard">
            <div>
                <div class="profile-order-title">
                    <c:set var="od" value="${order}"></c:set>
                        <div class="profile-order-title-left">
                            <div>OrderID: ${od.getOrderID()}</div>
                        <div>Order creation date: ${od.getOrderDate()}</div>
                    </div>
                    <div class="profile-order-title-right">
                        <c:choose>
                            <c:when test="${od.getRequiredDate()!=null && od.getShippedDate()!=null}"><span style="color: green;">Completed</span></c:when>
                            <c:when test="${od.getRequiredDate()!=null && od.getShippedDate()==null}"><td style="color: blue;">Pending|<a style="color: orchid" onclick="return cancelOrder()" href="<%=path%>/account/profile1?idCancel=${od.getOrderID()}">Cancel</a></span></c:when>
                            <c:when test="${od.getRequiredDate()==null && od.getShippedDate()==null}"><td style="color: red;">Canceled</td></c:when>
                        </c:choose>

                    </div>
                </div>
                <c:forEach var="odDetails" items="${orderDetailList}">
                    <div class="profile-order-content" style="background-color: white;">
                        <div class="profile-order-content-col1">
                            <a href="<%=path%>/index?idProDetail=${odDetails.getProductID()}"><img src="img/1.jpg" width="100%"/></a>
                        </div>
                        <c:forEach var="p" items="${productList}">
                            <c:if 
                                test="${p.getProductID()==odDetails.getProductID()}"><div class="profile-order-content-col2">${p.getProductName()}</div>
                            </c:if>
                        </c:forEach>
                        <div class="profile-order-content-col3">Quantity: ${odDetails.getQuantity()}</div>
                        <div class="profile-order-content-col4">${odDetails.getUnitPrice()} $</div>
                    </div> 
                </c:forEach>


            </div>
        </div>
    </div>
</div>
<%@include file="template/footer_admin.jsp" %>
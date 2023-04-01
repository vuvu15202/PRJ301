<%@include file="template/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="content-left">
    <h3 style="font-weight: normal;">Welcome, ${AccSession.getEmail()}</h3>
    <h3>Account Management</h3>
    <ul>
        <a href="<%=path%>/account/profile"><li>Personal information</li></a>
    </ul>
    <h3>My order</h3>
    <ul>
        <a href="<%=path%>/account/profile1"><li>All orders</li></a>
        <a href="<%=path%>/account/profile2"><li>Canceled order</li></a>
    </ul>
</div>
<div id="content-right">
    <div class="path">LIST ORDERS</b></div>
    <div class="content-main">
        <div id="profile-content-order">
            <c:if test="${emptyListMsg!=null}"> <div>${emptyListMsg}</div> </c:if>
            <c:forEach var="o" items="${orderList}">
                <div>
                    <div class="profile-order-title">
                        <div class="profile-order-title-left">
                            <div>Order creation date: ${o.getOrderDate()}</div>
                            <c:url value="/account/profile1" var="Edit">
                                <c:param name="id" value="${product.productID}" />
                            </c:url>
                            <div>Order: <a href="<%=path%>/account/profile1?idOdDetail=${o.getOrderID()}">${o.getOrderID()}</a></div>
                        </div>
                        <div class="profile-order-title-right">
                            <c:choose>
                                <c:when test="${o.getRequiredDate()!=null && o.getShippedDate()==null}">
                                    <span style="color: blue;">Pending</span><a onclick="return cancelOrder()" href="<%=path%>/account/profile1?idCancel=${o.getOrderID()}" style="color: red;">|Cancel</a>
                                </c:when>
                                <c:when test="${o.getRequiredDate()!=null && o.getShippedDate()!=null}">
                                    <span style="color: green;">Completed</span>
                                </c:when>
                                <c:when test="${o.getRequiredDate()==null && o.getShippedDate()==null}">
                                    <span style="color: red;">Canceled</span>
                                </c:when>
                                <c:otherwise>

                                </c:otherwise>
                            </c:choose>


                        </div>
                    </div>
                    <c:forEach var="od" items="${orderDetailList}">
                        <c:if test="${o.getOrderID()==od.getOrderID()}">
                            <div class="profile-order-content">
                                <div class="profile-order-content-col1">
                                    <a href="<%=request.getContextPath()%>/index?idProDetail=${od.getProductID()}"><img src="../img/2.jpg" width="100%"/></a>
                                </div>

                                <c:forEach var="p" items="${productList}">
                                    <c:if test="${od.getProductID()==p.getProductID()}"> <div class="profile-order-content-col2">${p.getProductName()}</div> </c:if>
                                </c:forEach>
                                <div class="profile-order-content-col3">Quantity: ${od.getQuantity()}</div>
                                <div class="profile-order-content-col4">${od.getUnitPrice()} $</div>
                            </div>
                        </c:if>

                    </c:forEach>


                </div>
            </c:forEach>

        </div>
    </div>
</div>

<%@include file="template/footer.jsp"%>
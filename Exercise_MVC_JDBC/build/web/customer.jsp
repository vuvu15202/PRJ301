<%@include file="template/header_admin.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="content-left">
    <ul>
        <a href="<%=path%>/dashboard_admin"><li>Dashboard</li></a>
        <a href="<%=path%>/orderManage_admin"><li>Orders</li></a>
        <a href="<%=path%>/productManage_admin"><li>Products</li></a>
        <a href="<%=path%>/customerManage_admin"><li>Customers</li></a>
    </ul>
</div>
<div id="content-right">
    <div class="path-admin">Customer LIST</b></div>
    <div class="content-main">
        <div id="content-main-dashboard">
            <div id="product-title-header">
                <div id="product-title-1" style="width: 25%;">

                </div>
                <div id="product-title-2" style="width: 55%;">
                    <form action="productManage_admin" method="">
                        <input id="s" type="text" name="txtSearch2" placeholder="Enter product name to search"/>
                        <input type="submit" value="Search"/>
                    </form>
                </div>
                <div id="product-title-3" style="width: 20%;">
                    <a href="">Create a new Customer</a>
                    <form action="">
                        <label for="uploads-file">Import .xls or .xlsx file</label>
                        <input type="file" name="file" id="upload-file" />
                    </form>
                </div>
            </div>

            <div id="order-table-admin">
                <table id="orders">
                    <tr>
                        <th>CustomerID</th>  
                        <th>CompanyName</th>
                        <th>ContactName</th>
                        <th>ContactTitle</th>
                        <th>Address</th>
                        <th>CreateDate</th>
                    </tr>
                    <c:forEach items="${listInCurrentPage}" var="c">
                        <tr>
                            <td>${c.getCustomerID()}</td>
                            <td>${c.getCompanyName()}</td>
                            <td>${c.getContactName()}</td>
                            <td>${c.getContactTitle()}</td>
                            <td>${c.getAddress()}</td>
                            <td>${c.getCreateDate()}</td>
                        </tr>
                    </c:forEach>

                </table>
            </div>
            <c:if test="${sessionMsg!=null}"><div>${sessionMsg}</div> </c:if>
                <div id="paging">
                    <div class="pagination">

                    <c:if test="${currentPage>1}">
                        <c:url value="/customerManage_admin" var="paginationPrevous">
                            <c:param name="currentPage" value="${currentPage-1}" />
                        </c:url>
                        <a href="${paginationPrevous}">&laquo;</a>
                    </c:if>

                    <c:forEach begin="1" end="${numberOfPage}" step="1" var="stepValue">
                        <c:choose>
                            <c:when test="${stepValue == currentPage}">
                                <a href="#" class="active">${stepValue}</a>
                            </c:when>
                            <c:otherwise>
                                <c:url value="/customerManage_admin" var="pagination">
                                    <c:param name="currentPage" value="${stepValue}" />
                                </c:url>
                                <a href="${pagination}">${stepValue}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <c:if test="${currentPage<numberOfPage}">
                        <c:url value="/customerManage_admin" var="paginationNext">
                            <c:param name="currentPage" value="${currentPage+1}" />
                        </c:url>
                        <a href="${paginationNext}">&raquo;</a>
                    </c:if>


                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="template/footer_admin.jsp" %>
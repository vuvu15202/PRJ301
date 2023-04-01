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
    <div style="overflow: auto;" id="content-right">
    <div class="path-admin">PRODUCTS LIST</b></div>
    <div class="content-main">
        <div id="content-main-dashboard">
            <div id="product-title-header">
                <div id="product-title-1" style="width: 25%;">
                    <b>Filter by Catetory:</b>
                    <form action="productManage_admin" method="">
                        <select name="dddlCategory">
                            <option value="0">No Filter</option>
                            <c:forEach items="${categoryList}" var="c">
                                <c:choose>
                                    <c:when test="${c.getCategoryID() == categoryIDSession}">
                                        <option value="${c.getCategoryID()}" selected="selected"><c:out value="${c.getCategoryName()}"/></option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${c.getCategoryID()}"><c:out value="${c.getCategoryName()}"/></option>
                                    </c:otherwise>
                                </c:choose>                               
                            </c:forEach>
                        </select>
                        <input type="submit" value="Filter">
                    </form>
                </div>
                <div id="product-title-2" style="width: 55%;">
                    <form action="productManage_admin" method="">
                        <input id="s" type="text" name="txtSearch2" placeholder="Enter product name to search" value="${sampleSession}"/>
                        <input type="submit" value="Search"/>
                    </form>
                </div>
                <div id="product-title-3" style="width: 20%;">
                    <a href="<%=request.getContextPath()%>/createProduct_admin">Create a new Product</a>
                    <form action="">
                        <label for="uploads-file">Import .xls or .xlsx file</label>
                        <input type="file" name="file" id="upload-file" />
                    </form>
                </div>
            </div>

                    <div style="width: 100%; overflow: auto;" id="order-table-admin">
                <table id="orders">
                    <tr>
                        <th>ProductID</th>
                        <th>ProductName</th>
                        <th>UnitPrice</th>
                        <th>Unit</th>
                        <th>UnitsInStock</th>
                        <th>Category</th>
                        <th>Discontinued</th>
                        <th></th>
                    </tr>
                    <c:forEach items="${listInCurrentPage}" var="x">
                        <tr>
                            <td><a href="<%=path%>/index?idProDetail=${x.getProductID()}">${x.getProductID()}</a></td>
                            <td>${x.getProductName()}</td>
                            <td>${x.getUnitPrice()}</td>
                            <td>pieces</td>
                            <td>${x.getUnitsInStock()}</td>
                            <c:forEach var="y" items="${categoryList}">
                                <c:choose>
                                    <c:when test="${x.getCategoryID().equals(y.getCategoryID())}">
                                        <td>${y.getCategoryName()}</td>
                                    </c:when>

                                </c:choose>
                            </c:forEach>
                            <td>${x.isDiscontinued()}</td>
                            <td>
                                <a onclick="return check()" href="<%=path%>/editProduct_admin?id=${x.getProductID()}">Edit</a> &nbsp; | &nbsp; 
                                <a onclick="return check()" href="<%=path%>/deleteProduct_admin?id=${x.getProductID()}">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>

                </table>
            </div>
            <c:if test="${emptyListMsg!=null}"><div style="color: orange; text-align: center;">${emptyListMsg}</div> </c:if>

            <c:if test="${sessionMsg!=null}"><div style="color: orange; text-align: center;">${sessionMsg}</div> </c:if>
                <div id="paging">
                    <div class="pagination">

                    <c:if test="${currentPage>1}">
                        <c:url value="/productManage_admin" var="paginationPrevous">
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
                                <c:url value="/productManage_admin" var="pagination">
                                    <c:param name="currentPage" value="${stepValue}" />
                                </c:url>
                                <a href="${pagination}">${stepValue}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <c:if test="${currentPage<numberOfPage}">
                        <c:url value="/productManage_admin" var="paginationNext">
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
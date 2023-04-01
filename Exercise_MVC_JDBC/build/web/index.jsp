<%@include file="template/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="content-left">
    <h3>CATEGORY</h3>
    <ul>
        <c:forEach items="${categoryList}" var="x">
            <a href="<%=request.getContextPath()%>/index?categoryID=${x.getCategoryID()}"><li>${x.getCategoryName()}</li></a>
                </c:forEach>
    </ul>
</div>
<div id="content-right">
    <c:choose>

        <c:when test="${mode==null}">
            <div class="content-mainSearch">
                <c:forEach items="${proList}" var="x">
                    <div class="productSearch">
                        <a href="<%=path%>/index?idProDetail=${x.getProductID()}"><img src="img/1.jpg" width="100%"/></a>
                        <div class="name"><a href="<%=path%>/index?idProDetail=${x.getProductID()}">${x.getProductName()}</a></div>
                        <div class="price">$${x.getUnitPrice()}</div>
                        <div><a href="<%=path%>/cart?id=${x.getProductID()}">Buy now</a></div>
                    </div>
                </c:forEach>
            </div>
            <div id="paging">
                <div class="pagination">

                    <c:if test="${currentPage>1}">
                        <c:url value="/index" var="paginationPrevous">
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
                                <c:url value="/index" var="pagination">
                                    <c:param name="currentPage" value="${stepValue}" />
                                </c:url>
                                <a href="${pagination}">${stepValue}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <c:if test="${currentPage<numberOfPage}">
                        <c:url value="/index" var="paginationNext">
                            <c:param name="currentPage" value="${currentPage+1}" />
                        </c:url>
                        <a href="${paginationNext}">&raquo;</a>
                    </c:if>


                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="path">Hot</b></div>
            <div class="content-main">

                <c:forEach items="${proList}" var="x">
                    <div class="product">
                        <a href="<%=path%>/index?idProDetail=${x.getProductID()}"><img src="img/1.jpg" width="100%"/></a>
                        <div class="name"><a href="<%=path%>/index?idProDetail=${x.getProductID()}">${x.getProductName()}</a></div>
                        <div class="price">$${x.getUnitPrice()}</div>
                        <div><a href="<%=path%>/cart?id=${x.getProductID()}">Buy now</a></div>
                    </div>
                </c:forEach>

            </div>
            <div class="path">Best Sale</b></div>
            <div class="content-main">
                <c:forEach items="${proList}" var="x">
                    <div class="product">
                        <a href="<%=path%>/index?idProDetail=${x.getProductID()}"><img src="img/1.jpg" width="100%"/></a>
                        <div class="name"><a href="<%=path%>/index?idProDetail=${x.getProductID()}">${x.getProductName()}</a></div>
                        <div class="price">$${x.getUnitPrice()}</div>
                        <div><a href="<%=path%>/cart?id=${x.getProductID()}">Buy now</a></div>
                    </div>
                </c:forEach>
            </div>
            <div class="path">New Product</b></div>
            <div class="content-main">
                <c:forEach items="${proList}" var="x">
                    <div class="product">
                        <a href="<%=path%>/index?idProDetail=${x.getProductID()}"><img src="img/1.jpg" width="100%"/></a>
                        <div class="name"><a href="<%=path%>/index?idProDetail=${x.getProductID()}">${x.getProductName()}</a></div>
                        <div class="price">$${x.getUnitPrice()}</div>
                        <div><a href="<%=path%>/cart?id=${x.getProductID()}">Buy now</a></div>
                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>

</div>

<%@include file="template/footer.jsp" %>

<%@include file="template/header_admin.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="content-left">
    <ul>
        <a href="dashboard.jsp"><li>Dashboard</li></a>
        <a href="order.jsp"><li>Orders</li></a>
        <a href="product.jsp"><li>Products</li></a>
        <a href="#"><li>Customers</li></a>
    </ul>
</div>
<div id="content-right">
    <c:if test="${create1_edit2 == 2}">
        <div class="path-admin">UPDATE PRODUCT</b></div>
        <c:url var="formAction" value="editProduct_admin" />
    </c:if>
    <c:if test="${create1_edit2 == 1}">
        <div class="path-admin">CREATE A NEW PRODUCT</b></div>
        <c:url var="formAction" value="createProduct_admin" />
    </c:if>
    <div class="content-main">
        <c:set var="p" value="${product}"></c:set>
        <form action="${formAction}" id="content-main-product" method="POST">
            <div class="content-main-1">
                <label>Product name (*):
                <input type="text" name="txtProductName" id="" value="${p.getProductName()}"><br/>
                <c:if test="${productNameMsg!=null}"><span class="msg-error">Product name is required.</span><br/></c:if>
                    <label>Unit price:</label><br/>
                    <input type="text" name="txtUnitPrice" id="" value="${p.getUnitPrice()}"><br/>
                    <label>Quantity per unit:</label><br/>
                    <input type="text" name="txtQuantityPerUnit" id="" value="${p.getQuantityPerUnit()}"><br/>
                    <label>Units in stock (*):</label><br/>
                    <input type="text" name="txtUnitsInStock" id="" value="${p.getUnitsInStock()}"><br/>
                <c:if test="${unitsInStockMsg!=null}"><span class="msg-error">Units in stock is required.</span><br/></c:if>

                </div>
                <div class="content-main-1">
                    <label>Category (*):</label><br/>
                    <select name="ddlCategory">
                    <c:forEach var="c" items="${categories}">
                        <c:choose>
                            <c:when test="${p.getCategoryID()==c.getCategoryID()}">
                                <option value="${c.getCategoryID()}" selected="selected">${c.getCategoryName()}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${c.getCategoryID()}">${c.getCategoryName()}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                        
                        
                    </select>
                    <br/>
                <c:if test="${ddlCategoryMsg}"><span class="msg-error">Product name is required.</span><br/></c:if>

                    <label>Reorder level:</label><br/>
                    <input type="text" name="txtReorderLevel" id="" value="${p.getReorderLevel()}"><br/>
                    <label>Units on order:</label><br/>
                    <input type="text" name="txtUnitsOnOrder" id="" value="${p.getUnitsOnOrder()}"><br/>
                    <label>Discontinued:</label><br/>
                    <c:choose>
                        <c:when test="${p.isDiscontinued()==true}"> 
                            <input type="checkbox" name="chkDiscontinued" id="" value="true" checked><br/>
                        </c:when>
                        <c:otherwise>
                            <input type="checkbox" name="chkDiscontinued" id=""><br/>
                        </c:otherwise>
                    </c:choose>
                    
                    <input type="submit" value="Save"/>
                </div>
            </form>
        </div>
    </div>
<%@include file="template/footer_admin.jsp" %>
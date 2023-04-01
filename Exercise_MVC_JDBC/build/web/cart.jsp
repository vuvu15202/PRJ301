<%@include file="template/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="cart">
    <div id="cart-title">
        <h3>SHOPPING CART</h3>
    </div>
    <div id="cart-content">
        <c:if test="${cartIsEmptyMsg!=null}"> <div style="text-align: center;color: red;">${cartIsEmptyMsg}</div></c:if>
        <c:if test="${successMsg!=null}"> <div style="text-align: center;color: green;">${successMsg}</div></c:if>
        <c:forEach var="cart" items="${itemList}" varStatus="varStatus">
            <div class="cart-item">
                <div class="cart-item-infor">
                    <div class="cart-item-img">
                        <img src="img/1.jpg"/>
                    </div>
                    <div class="cart-item-name">
                        <a href="detail.html?id=1">${cart.getProduct().getProductName()}</a>
                    </div>
                    <div class="cart-item-price">
                        ${cart.getProduct().getUnitPrice()}
                    </div>
                    <div class="cart-item-button">
                        <a href="<%=path%>/cart?iddDelete=${cart.getProduct().getProductID()}">Remove</a>
                    </div>
                </div>
                <div class="cart-item-function">
                    <a href="<%=path%>/cart?id=${cart.getProduct().getProductID()}&plus1_minus0=0">-</a>  
                    <a href="<%=path%>/cart?id=${cart.getProduct().getProductID()}&plus1_minus0=1">+</a>
                    <input type="text" value="${cart.getQuantity()}" disabled/>
                </div>
            </div>
        </c:forEach>
        
    </div>
    
    <div id="cart-summary">
        <div id="cart-summary-content">Total amount: <span style="color:red">${totalAmount} $</span></div>
    </div>
    <c:if test="${AccSession!=null}">
            <div id="cart-order">
                <a href="<%=path%>/orderAction" onclick="return orderAction()">
                    <input type="button" value="ORDER"/>
                </a>
            </div>
        </c:if>
        

    <c:if test="${AccSession==null}">
    <form action="orderAction" method="POST">
        <div id="customer-info">
            <div id="customer-info-content">
                <h3>CUSTOMER INFORMATION:</h3>
                <div id="customer-info-detail">
                    <div id="customer-info-left">
                        <input type="text" name="txtCompanyName" placeholder="Company name *"/><br/>
                        <input type="text" name="txtContactName" placeholder="Contact name *"/><br/>
                    </div>
                    <div id="customer-info-right">
                        <input type="text" name="txtContactTitle" placeholder="Contact title *"/><br/>
                        <input type="text" name="txtAddress" placeholder="Address *"/><br/>
                    </div>
                </div>
            </div>
        </div>
        <div id="customer-info">
            <div id="customer-info-content">
                <h3>PAYMENT METHODS:</h3>
                <div id="customer-info-payment">
                    <div>
                        <input type="radio" name="rbPaymentMethod" checked/>
                        Payment C.O.D - Payment on delivery
                    </div>
                    <div>
                        <input type="radio" name="rbPaymentMethod" disabled/>
                        Payment via online payment gateway
                    </div>
                </div>
            </div>
        </div>
        <div id="cart-order">
            <input id="buttonOrder" type="submit" onclick="return orderAction()" value="ORDER" />
        </div>
    </form>
    </c:if>
</div>

<%@include file="template/footer.jsp" %>
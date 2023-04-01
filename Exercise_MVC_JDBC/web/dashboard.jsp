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
                <div class="path-admin">DASHBOARD</b></div>
                <div class="content-main">
                    <div id="content-main-dashboard">
                        <div id="dashboard-1">
                            <div id="dashboard-1-container">
                                <div class="dashboard-item">
                                    <div class="dashboard-item-title">Weekly Sales</div>
                                    <div class="dashboard-item-content">$47K</div>
                                </div>
                                <div class="dashboard-item">
                                    <div class="dashboard-item-title">Total Orders</div>
                                    <div class="dashboard-item-content">$${totalOrderAmount}K</div>
                                </div>
                                <div class="dashboard-item">
                                    <div class="dashboard-item-title">Total Customers</div>
                                    <div class="dashboard-item-content">${cusList}</div>
                                </div>
                                <div class="dashboard-item">
                                    <div class="dashboard-item-title">Total Guest</div>
                                    <div class="dashboard-item-content">${totalOfGuest}</div>
                                </div>
                            </div>
                        </div>
                                <form action="dashboard_admin" method="">
                                     <select name="year">
                                    <option value="1996">1996</option>
                                    <option value="1997" >1997</option>
                                    <option value="1998">1998</option>
                                    <option value="2022">2022</option>
                                </select>
                                    <input type="submit" value="Submit">
                                </form>
                               
                        <div id="dashboard-2">
                            <div id="chart" style="text-align: center;">
                                <div id="chart1">
                                    <h3>Statistic Orders (Month)</h3>
                                    <canvas id="myChart1" style="width: 100%;"></canvas>
                                </div>
                                <div id="chart2">
                                    <canvas id="myChart2" style="width: 80%;"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
<%@include file="template/footer_admin.jsp" %>
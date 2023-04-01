</div>
<div id="footer-admin">
    <div class="" style="text-align: center; padding:1px">
        <div>Email : Vuvthe163299@fpt.edu.com</div>
        <div>Address: Tan Xa , Thach That District, Ha Noi Capital, Viet Nam</div>
        <h5>&copy; Copyright 2022. SmartPhone.VN</h5>
    </div>
</div>
</div>
</body>
</html>
<script>
    function cancelOrder() {
        let text;
        if (confirm("Are you sure to cancel this Order?") === false) {
            return false;
        } else {
            return true;
        }
    }

    function deleteProduct() {
        let text;
        if (confirm("Are you sure to edit/delete this product?") === false) {
            return false;
        } else {
            return true;
        }
    }

    function OrdersChart() {
        var xValues = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];
        var yValues = ${statistic};
        new Chart("myChart1", {
            type: "line",
            data: {
                labels: xValues,
                datasets: [{
                        data: yValues,
                        borderColor: "sienna",
                        fill: true
                    }]
            },
            options: {
                legend: {display: false}
            }
        });
    }
    //100,100,5000,100,100,5000,100,100,5000,100,100,100
    function CustomersChart() {
        var xValues = ["Total", "New customer"];
        var yValues = [${cusList}, ${newCusList}, 100];
        var barColors = ["green", "red"];

        new Chart("myChart2", {
            type: "bar",
            data: {
                labels: xValues,
                datasets: [{
                        backgroundColor: barColors,
                        data: yValues
                    }]
            },
            options: {
                legend: {display: false},
                title: {
                    display: true,
                    text: "New Customers (30 daily Avg)"
                }
            }
        });
    }

    OrdersChart();
    CustomersChart();
</script>
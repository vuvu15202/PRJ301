</div>
<div id="footer">
    <div class="" style="text-align: center; padding:1px">
        <div>Email : Vuvthe163299@fpt.edu.com</div>
        <div>Address: Tan Xa , Thach That District, Ha Noi Capital, Viet Nam</div>
        <h5>&copy; Copyright 2022. SmartPhone.VN</h5>
    </div>
</div>
</div>
</body>
<script>
    function addToCart(id) {
        window.location.href = '<%=request.getContextPath()%>/cart?id=' + id + '&justAdd=' + 1;

    }

    function buyNow(id) {
        window.location.href = '<%=request.getContextPath()%>/cart?id=' + id;

    }

    function deleteItem(id) {
        window.location.href = '<%=request.getContextPath()%>/cart?iddDelete=' + id;

    }
    function orderAction() {
        let text;
        if (confirm("Are you sure to order?") === false) {
            return false;
        } else {
            return true;
        }
    }
    function cancelOrder() {
        let text;
        if (confirm("Are you sure to cancel this Order?") === false) {
            return false;
        } else {
            return true;
        }
    }
    function editProfile() {
        window.location.href = '<%=request.getContextPath()%>/account/profile?editProfile=' + 1;

    }

    function changePass() {
        let text;
        if (confirm("Are you sure to change password?") === false) {
            return false;
        } else {
            return true;
        }
    }
//        function changePass() {
//        var email = document.getElementById("email").value;
//        if (email.length !== 0){
//            alert('Change password successfully');
//        }
// }


</script>
</html>
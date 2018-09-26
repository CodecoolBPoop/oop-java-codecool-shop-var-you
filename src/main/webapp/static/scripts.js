$(".cart-button").click(function () {
    $.get("/cart-request", function (data) {
        var cartItems = $.parseJSON(data);
        for (var i = 0; i < cartItems.length; i++) {
            console.log(cartItems[i]);
        }
    })
});

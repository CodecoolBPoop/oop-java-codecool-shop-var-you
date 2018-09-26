$(".cart-button").click(function () {
    $.get("/cart-request", function (data) {
        var products = JSON.parse(data);
        for (var i = 0; i < products.length; i++) {
            JSON.parse(i);
            console.log(products);
        }
    })
});

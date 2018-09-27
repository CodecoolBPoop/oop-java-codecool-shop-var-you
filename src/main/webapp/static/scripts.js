$(".cart-button").click(function () {
    $.get("/cart-request", function (data) {
        var cartItems = $.parseJSON(data);
        var stringBuilder = [];
        for (var i = 0; i < cartItems.length; i++) {
            stringBuilder.push('<div class="row"> <div class="col-md-4">', cartItems[i].name, '</div>');
            stringBuilder.push('<div class="col-md-4">', cartItems[i].price, '</div>', '</div>');
            $(".cart-table").empty();
            $(".cart-table").append(stringBuilder.join(""));
            console.log(cartItems[i]);
        }
    });
    $("#cartModal").modal('toggle');
});

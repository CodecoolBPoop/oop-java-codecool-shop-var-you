$(".cart-button").click(function () {
    $.get("/cart-request", function (data) {
        var cartItems = $.parseJSON(data);
        var stringBuilder = [];
        for (var i = 0; i < cartItems.length; i++) {
            stringBuilder.push('<div class="row"> <div class="col ml-auto">', cartItems[i].name, '</div>');
            stringBuilder.push('<div class="ml-auto">', cartItems[i].price, '</div>');
            stringBuilder.push('<div class="ml-auto">' +
                ' <form method="post" action="/"><button class="btn btn-success add_to_cart" type="submit" name="add" value=',cartItems[i].id, '>+</button></form></div>');'\n'
            stringBuilder.push('<div class="ml-auto">' +
                ' <form method="post" action="/"><button class="btn btn-success add_to_cart" type="submit" name="remove" value=',cartItems[i].id, '>-</button></form></div>', '</div>');
            $(".cart-table").empty();
            $(".cart-table").append(stringBuilder.join(""));

            console.log(cartItems[i]);
        }
    });
    $("#cartModal").modal('toggle');
});

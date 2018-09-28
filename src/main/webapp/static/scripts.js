$(".cart-button").click(function () {
    $.get("/cart-request", function (data) {
        var cartItems = $.parseJSON(data);
        var stringBuilder = [];
        for (var i = 0; i < cartItems.length-1; i++) {
            stringBuilder.push('<tr> <td>', cartItems[i].name, '</td>');
            stringBuilder.push('<td>', cartItems[i].price, '</td>');
            stringBuilder.push('<td>' +
                ' <form method="post" action="/"><button class="btn btn-success add_to_cart" type="submit" name="add" value=',cartItems[i].id, '>+</button></form></td>');'\n'
            stringBuilder.push('<td>' +
                ' <form method="post" action="/"><button class="btn btn-success add_to_cart" type="submit" name="remove" value=',cartItems[i].id, '>-</button></form></td> </tr>');
            $(".cart-table").empty();
            $(".cart-table").append(stringBuilder.join(""));

            console.log(cartItems[i]);
        }
        stringBuilder.push('<tr> <td>' + "TOTAL:  " , cartItems[cartItems.length -1].total, " USD" + '</td>');
        $(".cart-table").empty();
        $(".cart-table").append(stringBuilder.join(""));
        console.log(cartItems);

    });
    $("#cartModal").modal('toggle');
});

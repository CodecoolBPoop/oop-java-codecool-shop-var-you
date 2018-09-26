$(".cart-button").click(function () {
    $.get("/cart-request", function (data) {
        console.log($.parseJSON(data))
    })
});

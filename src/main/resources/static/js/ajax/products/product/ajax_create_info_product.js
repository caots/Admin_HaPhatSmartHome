$(document).ready(function () {
    findDetailsProductNewsById();
});

function findDetailsProductNewsById() {
    const urlCreateProduct = window.location.href;
    console.log(urlCreateProduct);
    var str = urlCreateProduct.split("=");
    const id = str[str.length - 1];
    console.log(id);
    $.ajax({
        type: "GET",
        dataType: "json",
        headers: {
            "adminbksoftwarevn": tokenHeader_value,
        },
        url: "api/v1/public/products/find-details-product-by-id?id=" + id,
        timeout: 30000,
        success: function (result) {
            createNewsProduct(result);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });

}

function createNewsProduct(detailsProduct) {

    $("#btn-create-news-product").click(function () {
        var info = CKEDITOR.instances["editor"].getData();
        detailsProduct.info = info;
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            headers: {
                "adminbksoftwarevn": tokenHeader_value,
            },
            url: "api/v1/admin/product-details",
            data: JSON.stringify(detailsProduct),
            timeout: 30000,
            success: function () {
                alert('UPDATE PRODUCT SUCCESS');
                $("#btn-create-news-product").prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
                alert("UPDATE ERROR ");
                $("#btn-create-news-product").prop("disabled", true);
            }
        });
    })
}

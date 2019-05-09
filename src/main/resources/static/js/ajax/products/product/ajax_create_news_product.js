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
    var formNewsData;
    $("#btn-create-images-news-product").click(function () {
            var formImages = $("#images-news-product")[0];
            formNewsData = new FormData(formImages);
            uploadMultiFile(formNewsData).then(function (files) {
                files.map(function (data) {
                    $("#list-image").append(`<a href="${data}">${data}</a>` + '&nbsp;&nbsp;&nbsp;&nbsp;');
                    var dataNews = {
                        "url": data ,
                    };
                    $.ajax({
                        type: "POST",
                        headers: {
                            "adminbksoftwarevn": tokenHeader_value,
                        },
                        contentType: "application/json",
                        url: "api/v1/admin/product-details/product-image?product-details-id=" + detailsProduct.id,
                        data: JSON.stringify(dataNews),
                        timeout: 30000,
                        success: function () {
                            console.log(dataNews);
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            errMess(jqXHR, textStatus, errorThrown);
                        }
                    });
                });
            });
        }
    );

    $("#btn-create-news-product").click(function () {
        var news = CKEDITOR.instances["editor"].getData();
        detailsProduct.news = news;
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

var uploadMultiFile = async (files) => {
    let data;
    await $.ajax({
        type: "POST",
        headers: {
            "adminbksoftwarevn": tokenHeader_value,
        },
        url: "api/v1/public/upload-multi-files",
        enctype: 'multipart/form-data',
        data: files,
        cache: false,
        processData: false,
        contentType: false,
        success: function (result) {
            data = result;
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("chưa chọn ảnh!");
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
    return data;
};
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
        url: "api/v1/public/menu/footerSmall/find-by-id?id=" + id,
        timeout: 30000,
        success: function (result) {
            createNewsFooter(result);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });

}


function createNewsFooter(detailsFooter) {

    $("#btn-create-news-footer").click(function () {
        var news = CKEDITOR.instances["editor"].getData();
        detailsFooter.content = news;
        $.ajax({
            type: "PUT",
            headers: {
                "adminbksoftwarevn": tokenHeader_value,
            },
            contentType: "application/json",
            url: "api/v1/admin/footer-menu-details",
            data: JSON.stringify(detailsFooter),
            timeout: 30000,
            success: function () {
                alert('chỉnh sửa bài viết thành công');
                $("#btn-create-news-footer").prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
                alert("chỉnh sửa bài viết thất bại ");
                $("#btn-create-news-footer").prop("disabled", true);
            }
        });
    })
}

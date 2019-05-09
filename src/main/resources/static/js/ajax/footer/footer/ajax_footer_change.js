$(document).ready(function () {
    findFooterById();
});

// ============ FIND FOOTER BY ID ===================

function findFooterById() {

    const urlChangeFooter = window.location.href;
    var str = urlChangeFooter.split("=");
    const id = str[str.length - 1];
    console.log(id);
    $.ajax({
        type: "GET",
        dataType: "json",
        headers: {
            "adminbksoftwarevn": tokenHeader_value,
        },
        url: "api/v1/public/menu/footerBig/find-by-id?id=" + id,
        timeout: 30000,
        success: function (result) {
            updateFooter(result);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

//============ UPDATE COMPANY ========================
function updateFooter(data) {

    $('#name-footer-value').val(data.name);

    $('#btn-create-footer').click(function () {
        data.name = $('#name-footer-value').val();
        $.ajax({
            type: "PUT",
            headers: {
                "adminbksoftwarevn": tokenHeader_value,
            },
            contentType: "application/json",
            url: "api/v1/admin/footer-menu",
            data: JSON.stringify(data),
            timeout: 30000,
            success: function () {
                alert('UPDATE SUCCESS');
                $('#btn-create-footer').prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
                alert("UPDATE ERROR ");
                $('#btn-create-footer').prop("disabled", true);
            }
        });
    });
}


$(document).ready(function () {
    clickBtnCartChangeSubmit();
});

// ============ FIND PARTNER BY ID ===================

function findBuyFormCartById(id) {

    $.ajax({
        type: "GET",
        dataType: "json",
        headers: {
            "adminbksoftwarevn": tokenHeader_value,
        },
        url: "api/v1/public/buy-form/find-by-id?id=" + id,
        timeout: 30000,
        success: function (result) {
            sendEmail(result);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

function sendEmail(data) {

    $("#email").val(data.email);

    $("#send-email-btn").click(function () {


        var email = $("#email").val();
        var title = $("#header-mail").val();
        var content = $("#content-mail").val();

        $.ajax({
            type: "GET",
            dataType: "json",
            headers: {
                "adminbksoftwarevn": tokenHeader_value,
            },
            url: "api/v1/public/send-email?email=" + email + "&header=" + title + "&content=" + content,
            timeout: 30000,
            success: function (result) {
                alert("Gửi email thành công");
                $('#send-email-btn').prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $('#send-email-btn').prop("disabled", true);
                errMess(jqXHR, textStatus, errorThrown);
            }
        });

    })

}

function clickBtnCartChangeSubmit() {
    const urlCreatePartner = window.location.href;
    const str = urlCreatePartner.split("=");
    const id = str[str.length - 1];
    findBuyFormCartById(id);
}


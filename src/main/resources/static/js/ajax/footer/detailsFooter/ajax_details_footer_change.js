$(document).ready(function () {
    findAllFooterValue();
    clickBtnDetailsFooterChangeSubmit();
});

//============ CREATE DETAILS FOOTER ========================

function createDetailsFooter() {

    $('#btn-create-details-footer').click(function () {

        var idFooter = $("#footer-value").val();

        const name = $("#name-details-footer-value").val();

        const detailsFooter = {
            "name": name,
        };
        $.ajax({
            type: "POST",
            contentType: "application/json",
            headers: {
                "adminbksoftwarevn": tokenHeader_value,
            },
            url: "api/v1/admin/footer-menu-details?footer-menu-id=" + idFooter,
            data: JSON.stringify(detailsFooter),
            cache: false,
            timeout: 300000,
            success: function (data) {
                alert("CREATE SUCCESS : " + data.product.name);
                $("#btn-create-details-footer").prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
                alert("CREATE ERROR ");
                $("#btn-create-details-footer").prop("disabled", true);
            }
        })
    });
}

// ============ FIND DETAILS FOOTER BY ID ===================

function findDetailsFooterById(id) {
    $.ajax({
        type: "GET",
        dataType: "json",
        headers: {
            "adminbksoftwarevn": tokenHeader_value,
        },
        url: "api/v1/public/menu/footerSmall/find-by-id?id=" + id,
        timeout: 30000,
        success: function (result) {
            updateDetailsFooter(result);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

//============ UPDATE DETAILS FOOTER ========================
function updateDetailsFooter(data) {
    $("#footer-value").prop("disabled", true);

    $('#name-details-footer-value').val(data.name);

    $('#btn-create-details-footer').click(function () {

        data.name = $('#name-details-footer-value').val();
        console.log(data);
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            headers: {
                "adminbksoftwarevn": tokenHeader_value,
            },
            url: "api/v1/admin/footer-menu-details",
            data: JSON.stringify(data),
            timeout: 30000,
            success: function () {
                alert('UPDATE SUCCESS');
                $('#btn-create-details-footer').prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
                alert("UPDATE ERROR ");
                $('#btn-create-details-footer').prop("disabled", true);

            }
        });
    });
}

function clickBtnDetailsFooterChangeSubmit() {
    const urlCreateDetailsProduct = window.location.href;
    console.log(urlCreateDetailsProduct);
    var str = urlCreateDetailsProduct.split("=");
    const id = str[str.length - 1];
    if ((id - 1) >= 0) {
        findDetailsFooterById(id)
    } else createDetailsFooter();

}


function findAllFooterValue() {
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": tokenHeader_value,
        },
        url: "api/v1/public/menu/footerBig",
        success: function (footer) {
            const listSize = Object.keys(footer).length;
            if (footer.check == "fail") {
                alert("products isEmpty! Name not found!");
                return;
            }
            if (listSize > 0) {
                let contentRow = '';
                footer.map(function (foo) {
                    contentRow += `
                       <option id="option${foo.id}" value="${foo.id}">${foo.name}</option>
                    `;
                });
                $("#footer-value").html(contentRow);
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}


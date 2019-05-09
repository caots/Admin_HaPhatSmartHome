$(document).ready(function () {
    clickBtnCompanyChangeSubmit();
});

//============ CREATE COMPANY ========================

function createCompany() {

    $('#btn-create-company').click(function () {

        const name = $('#name-company-value').val();
        const address = $('#address-company-value').val();
        const hotline = $('#hotline-company-value').val();
        const email = $('#email-company-value').val();
        const company = {
            "name": name,
            "address": address,
            "hotline": hotline,
            "email": email,
        };
        console.log(company);
        $.ajax({
            type: "POST",
            contentType: "application/json",
            headers: {
                "adminbksoftwarevn": tokenHeader_value,
            },
            url: "api/v1/admin/company",
            data: JSON.stringify(company),
            cache: false,
            timeout: 300000,
            success: function (data) {
                alert("CREATE SUCCESS : " + data.name);
                $('#btn-create-company').prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
                alert("CREATE ERROR ");
                $('#btn-create-company').prop("disabled", true);
            }
        })
    });
}

// ============ FIND COMPANY BY ID ===================

function findCompanyById(id) {

    $.ajax({
        type: "GET",
        dataType: "json",
        headers: {
            "adminbksoftwarevn": tokenHeader_value,
        },
        url: "api/v1/public/companies/find-by-id?id=" + id,
        timeout: 30000,
        success: function (result) {
            updateCompany(result);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

//============ UPDATE COMPANY ========================
function updateCompany(data) {

    $('#name-company-value').val(data.name);
    $('#address-company-value').val(data.address);
    $('#hotline-company-value').val(data.hotline);
    $('#email-company-value').val(data.email);


    $('#btn-create-company').click(function () {
        data.name = $('#name-company-value').val();
        data.address = $('#address-company-value').val();
        data.hotline = $('#hotline-company-value').val();
        data.email = $('#email-company-value').val();
        $.ajax({
            type: "PUT",
            headers: {
                "adminbksoftwarevn": tokenHeader_value,
            },
            contentType: "application/json",
            url: "api/v1/admin/company",
            data: JSON.stringify(data),
            timeout: 30000,
            success: function () {
                alert('UPDATE SUCCESS');
                $('#btn-create-company').prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
                alert("UPDATE ERROR ");
                $('#btn-create-company').prop("disabled", true);

            }
        });
    });
}

function clickBtnCompanyChangeSubmit() {
    const urlChangeCompany = window.location.href;
    var str = urlChangeCompany.split("=");
    const id = str[str.length - 1];
    if ((id - 1) >= 0) {
        findCompanyById(id)
    } else createCompany();
}


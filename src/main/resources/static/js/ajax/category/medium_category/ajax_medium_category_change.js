$(document).ready(function () {
    clickBtnMediumChangeSubmit();
});

//============ Create Big Category ========================
function createMediumCategory() {
    $("#btn-create-medium-category").prop("disabled", true);

    let idBigCategory = '';
    $('#big-category-value').click(function () {
        idBigCategory = $(this).val();
    });
    $('#btn-create-medium-category').click(function () {
        const nameMediumCategory = $("#name-medium-category").val();
        console.log(nameMediumCategory);
        const bigCategory = {
            "name": nameMediumCategory,
        };
        $.ajax({
            type: "POST",
            contentType: "application/json",
            headers: {
                "adminbksoftwarevn": tokenHeader_value,
            },
            url: "api/v1/admin/category/medium?big-id=" + idBigCategory,
            data: JSON.stringify(bigCategory),
            cache: false,
            timeout: 300000,
            success: function (data) {
                alert("CREATE SUCCESS : " + data.name);
                $("#btn-create-medium-category").prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
                alert("CREATE ERROR ");
            }
        })
    });
}

//============ Find Big Category By Id ===================

function findMediumCategoryById(id) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        headers: {
            "adminbksoftwarevn": tokenHeader_value,
        },
        url: "api/v1/public/category/findMediumCategoryById?idMedium=" + id,
        timeout: 30000,
        success: function (result) {
            updateMediumCategory(result);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

// ============ UPDATE Medium Category ========================
function updateMediumCategory(data) {

    $('#name-medium-category').val(data.name);
    $("#big-category-value").prop("disabled", true);
    $('#btn-create-medium-category').click(function () {
        data.name = $('#name-medium-category').val();
        console.log(data);
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            headers: {
                "adminbksoftwarevn": tokenHeader_value,
            },
            url: "api/v1/admin/category/medium",
            data: JSON.stringify(data),
            timeout: 30000,
            success: function (result) {
                alert('UPDATE SUCCESS'+result.name);
                return;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
                alert("UPDATE ERROR ");
            }
        });
    });
}

function clickBtnMediumChangeSubmit() {
    const urlCreateCategory = window.location.href;
    console.log(urlCreateCategory);
    var str = urlCreateCategory.split("=");
    const id = str[str.length - 1];
    if ((id - 1) >= 0) {
        findMediumCategoryById(id)
    } else createMediumCategory();

}

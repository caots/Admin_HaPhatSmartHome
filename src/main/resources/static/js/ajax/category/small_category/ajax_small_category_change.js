$(document).ready(function () {
    clickBtnSmallChangeSubmit();
});

//============ Create Big Category ========================
function createSmallCategory() {
    let idMediumCategory = '';
    $('#medium-category-value').click(function () {
        idMediumCategory = $(this).val();
    });

    $("#btn-create-small-category").prop("disabled", true);
    $('#btn-create-small-category').click(function () {



        const nameSmallCategory = $("#name-small-category").val();

        console.log(nameSmallCategory + " - "+idMediumCategory);
        const smallCategory = {
            "name": nameSmallCategory,
        };
        $.ajax({
            type: "POST",
            contentType: "application/json",
            headers: {
                "adminbksoftwarevn": tokenHeader_value,
            },
            url: "api/v1/admin/category/small?medium-id=" + idMediumCategory,
            data: JSON.stringify(smallCategory),
            cache: false,
            timeout: 300000,
            contentType: "application/json",
            success: function (data) {
                alert("CREATE SUCCESS : " + data.name);
                $("#btn-create-small-category").prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
                alert("CREATE ERROR :" );

            }
        })
    });
}

//============ Find Big Category By Id ===================

function findSmallCategoryById(id) {
    $.ajax({
        type: "GET",
        dataType: "json",
        headers: {
            "adminbksoftwarevn": tokenHeader_value,
        },
        url: "api/v1/public/category/findSmallCategoryById?idSmall=" + id,
        timeout: 30000,
        success: function (result) {
            updateSmallCategory(result);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

// ============ UPDATE Medium Category ========================
function updateSmallCategory(data) {

    $('#name-small-category').val(data.name);
    $("#medium-category-value").prop("disabled", true);
    $('#btn-create-small-category').click(function () {
        data.name = $('#name-small-category').val();
        console.log(data);
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            headers: {
                "adminbksoftwarevn": tokenHeader_value,
            },
            url: "api/v1/admin/category/small",
            data: JSON.stringify(data),
            timeout: 30000,
            success: function (result) {
                alert('UPDATE SUCCESS : '+result.name);
                return;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
                alert('UPDATE FAIL');
            }
        });
    });
}

function clickBtnSmallChangeSubmit() {
    const urlCreateCategory = window.location.href;
    console.log(urlCreateCategory);
    var str = urlCreateCategory.split("=");
    const id = str[str.length - 1];
    if ((id - 1) >= 0) {
        findSmallCategoryById(id)
    } else createSmallCategory();
}

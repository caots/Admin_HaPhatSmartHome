$(document).ready(function () {
    findAllNameBigCategory();
    findAllNameMediumCategory();
    findAllNameSmallCategory();
});

function findAllNameBigCategory() {
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": tokenHeader_value,
        },
        url: "api/v1/public/category/showBig/all",
        success: function (bigCategories) {
            const listSize = Object.keys(bigCategories).length;
            if (bigCategories.check == "fail") {
                alert("Category isEmpty! Name not found!");
                return;
            }
            if (listSize > 0) {

                let contentRow = '';
                bigCategories.map(function (bigCategory) {
                    contentRow += `
                       <option value ="${bigCategory.id}" >${bigCategory.name}</option>
                    `;
                });
                $("#big-category-value").html(contentRow);
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

function findAllNameMediumCategory() {
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": tokenHeader_value,
        },
        url: "api/v1/public/category/medium-category/all",
        success: function (mediumCategories) {
            const listSize = Object.keys(mediumCategories).length;
            if (mediumCategories.check == "fail") {
                alert("Category isEmpty! Name not found!");
                return;
            }
            if (listSize > 0) {
                let contentRow = '';
                mediumCategories.map(function (mediumCategory) {
                    contentRow += `
                       <option value="${mediumCategory.id}">${mediumCategory.name}</option>
                    `;
                });
                $("#medium-category-value").html(contentRow);
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}
function findAllNameSmallCategory() {
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": tokenHeader_value,
        },
        url: "api/v1/public/category/small-category/all",
        success: function (smallCategories) {
            const listSize = Object.keys(smallCategories).length;
            if (smallCategories.check == "fail") {
                alert("Small Category isEmpty! Name not found!");
                return;
            }
            if (listSize >= 0) {
                let contentRow = '';
                smallCategories.map(function (smallCategory) {
                    contentRow += `
                       <option value="${smallCategory.id}">${smallCategory.name}</option>
                    `;
                });
                $("#small-category-value").html(contentRow);
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}


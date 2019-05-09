$(document).ready(function () {
    findAllSmallCategory(1);
    findAllPageSmallNumber();
});

//==================================page=============================.unbind('click')
function pageSmallCategory(size) {
    let contentRow = '';
    for (let i = 1; i <= size; i++) {
        contentRow += `<li><a href="#" class="page" id="_${i}" name="${i}" >${i}</a></li>`;
    }
    $(".pagination").html(
        `<li><a href="#" class="prev" id="prev">&laquo</a></li>`
        + contentRow +
        `<li><a href="#" class="next" id="next">&raquo;</a></li>`
    );
    $("#_1").addClass("active");
}

function findAllPageSmallNumber() {
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": tokenHeader_value,
        },
        url: "api/v1/public/category/small-category/size",
        success: function (size) {

            pageSmallCategory(size);

            $('.page').click(function () {
                const page = $(this).attr("name");
                for (let i = 1; i <= size; i++) {
                    var id = "#_" + i;
                    $(id).removeClass("active");
                }
                $(this).addClass("active");
                findAllSmallCategory(page);
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

function findAllSmallCategory(page) {
    //============ Get All Small Category ========================
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": tokenHeader_value,
        },
        url: "api/v1/public/category/small-category?page=" + page,
        success: function (smallCategories) {
            $("#column-small-category").html(
                "<td> STT</td>" +
                "<td> Tên</td>" +
                "<td> Loại Sản Phẩm</td>" +
                "<td> Chức năng</td>"
            );
            const listSize = Object.keys(smallCategories).length;
            if (smallCategories.check == "fail") {
                alert("Category isEmpty! Name not found!");
                return;
            }
            if (listSize > 0) {

                var contentRow = '';
                smallCategories.map(function (smallCategory) {
                    contentRow += `
                        <tr>
                        <td> ${smallCategory.id} </td>
                        <td> ${smallCategory.name} </td>
                        <td> ${smallCategory.mediumCategory.name} </td>
                        <td>
                              <a href="update-category?id=${smallCategory.id}" name="${smallCategory.id}"  class="update-small-category" style="cursor: pointer;color: #4285F4">Chỉnh sửa</a>&nbsp;
                              <span name="${smallCategory.id}" class="delete-small-category" style="cursor: pointer;color: red">Xóa</span>&nbsp;
                        </td>
                        </tr>
                    `;
                })
                $("#row-small-category").html(contentRow);
                //============ delete =============
                deleteSmallCategory();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    })
}

//============ Delete Big Category ========================
function deleteSmallCategory() {

    $('.delete-small-category').click(function () {
        const id = $(this).attr("name");
        $.ajax({
            type: "PUT",
            headers: {
                "adminbksoftwarevn": tokenHeader_value,
            },
            contentType: "application/json",
            url: "api/v1/admin/category/delete-small?id=" + id,
            timeout: 30000,

            success: function () {
                alert('DELETE SUCCESS');
                return;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
            }
        });
    });

}

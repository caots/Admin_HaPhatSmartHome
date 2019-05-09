$(document).ready(function () {
    findAllMediumCategory(1);
    findAllPageMediumNumber();

});

//==================================page=============================.unbind('click')
function pageMediumCategory(size) {
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

function findAllPageMediumNumber() {
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": tokenHeader_value,
        },
        url: "api/v1/public/category/medium-category/size",
        success: function (size) {

            pageMediumCategory(size);

            $('.page').click(function () {
                const page = $(this).attr("name");
                for (let i = 1; i <= size; i++) {
                    var id = "#_" + i;
                    $(id).removeClass("active");
                }
                $(this).addClass("active");
                findAllMediumCategory(page);
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

function findAllMediumCategory(page) {
    //============ Get All Medium Category ========================
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": tokenHeader_value,
        },
        url: "api/v1/public/category/medium-category?page=" + page,
        success: function (mediumCategories) {
            $("#column-medium-category").html(
                "<td> STT</td>" +
                "<td> Tên</td>" +
                "<td> Chuyên Mục Sản Phẩm</td>" +
                "<td> Chức Năng</td>"
            );
            const listSize = Object.keys(mediumCategories).length;
            if (mediumCategories.check == "fail") {
                alert("Medium Category isEmpty! Name not found!");
                return;
            }
            if (listSize > 0) {

                let contentRow = '';


                mediumCategories.map(function (mediumCategory) {
                    contentRow += `
                        <tr>
                        <td> ${mediumCategory.id} </td>
                        <td> ${mediumCategory.name} </td>
                        <td> ${mediumCategory.bigCategory.name} </td>
                        <td>
                              <a href="update-category?id=${mediumCategory.id}" name="${mediumCategory.id}"  class="update-medium-category" style="cursor: pointer;color: #4285F4">Sửa</a>&nbsp;
                              <span name="${mediumCategory.id}" class="delete-medium-category" style="cursor: pointer;color: red">Xóa</span>&nbsp;
                        </td>
                        </tr>
                    `;
                })
                $("#row-medium-category").html(contentRow);
                //============ delete =============
                deleteMediumCategory();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    })
}

//============ Delete Big Category ========================
function deleteMediumCategory() {

    $('.delete-medium-category').click(function () {
        const id = $(this).attr("name");
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            headers: {
                "adminbksoftwarevn": tokenHeader_value,
            },
            url: "api/v1/admin/category/delete-medium?id=" + id,
            timeout: 30000,
            success: function () {
                alert('DELETE SUCCESS');
                return;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("DELETE FAIL");
                errMess(jqXHR, textStatus, errorThrown);
            }
        });
    });

}

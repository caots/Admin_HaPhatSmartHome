$(document).ready(function () {
    findAllPageDetailsFooterNumber();
});

//==================================page=============================
function pageDetailsFooter(size) {
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

function findAllPageDetailsFooterNumber() {
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": tokenHeader_value,
        },
        url: "api/v1/public/menu/footerSmall/size",
        success: function (size) {
            findAllDetailsFooter(1);
            pageDetailsFooter(size);
            $('.page').click(function () {
                const page = $(this).attr("name");
                for (let i = 1; i <= size; i++) {
                    var id = "#_" + i;
                    $(id).removeClass("active");
                }
                $(this).addClass("active");
                findAllDetailsFooter(page);

            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

//============ Get All Big Category ========================
function findAllDetailsFooter(page) {
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": tokenHeader_value,
        },
        url: "api/v1/public/menu/find-all-footer-small?page=" + page,
        success: function (detailsFooters) {
            const listSize = Object.keys(detailsFooters).length;
            if (detailsFooters.check == "fail") {
                alert("Footer isEmpty! Name not found!");
                return;
            }

            $("#column-details-footer").html(
                "<td> STT</td>" +
                "<td> Tên</td>" +
                "<td>Danh Mục Sản Phẩm</td>" +
                "<td> Chức Năng</td>"
            );
            console.log(listSize);
            if (listSize > 0) {
                let contentRow = '';
                detailsFooters.map(function (detailsFooter) {
                    contentRow += `
                        <tr>
                        <td> ${detailsFooter.id} </td>
                        <td> ${detailsFooter.name} </td>
                        <td> ${detailsFooter.footerMenu.name} </td>
                        <td>
                                 <a href="create-news-footer?id=${detailsFooter.id}" name="${detailsFooter.id}" style="cursor: pointer;color: green">Thêm Bài viết</a>&nbsp;
                                 <a href="update-details-footer?id=${detailsFooter.id}" style="cursor: pointer;color: #4285F4">Chỉnh Sửa</a>&nbsp;
                                 <span name="${detailsFooter.id}" class="delete-details-footer" style="cursor: pointer;color: red">Xóa</span>&nbsp;
                        </td>
                        </tr>
                    `;
                });
                $("#row-details-footer").html(contentRow);
                //============ delete =============
                deleteDetailsFooter();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

//============ Delete Big Category ========================
function deleteDetailsFooter() {

    $('.delete-details-footer').click(function () {
        const id = $(this).attr("name");
        $.ajax({
            type: "PUT",
            headers: {
                "adminbksoftwarevn": tokenHeader_value,
            },
            contentType: "application/json",
            url: "api/v1/admin/delete-footer-menu-details?id=" + id,
            timeout: 30000,
            success: function () {
                $('.delete-details-footer').prop("disabled", true);
                alert('DELETE SUCCESS');
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("DELETE FAIL");
                errMess(jqXHR, textStatus, errorThrown);
            }
        });
    });
}




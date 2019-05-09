$(document).ready(function () {
    findAllFooter();
});

//============ Get All Big Category ========================
function findAllFooter() {
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": tokenHeader_value,
        },
        url: "api/v1/public/menu/footerBig",
        success: function (footers) {
            const listSize = Object.keys(footers).length;
            if (footers.check == "fail") {
                alert("Footer isEmpty! Name not found!");
                return;
            }

            $("#column-footer").html(
                "<td> STT</td>" +
                "<td> Tên</td>" +
                "<td>Chức Năng</td>"
            );
            if (listSize > 0) {

                let contentRow = '';

                footers.map(function (footer) {
                    contentRow += `
                        <tr>
                        <td> ${footer.id} </td>
                        <td> ${footer.name} </td>
                        <td>
                                 <a href="update-footer?id=${footer.id}" style="cursor: pointer;color: #4285F4">Chỉnh Sửa</a>&nbsp;
                                 <span name="${footer.id}" class="delete-footer" style="cursor: pointer;color: red">Xóa</span>&nbsp;

                        </td>
                        </tr>
                    `;
                });
                $("#row-footer").html(contentRow);
                //============ delete =============
                deleteFooter();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

//============ Delete Big Category ========================
function deleteFooter() {

    $('.delete-footer').click(function () {
        const id = $(this).attr("name");
        $.ajax({
            type: "PUT",
            headers: {
                "adminbksoftwarevn": tokenHeader_value,
            },
            contentType: "application/json",
            url: "api/v1/admin/delete-footer-menu?id=" + id,
            timeout: 30000,
            success: function () {
                $('.delete-footer').prop("disabled", true);
                alert('DELETE SUCCESS');
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("DELETE FAIL");
                errMess(jqXHR, textStatus, errorThrown);
            }
        });
    });
}




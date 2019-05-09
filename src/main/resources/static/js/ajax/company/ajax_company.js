$(document).ready(function () {
    findAllCompany();
});

//============ Get All Big Category ========================
function findAllCompany() {
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": tokenHeader_value,
        },
        url: "api/v1/public/companies",
        success: function (companies) {
            const listSize = Object.keys(companies).length;
            if (companies.check == "fail") {
                alert("Category isEmpty! Name not found!");
                return;
            }

            $("#column-company").html(
                "<td> STT</td>" +
                "<td> Tên</td>" +
                "<td> Địa Chỉ</td>" +
                "<td> Hotline</td>" +
                "<td> Email</td>" +
                "<td> Chức năng</td>"
            );
            if (listSize > 0) {

                let contentRow = '';

                companies.map(function (company) {
                    contentRow += `
                        <tr>
                        <td> ${company.id} </td>
                        <td> ${company.name} </td>
                        <td> ${company.address} </td>
                        <td> ${company.hotline} </td>
                        <td> ${company.email} </td>
                        <td>
                                 <a href="update-company?id=${company.id}" style="cursor: pointer;color: #4285F4">Chỉnh sửa</a>&nbsp;
                                 <span name="${company.id}" class="delete-company" style="cursor: pointer;color: red">Xóa</span>&nbsp;

                        </td>
                        </tr>
                    `;
                });
                $("#row-company").html(contentRow);
                //============ delete =============
                deleteCompany();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

//============ Delete Big Category ========================
function deleteCompany() {

    $('.delete-company').click(function () {
        const id = $(this).attr("name");
        $.ajax({
            type: "PUT",
            headers: {
                "adminbksoftwarevn": tokenHeader_value,
            },
            contentType: "application/json",
            url: "api/v1/admin/delete-company?id=" + id,
            timeout: 30000,
            success: function () {
                $('.delete-big-category').prop("disabled", true);
                alert('DELETE SUCCESS');
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("DELETE FAIL");
                errMess(jqXHR, textStatus, errorThrown);
            }
        });
    });
}





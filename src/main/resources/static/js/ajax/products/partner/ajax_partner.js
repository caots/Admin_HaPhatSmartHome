$(document).ready(function () {
    findAllPartnerPage(1);
    findAllPagPartnerNumber();
});
//==================================page=============================.unbind('click')
function pagePartner(size) {
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

function findAllPagPartnerNumber() {
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": tokenHeader_value,
        },
        url: "api/v1/public/partner/find-all/size",
        success: function (size) {
            console.log(size);
            pagePartner(size);
            $('.page').click(function () {
                const page = $(this).attr("name");
                for (let i = 1; i <= size; i++) {
                    var id = "#_" + i;
                    $(id).removeClass("active");
                }
                $(this).addClass("active");
                findAllPartnerPage(page);
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}


function findAllPartnerPage(page) {
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": tokenHeader_value,
        },
        url: "api/v1/public/partner/find-all?page="+page ,
        success: function (partners) {

            $("#column-partner").html(
                "<td> STT</td>" +
                "<td> Tên </td>" +
                "<td> Mô tả </td>" +
                "<td> Chức năng</td>"
            );

            const listSize = Object.keys(partners).length;

            if (partners.check == "fail") {
                alert("Product isEmpty! Name not found!");
                return;
            }

            if (listSize > 0) {

                let contentRow = '';

                partners.map(function (partner) {
                    contentRow += `
                        <tr>
                        <td> ${partner.id} </td>
                        <td> ${partner.name} </td>
                        <td> ${partner.present} </td>
                       
                        <td>
                              <a href="update-partner?id=${partner.id}" name="${partner.id}"   style="cursor: pointer;color: #4285F4">Chỉnh sửa</a>&nbsp;
                              <span name="${partner.id}" class="delete-partner" style="cursor: pointer;color: red">Xóa</span>&nbsp;
                        </td>
                        </tr>
                    `;
                })

                $("#row-partner").html(contentRow);

                //===== delete =======
                deletePartner();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

//============ Delete PRODUCT ========================
function deletePartner() {
    $('.delete-partner').click(function () {
        const id = $(this).attr("name");
        console.log("id-partner " + id);
        $.ajax({
            type: "PUT",
            headers: {
                "adminbksoftwarevn": tokenHeader_value,
            },
            contentType: "application/json",
            url: "api/v1/admin/delete-partner?id=" + id,
            timeout: 30000,
            success: function () {
                alert('DELETE SUCCESS');
                return;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
                alert('DELETE FAIL');
            }
        });
    });
}

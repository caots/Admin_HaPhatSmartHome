$(document).ready(function () {
    findAllRequestPricePage(1);
    findAllPageRequestPriceNumber();
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

function findAllPageRequestPriceNumber() {
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": tokenHeader_value,
        },
        url: "api/v1/public/request-price/page",
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
                findAllRequestPricePage(page);
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}


function findAllRequestPricePage(page) {
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": tokenHeader_value,
        },
        url: "api/v1/public/request-price?page=" + page,
        success: function (requestPrices) {

            $("#column-request-price").html(
                "<td> STT</td>" +
                "<td> Tên </td>" +
                "<td> Số điện thoại </td>" +
                "<td> Email </td>" +
                "<td> Tên sản phẩm</td>" +
                "<td> Tên công ty</td>" +
                "<td> địa chỉ</td>" +
                "<td> file</td>" +
                "<td> Chức năng</td>"
            );

            const listSize = Object.keys(requestPrices).length;
            if (listSize > 0) {
                let contentRow = '';
                requestPrices.map(function (requestPrice) {
                    contentRow += `
                        <tr>
                        <td> ${requestPrice.id} </td>
                        <td> ${requestPrice.name} </td>
                        <td> ${requestPrice.phone} </td>
                        <td> ${requestPrice.email} </td>
                        <td> ${requestPrice.nameProduct} </td>
                        <td> ${requestPrice.nameCompany} </td>
                        <td> ${requestPrice.address} </td> 
                        <td> ${requestPrice.file} </td>
                        <td>
                              <span name="${requestPrice.id}" class="delete-request-page" style="cursor: pointer;color: red">Xóa</span>&nbsp;
                        </td>
                        </tr>
                    `;
                })

                $("#row-request-price").html(contentRow);

                //===== delete =======
                deleteRequestPrice();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

//============ Delete PRODUCT ========================
function deleteRequestPrice() {
    $('.delete-request-page').click(function () {
        const id = $(this).attr("name");
        console.log("id-request-price " + id);
        $.ajax({
            type: "PUT",
            headers: {
                "adminbksoftwarevn": tokenHeader_value,
            },
            contentType: "application/json",
            url: "api/v1/admin/request-price/delete?id=" + id,
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
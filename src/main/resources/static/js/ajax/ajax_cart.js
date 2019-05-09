$(document).ready(function () {
    findAllCartPage(1);
    findAllPagCartNumber();
});

//==================================page=============================.unbind('click')
function pageCart(size) {
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

function findAllPagCartNumber() {
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": tokenHeader_value,
        },
        url: "api/v1/public/buy-form/size",
        success: function (size) {
            console.log(size);
            pageCart(size);
            $('.page').click(function () {
                const page = $(this).attr("name");
                for (let i = 1; i <= size; i++) {
                    var id = "#_" + i;
                    $(id).removeClass("active");
                }
                $(this).addClass("active");
                findAllCartPage(page);
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}


function findAllCartPage(page) {
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": tokenHeader_value,
        },
        url: "api/v1/public/buy-form?page="+page,
        success: function (buyforms) {

            $("#column-cart").html(
                "<td>STT</td>" +
                "<td>Tên</td>" +
                "<td>Phone</td>" +
                "<td>email</td>" +
                "<td>Địa chỉ</td>" +
                "<td>Ghi chú</td>" +
                "<td>Tên sản phẩm</td>" +
                "<td>Số lượng</td>" +
                "<td>Giá</td>" +
                "<td>Chức năng</td>"
            );

            const listSize = Object.keys(buyforms).length;

            if (listSize > 0) {


                let contentRow = '';

                buyforms.map(function (buyform) {
                    contentRow += `
                            <tr>
                            <td>${buyform.id} </td>
                            <td>${buyform.name} </td>
                            <td>${buyform.phoneNumber} </td>
                            <td>${buyform.email} </td>
                            <td>${buyform.address} </td>
                            <td>${buyform.note} </td>
                            <td>${buyform.products} </td>
                            <td>${buyform.quantity} </td>
                            <td>${buyform.price} </td>
                            
                            <td>
                                  <a href="send-mail?id=${buyform   .id}"  style="cursor: pointer;color: #4285F4" target="_blank">Gửi email</a>&nbsp;
                                  <span name="${buyform.id}" class="delete-cart" style="cursor: pointer;color: red">Xóa</span>&nbsp;
                            </td>
                            </tr>
                        `;
                });
                $("#row-cart").html(contentRow);
                $(".body-main .table-responsive tr td").css({
                    "max-width": "260px",
                    "overflow": "-webkit-paged-y"
                });

                //===== delete =======
                deleteCart();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

//============ Delete PRODUCT ========================
function deleteCart() {
    $('.delete-cart').click(function () {
        const id = $(this).attr("name");
        console.log("id-cart " + id);
        $.ajax({
            type: "PUT",
            headers: {
                "adminbksoftwarevn": tokenHeader_value,
            },
            contentType: "application/json",
            url: "api/v1/public/buy-form/delete?id-product=" + id,
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

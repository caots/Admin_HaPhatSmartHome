$(document).ready(function () {
    findAllPageProductNumber();
    searchProductByName(1);
});

//==================================page=====================================

function pageProduct(size) {
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

function findAllPageProductNumber() {
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": tokenHeader_value,
        },
        url: "api/v1/public/products/size",
        success: function (size) {

            findAllProductBySize(1);
            pageProduct(size);
            $('.page').click(function () {
                const page = $(this).attr("name");
                for (let i = 1; i <= size; i++) {
                    var id = "#_" + i;
                    $(id).removeClass("active");
                }
                $(this).addClass("active");
                findAllProductBySize(page);
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

function findAllPageProductByNameNumber() {
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": tokenHeader_value,
        },
        url: "api/v1/public/products/name/size",
        success: function (size) {
            pageProduct(size);
            $('.page').click(function () {
                    const page = $(this).attr("name");
                    searchProductByName(page);
                }
            );
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

//============ FIND ALL PRODUCT ========================
function findAllProductBySize(page) {
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": tokenHeader_value,
        },
        url: "api/v1/public/products/find-all?page=" + page,
        success: function (products) {
            displayOnTable(products);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    })
}


function displayOnTable(products) {
    $("#column-product").html(
        "<td>STT </td>" +
        "<td>Tên </td>" +
        "<td>Tình Trạng </td>" +
        "<td>Bảo hành</td>" +
        "<td>Giá Gốc</td>" +
        "<td>Giá Bán </td>" +
        "<td>Giới thiệu </td>" +
        "<td>Chức năng</td>"
    );
    const listSize = Object.keys(products).length;
    if (listSize > 0) {

        let contentRow;
        products.map(function (product) {
            contentRow += `
                         <tr>
                          <td> ${product.id} </td>
                         <td> ${product.name} </td>
                         <td> ${product.productStatus} </td>
                         <td> ${product.guarantee} </td>
                         <td> ${product.originCost} </td>
                         <td> ${product.saleCost} </td>
                         <td> ${product.present} </td>
                         <td>
                              <a href="create-news-product?id=${product.id}" name="${product.id}" style="cursor: pointer;color: green">Thêm Bài viết</a>&nbsp;
                              <a href="create-info-product?id=${product.id}" name="${product.id}" style="cursor: pointer;color: #17a2b8">THông số kĩ thuật</a>&nbsp;
                              <a href="update-product?id=${product.id}" name="${product.id}"  class="update-product" style="cursor: pointer;color: #4285F4">Chỉnh sửa</a>&nbsp;
                              <span name="${product.id}" class="delete-product" style="cursor: pointer;color: red">Xóa</span>&nbsp;
                        </td>
                        </tr>
                       `;
        });
        $("#row-product").html(contentRow);
        $(".body-main .table-responsive tr td").css({
            "max-width": "260px",
            "overflow": "-webkit-paged-y"
        });
//===== delete =======
        deleteProduct();
        deleteDetailsProduct();
    }
}

//============ Delete PRODUCT ========================
function deleteProduct() {
    $('.delete-product').click(function () {
        const id = $(this).attr("name");
        console.log("id-product " + id);
        $.ajax({
            type: "PUT",
            headers: {
                "adminbksoftwarevn": tokenHeader_value,
            },
            contentType: "application/json",
            url: "api/v1/admin/delete-product?id=" + id,
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

//=========================== SEARCH BY NAME ===================================
function searchProductByName(page) {
    $("#input-search").keypress(function (event) {
        const keycode = event.keycode ? event.keycode : event.which;
        if (keycode == 13) {
            const nameProduct = $('#input-search').val();
            $.ajax({
                type: "GET",
                headers: {
                    "adminbksoftwarevn": tokenHeader_value,
                },
                url: "api/v1/public/products?name=" + nameProduct + "&page=" + page,
                success: function (products) {
                    findAllPageProductByNameNumber();
                    displayOnTable(products);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    errMess(jqXHR, textStatus, errorThrown);
                }
            })
        }
    });
}

//============ DELETE DETAILS  PRODUCT ========================
function deleteDetailsProduct() {
    $('.delete-product').click(function () {
        const id = $(this).attr("name");
        $.ajax({
            type: "PUT",
            headers: {
                "adminbksoftwarevn": tokenHeader_value,
            },
            contentType: "application/json",
            url: "api/v1/admin/product-details/delete-details-product?id=" + id,
            timeout: 30000,
            success: function () {
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
            }
        });
    });
}



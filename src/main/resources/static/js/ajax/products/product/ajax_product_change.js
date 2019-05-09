$(document).ready(function () {
    findAllPartner();
    clickBtnProductChangeSubmit();

});
//============ CREATE PRODUCT ========================

var uploadFile = async (file) => {
    let data;
    await $.ajax({
        type: "POST",
        headers: {
            "adminbksoftwarevn": tokenHeader_value,
        },
        url: "api/v1/public/upload-file",
        enctype: 'multipart/form-data',
        data: file,
        cache: false,
        processData: false,
        contentType: false,
        success: function (result) {
            data = result
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
    return data;
};

function createProduct() {

    let idSmallCategory = '';
    $('#small-category-value').click(function () {
        idSmallCategory = $(this).val();
    });
    let idPartner = '';
    $('#partner-value').click(function () {
        idPartner = $(this).val();
    });

    $('#btn-create-product').click(function () {

            const nameProduct = $("#name-product").val();
            const originCost = $('#origin-cost').val();
            const saleCost = $('#sale-cost').val();

            var formImg = $('#btn-img-product')[0];
            var formData = new FormData(formImg);

            uploadFile(formData).then(function (data) {
                var product = {
                    "name": nameProduct,
                    "originCost": originCost,
                    "saleCost": saleCost,
                    "imgUrl": data,
                };
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    headers: {
                        "adminbksoftwarevn": tokenHeader_value,
                    },
                    url: "api/v1/admin/product?small-category-id=" + idSmallCategory + "&partner-id=" + idPartner,
                    data: JSON.stringify(product),
                    cache: false,
                    timeout: 300000,
                    success: function (data) {
                        console.log(data.name);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        errMess(jqXHR, textStatus, errorThrown);
                    }
                }).then(function (data) {
                    createDetailsProduct(data.id);
                })
            });
        }
    );
}

// ============ FIND PRODUCT BY ID ===================

function findProductById(id) {
    $.ajax({
        type: "GET",
        dataType: "json",
        headers: {
            "adminbksoftwarevn": tokenHeader_value,
        },
        url: "api/v1/public/products/findProductById?id=" + id,
        timeout: 30000,
        success: function (result) {
            updateProduct(result);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    })
}

//============ UPDATE PRODUCT ========================
function updateProduct(data) {
    $("#big-category-value").prop("disabled", true);
    $("#medium-category-value").prop("disabled", true);
    $("#small-category-value").prop("disabled", true);
    $("#partner-value").prop("disabled", true);

    $('#name-product').val(data.name);
    $('#origin-cost').val(data.originCost);
    $('#sale-cost').val(data.saleCost);

    $('#btn-create-product').click(function () {
        data.name = $('#name-product').val();
        data.originCost = $('#origin-cost').val();
        data.saleCost = $('#sale-cost').val();

        var formImgUpdate = $('#btn-img-product')[0];
        var formDataUpdate = new FormData(formImgUpdate);

        uploadFile(formDataUpdate).then(function (urlImg) {
            data.imgUrl = urlImg;
            $.ajax({
                type: "PUT",
                headers: {
                    "adminbksoftwarevn": tokenHeader_value,
                },
                contentType: "application/json",
                url: "api/v1/admin/product",
                data: JSON.stringify(data),
                timeout: 30000,
                success: function () {
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    errMess(jqXHR, textStatus, errorThrown);
                }
            });
        })
    });
}

function clickBtnProductChangeSubmit() {
    const urlCreateProduct = window.location.href;
    console.log(urlCreateProduct);
    var str = urlCreateProduct.split("=");
    const id = str[str.length - 1];
    if ((id - 1) >= 0) {
        findProductById(id);
        findDetailsProductById(id)
    } else {
        createProduct();
    }
}

function findAllPartner() {
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": tokenHeader_value,
        },
        url: "api/v1/public/partner/all",
        success: function (partners) {
            const listSize = Object.keys(partners).length;
            if (partners.check == "fail") {
                alert("Category isEmpty! Name not found!");
                return;
            }
            if (listSize > 0) {

                let contentRow = '';
                partners.map(function (partner) {
                    contentRow += `
                       <option value ="${partner.id}" >${partner.name}</option>
                    `;
                });
                $("#partner-value").html(contentRow);
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}


//============ CREATE DETAILS PRODUCT ========================

function createDetailsProduct(id) {
    let productStatus = '';
    $('#product-status').click(function () {
        productStatus = $(this).val();
    });
    console.log(id);

    const guarantee = $("#guarantee-value").val();
    const present = $('#present-value').val();
    const detailsProduct = {
        "productStatus": productStatus,
        "guarantee": guarantee,
        "present": present
    };
    $.ajax({
        type: "POST",
        headers: {
            "adminbksoftwarevn": tokenHeader_value,
        },
        contentType: "application/json",
        url: "api/v1/admin/product-details?product-id=" + id,
        data: JSON.stringify(detailsProduct),
        cache: false,
        timeout: 300000,
        success: function (data) {
            alert('CREATE PRODUCT SUCCESS');
            $("#btn-create-product").prop("disabled", true);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
            $("#btn-create-product").prop("disabled", true);
        }
    })
}

// ============ FIND DETAILS PRODUCT BY ID ===================

function findDetailsProductById(id) {

    $.ajax({
        type: "GET",
        dataType: "json",
        headers: {
            "adminbksoftwarevn": tokenHeader_value,
        },
        url: "api/v1/public/products/find-details-product-by-id?id=" + id,
        timeout: 30000,
        success: function (result) {
            updateDetailsProduct(result);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

//============ UPDATE DETAILS PRODUCT ========================
function updateDetailsProduct(data) {

    $('#product-status').val(data.productStatus);
    $('#guarantee-value').val(data.guarantee);
    $('#present-value').val(data.present);
    $('#btn-create-product').click(function () {
        data.productStatus = $('#product-status').val();
        data.guarantee = $('#guarantee-value').val();
        data.present = $('#present-value').val();
        console.log(data);
        $.ajax({
            type: "PUT",
            headers: {
                "adminbksoftwarevn": tokenHeader_value,
            },
            contentType: "application/json",
            url: "api/v1/admin/product-details",
            data: JSON.stringify(data),
            timeout: 30000,
            success: function () {
                alert('UPDATE PRODUCT SUCCESS');
                $('#btn-create-product').prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
                alert("UPDATE ERROR ");
                $('#btn-create-product').prop("disabled", true);

            }
        });
    });
}

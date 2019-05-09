// $(document).ready(function () {
//     productAllCart();
//     addProductInCart();
// });
//
// const URL_API = 'http://123.31.45.240:8686/admin_haphatsmarthome_com/';
//
// function productAllCart() {
//     $.ajax({
//         type: 'GET',
//         contentType: "application/json",
//         url: URL_API+"api/v1/public/cart/find-all-cart",
//         headers: {
//             "adminbksoftwarevn": tokenHeader_value,
//         },
//         timeout: 30000,
//         success: function (result) {
//             console.log(result);
//         },
//         error: function (jqXHR, textStatus, errorThrown) {
//             console.log(errorThrown);
//         }
//     })
// }
//
// function addProductInCart(productId) {
//     $.ajax({
//         type: 'GET',
//         contentType: "application/json",
//         url: URL_API+ "api/v1/public/cart/addProductCart?productId=1",
//         headers: {
//             "adminbksoftwarevn": tokenHeader_value,
//         },
//         timeout: 30000,
//         success: function (result) {
//             console.log(result);
//         },
//         error: function (jqXHR, textStatus, errorThrown) {
//             console.log(errorThrown);
//         }
//     })
// }
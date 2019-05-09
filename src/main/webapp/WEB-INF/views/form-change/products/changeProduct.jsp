<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script src="resources/js/ajax/category/ajax_category_create_select.js"></script>
<script src="resources/js/ajax/products/product/ajax_product_change.js"></script>
<style type="text/css" >
    .myInput{
        width: 28%;
    }
</style>
<div class="container">
    <div class="form-product">
        <button type="button" class="btn btn-primary">
            <a href="product" style="text-decoration: none;color: white">Quay lại</a>
        </button>
        <div class="title">
            <h2 class=" font-weight-bold">Thêm sản phẩm</h2>
        </div>
        <div class="name-and-partner">
            <div class="myInput">
                <p>Tên sản phẩm:</p>
                <input type="text" class="form-control" name="name" id="name-product" placeholder="Tên sản phẩm">
            </div>
            <div class="myInput">
                <p>Hãng sản xuất:</p>
                <select class="form-control" name="partner" id="partner-value"></select>
            </div>

            <div class="myInput">
                <p>Tình trạng </p>
                <select class="form-control" id="product-status" name="product-status-value">
                    <option value="true">true</option>
                    <option value="false">false</option>
                </select>
            </div>
        </div>

        <div class="double-input">
            <div class="myInput">
                <p>Giá gốc:</p>
                <input type="number" class="form-control" name="origin-cost" id="origin-cost" placeholder="Giá gốc">
            </div>
            <div class="myInput">
                <p>Giá khuyến mãi:</p>
                <input type="number" class="form-control" name="sale-cost" id="sale-cost"
                       placeholder="Giá khuyến mãi">
            </div>

            <div class="myInput">
                <p>Thời gian bảo hành</p>
                <input type="number" class="form-control" name="name" id="guarantee-value" placeholder="Bảo hành">
            </div>
        </div>

        <div class="double-input">
            <div class="myInput">
                <p>Chi tiết loại sản phẩm</p>
                <select class="form-control" placeholder="small category" id="small-category-value">
                </select>
            </div>
            <div class="myInput">
                <p>Giới thiệu </p>
                <textarea style="max-height: 190px" class="form-control" rows="5" id="present-value"></textarea>

            </div>
            <div class="myInput" style="padding-top: 50px;">
                <form method="POST" action="" enctype="multipart/form-data" id="btn-img-product">
                    <div>
                        <label for="image-product-value">Chọn file ảnh :</label>
                        <input type="file" id="image-product-value" name="file" multiple="multiple"/>
                    </div>
                </form>
            </div>
        </div>
        <div class="submit">
            <button type="button" class="btn btn-success" id="btn-create-product">Hoàn Tất</button>
        </div>
    </div>
</div>

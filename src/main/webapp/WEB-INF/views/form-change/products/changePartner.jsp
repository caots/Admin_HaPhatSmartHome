<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script src="resources/js/ajax/products/partner/ajax_partner_change.js"></script>
<div class="container">
    <div class="form-product">
        <button type="button" class="btn btn-primary">
            <a href="partner" style="text-decoration: none;color: white">Quay lại</a>
        </button>
        <div class="title">
            <h2 class=" font-weight-bold">Thêm Đối Tác </h2>
        </div>
        <div class="name-and-partner">
            <div class="myInput">
                <p>Tên Đối Tác :</p>
                <input type="text" class="form-control" name="name" id="name-partner-value" placeholder="Tên đối tác ">
            </div>
            <div class="myInput">
                <p>Giới thiệu:</p>
                <input type="text" class="form-control" name="name" id="present-partner-value" placeholder="Mô tả ">
            </div>
        </div>

        <div class="double-input">
            <div class="myInput">
                <p>Ảnh đại diện:</p>
                <input type="file" class="form-control-file" name="image" id="image-partner-value">
            </div>
        </div>

        <div class="submit">
            <button type="button" class="btn btn-success" id="btn-create-partner">Hoàn Tất</button>
        </div>
    </div>

</div>
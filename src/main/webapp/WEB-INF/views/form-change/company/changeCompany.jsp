<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script src="resources/js/ajax/company/ajax_company_change.js"></script>
<div class="container">
    <div class="form-product">
        <button type="button" class="btn btn-primary">
            <a href="company" style="text-decoration: none;color: white">Quay lại</a>
        </button>
        <div class="title">
            <h2 class=" font-weight-bold">Thêm Công Ty </h2>
        </div>
        <div class="double-input">
            <div class="myInput">
                <p>Tên Công Ty:</p>
                <input type="text" class="form-control" name="name" id="name-company-value" placeholder="Tên công ty ">
            </div>
            <div class="myInput">
                <p>Địa Chỉ:</p>
                <input type="text" class="form-control" name="name" id="address-company-value" placeholder="Địa chỉ">
            </div>
        </div>

        <div class="double-input">
            <div class="myInput">
                <p>Hotline: </p>
                <input type="number" class="form-control" name="name" id="hotline-company-value" placeholder="Hotline ">
            </div>
            <div class="myInput">
                <p>Email:</p>
                <input type="email" class="form-control" name="name" id="email-company-value" placeholder="Email">
            </div>
        </div>
        <div class="submit">
            <button type="button" class="btn btn-success" id="btn-create-company">Hoàn Tất</button>
        </div>
    </div>
</div>
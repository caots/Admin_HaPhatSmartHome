<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script src="resources/js/ajax/footer/detailsFooter/ajax_details_footer_change.js"></script>

<div class="container">
    <div class="form-product">
        <button type="button" class="btn btn-primary" id="btnBack">
            <a href="details-footer" style="text-decoration: none;color: white;">  Quay lại</a>
        </button>
        <div class="title">
            <h2 class=" font-weight-bold">Thêm Chi tiết Cuối Trang</h2>
        </div>

        <div class="double-input">
            <div class="myInput">
                <p>Tên</p>
                <input type="text" class="form-control" name="name" id="name-details-footer-value" placeholder="tên">
            </div>
            <div class="myInput">
                <p>Tên Cuối Trang</p>
                <select class="form-control" name="footer" id="footer-value"></select>
            </div>
        </div>
        <div class="submit">
            <button type="button" class="btn btn-success" id="btn-create-details-footer">Hoàn Tất</button>
        </div>
    </div>

</div>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script src="resources/js/ajax/category/big_category/ajax_big_category.js"></script>
<h3 class="title-body" style="text-align: center">
    Quản Lý Danh Mục Sản Phẩm
</h3>
<button type="button" class="btn btn-success float-left btn-search">
    <a href="create-category" style="text-decoration: none;color: white">
        <i class="fas fa-plus"></i>Thêm
    </a>
</button>
<!-- TABLE -->
<div class="table-responsive" style="overflow-x:auto;overflow-y: auto">
    <table class="table text-center">
        <thead>
        <tr id="column-big-category" style="font-weight: 600"></tr>
        </thead>
        <tbody id="row-big-category"></tbody>
    </table>
</div>
<!-- END_TABLE -->
<div class="pageable">
    <ul class="pagination"></ul>
</div>
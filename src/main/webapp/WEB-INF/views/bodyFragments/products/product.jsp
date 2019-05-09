<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script src="resources/js/ajax/products/product/ajax_products.js"></script>
<link type="text/css" href="resources/css/bodyFragments/product.css" rel="stylesheet">
<h3 class="title-body" style="text-align: center">
    Quản Lý Sản phẩm
</h3>
<div class="from-find float-right">
    <input type="text" name="search" id="input-search" placeholder="tìm kiếm..."
           class="input-search float-left">
    <div class="fas fa-search float-right" id="icon-search"></div>
</div>
<button type="button" class="btn btn-success float-left btn-search">
    <a href="create-product" style="text-decoration: none;color: white">
        Thêm sản phẩm
    </a>
</button>

<!-- TABLE -->
<div class="table table-responsive" style="overflow-x:auto;overflow-y: auto">
    <table class=" text-center">
        <thead>
        <tr id="column-product" style="font-weight: 600"></tr>
        </thead>
        <tbody id="row-product"></tbody>
    </table>
</div>
<!-- END_TABLE -->
<div class="pageable">
    <ul class="pagination">
    </ul>
</div>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script src="resources/js/ajax/products/product/ajax_create_info_product.js"></script>
<script src="//cdn.ckeditor.com/4.11.4/full/ckeditor.js"></script>

<center><h1>Thông số kĩ thuật</h1></center>

<br>
<textarea id="editor" name="editor" cols="80" rows="10"></textarea>

<div class="submit">
    <button type="button" class="btn btn-success"  id="btn-create-news-product" >Hoàn Tất</button>
    <button type="button" class="btn btn-primary float-right">
        <a href="product" style="text-decoration: none;color: white">Quay lại</a>
    </button>
</div>



<script>
    CKEDITOR.replace('editor', {});
</script>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script src="resources/js/ajax/footer/detailsFooter/ajax_create_news_footer.js"></script>
<script src="//cdn.ckeditor.com/4.11.4/full/ckeditor.js"></script>

<center><h1>Giới thiệu Công Ty</h1></center>
<br>
<br>
<textarea id="editor" name="editor" cols="80" rows="10"></textarea>
<div class="submit">
    <button type="button" class="btn btn-success" id="btn-create-news-footer">Hoàn Tất</button>
    <button type="button" class="btn btn-primary float-right">
        <a href="details-footer" style="text-decoration: none;color: white">Quay lại</a>
    </button>
</div>

<script>
    CKEDITOR.replace('editor', {});
</script>
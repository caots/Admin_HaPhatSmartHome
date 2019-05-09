<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script src="resources/js/ajax/ajax_send_mail.js"></script>

<div class="create-category">
    <div class="row ">
        <div class="col-md-2"></div>
        <div class="col-md-9">
            <center><h1>Gửi Email khách hàng</h1></center>
            <div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" class="form-control" id="email">
                </div>

                <div class="form-group">
                    <label for="header-mail">Tiêu đề</label>
                    <input type="header" class="form-control" id="header-mail">
                </div>

                <div class="form-group">
                    <label for="content-mail">Nội dung</label>
                    <textarea class="form-control" id="content-mail"></textarea>
                </div>
            </div>
            <div class="text-center">
                <button type="submit" id="send-email-btn" class="btn btn-primary">Submit</button>
            </div>

        </div>
    </div>
</div>
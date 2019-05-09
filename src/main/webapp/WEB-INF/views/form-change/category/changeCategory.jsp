<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script src="resources/js/ajax/category/ajax_category_create_select.js"></script>
<script src="resources/js/ajax/category/big_category/ajax_big_category_change.js"></script>
<script src="resources/js/ajax/category/medium_category/ajax_medium_category_change.js"></script>
<script src="resources/js/ajax/category/small_category/ajax_small_category_change.js"></script>
<div class="create-category">
    <div class="row ">
        <div class="col-md-2"></div>
        <div class="col-md-9">
            <h1>Thêm Danh Mục Lớn </h1>
            <div class="category">
                <div class="form-group row">
                    <div class="col-md-7">
                        <label><h5>Big category</h5></label>
                        <input class="form-control" placeholder="big category" id="name-big-category">
                    </div>

                    <div class="col-md-2">
                        <button type="button" class="btn btn-lg btn-primary" id="btn-create-big-category">Submit
                        </button>
                    </div>
                </div>
            </div>
            <h1 style="margin-top: 40px">Thêm Danh Mục Vừa </h1>
            <div class="category">
                <div class="form-group row">
                    <div class="col-md-6">
                        <label><h5>Medium Category</h5></label>
                        <input class="form-control" placeholder="medium category" id="name-medium-category">
                    </div>
                    <div class="col-md-4">
                        <label><h5>Big Category</h5></label>
                        <select class="form-control" id="big-category-value"></select>
                    </div>
                    <div class="col-md-2">
                        <button type="button" class="btn btn-lg btn-primary"  id="btn-create-medium-category">Submit</button>
                    </div>

                </div>
            </div>
            <h1 style="margin-top: 40px">Thêm Danh Mục Bé</h1>
            <div class="category">
                <div class="form-group row">
                    <div class="col-md-6">
                        <label><h5>Small Category </h5></label>
                        <input class="form-control" placeholder="small category"  id="name-small-category">
                    </div>
                    <div class="col-md-4">
                        <label><h5>medium Category</h5></label>
                        <select class="form-control"  id="medium-category-value"> </select>
                    </div>
                    <div class="col-md-2">
                        <button type="button" class="btn btn-lg btn-primary" id="btn-create-small-category">Submit</button>
                    </div>

                </div>
            </div>
            <div style="text-align: center">
                <a href="big-category">
                    <button type="button" class="btn btn-lg btn-warning">Thoát</button>
                </a>
            </div>

        </div>
        <div class="col-md-2"></div>
    </div>
</div>
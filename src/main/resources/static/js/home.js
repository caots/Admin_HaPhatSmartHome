

$(function () {
    //responsive
    var statusMenu = true;
    var height = $(window).height();
    var width = $(window).width();
    if (height < 780) {
        if (width > 768) {
            $(".set-height").css("height", 780);
        } else {
            $(".set-height").css("height", 880);
        }
    } else {
        $(".set-height").css("height", height - 50);
    }
    $(window).resize(function () {
        height = $(window).height();
        width = $(window).width();
        if (height < 780) {
            if (width > 768) {
                $(".set-height").css("height", 780);
            } else {
                $(".set-height").css("height", 880);
            }
        } else {
            $(".set-height").css("height", height - 50);
        }
    })
    //toggle-user-menu



    $(".infor-admin").click(function () {
        $(".user-menu").toggleClass("not-view");
    })
    //not-load-with-tag-a
    $("a").click(function () {
        //do something
    })

    //click-menu-right dùng id hoặc key khi click vào sẽ lấy ra key id để set lại
    //chưa hoàn thành

    //push-menu-right & pull-display-main
    $("#bar-click").click(function () {
        if (statusMenu) {
            //change-col
            $(".menu-right").removeClass("col-5 col-sm-4 col-md-3 col-lg-3 col-xl-2");
            $(".menu-right").addClass("col-1 col-sm-1 col-md-1 col-lg-1 col-xl-1");

            $(".display-main").removeClass("col-7 col-sm-8 col-md-9 col-lg-9 col-xl-10");
            $(".display-main").addClass("col-11 col-sm-11 col-md-11 col-lg-11 col-xl-11");

            //change logo
            $(".logo-top img").attr("src", "resources/img/home-logo-bksoftware-responsive.png");

            //hide-text
            $(".menu-list ul > li > a > span").css("display", "none");
            $(".menu-list ul > li > a").css("padding-left", "6px");
            $(".menu-list ul > li").css("text-align", "center");
        } else {
            //change-col
            $(".menu-right").removeClass("col-1 col-sm-1 col-md-1 col-lg-1 col-xl-1");
            $(".menu-right").addClass("col-5 col-sm-4 col-md-3 col-lg-3 col-xl-2");

            $(".display-main").removeClass("col-11 col-sm-11 col-md-11 col-lg-11 col-xl-11");
            $(".display-main").addClass("col-7 col-sm-8 col-md-9 col-lg-9 col-xl-10");

            //change-logo
            $(".logo-top img").attr("src", "resources/img/home-logo-bksoftware.png");

            //view-text
            $(".menu-list ul > li > a > span").css("display", "inline");
            $(".menu-list ul > li > a").css("padding-left", "8px");
            $(".menu-list ul > li").css("text-align", "unset");
        }
        statusMenu = !statusMenu;
    })

    //click-menu-right
    $(".menu-list > ul > li > a").click(function (event) {
        let idMenuClick = event.currentTarget.id; //click vào con lấy cha current
        if (idMenuClick != '') {
            $("#" + idMenuClick).addClass("menu-iteam-click");
            if ( $("#" + idMenuClick).next().length != 0) {
                $("#" + idMenuClick).next().removeClass("not-view");
            }
            $(".menu-list > ul > li > a").map((index, data) => {
                if (idMenuClick != data.id && data.id != '') {
                $("#" + data.id).removeClass("menu-iteam-click");
                if ( $("#" + data.id).next().length != 0) {
                    $("#" + data.id).next().addClass("not-view");
                }
            }
        })
        }
    })

    //click-menu-extra

    //lay cha dang duoc hien thi
    $(".menu-extra > li > a").click(function(event) {
        let idMenuExtraClick = event.currentTarget.id;
        if (idMenuExtraClick != '') {
            $("#"+idMenuExtraClick).addClass("menu-extra-click");
            $(".menu-extra > li > a").map((index, data) => {
                if (idMenuExtraClick != data.id && data.id != '') {
                $("#"+data.id).removeClass("menu-extra-click");
            }
        })
        }
    });

    //pageable
    $(".pageable ul.pagination li a").click(function (event) {
        let idClick = event.target.id;
        let textClick = "1";
        if (idClick != '') {
            switch (idClick) {
                case "prev":
                    textClick = $(".pageable ul.pagination li a.active").text();
                    if (textClick - 1 > 0) {
                        $("#_" + textClick).removeClass("active");
                        $("#_" + (textClick - 1)).addClass("active");
                    }
                    break;
                case "next":
                    textClick = $(".pageable ul.pagination li a.active").text();
                    if (textClick - 0 + 1 <= 5) {
                        $("#_" + textClick).removeClass("active");
                        $("#_" + (textClick - 0 + 1)).addClass("active"); //-0 để chuyển về số
                    }
                    break;
                default:
                    $(".pageable ul.pagination li a").map((index, data) => {
                        if (idClick != data.id) {
                    $("#" + data.id).removeClass("active");
                } else {
                    $("#" + idClick).addClass("active");
                }
            })
        }
        }
    })
});

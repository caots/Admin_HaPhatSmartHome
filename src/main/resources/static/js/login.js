$(document).ready(function () {
    //responsie
    let height = $(window).height();
    $(".img-background").css("height", height);
    $(window).resize(function () {
        height = $(window).height();
        if (height > 564) {
            $(".img-background").css("height", height);
        } else {
            $(".img-background").css("height", 635);
        }
    });
    $(".input-login").keypress(function (event) {
        let keycode = (event.keyCode ? event.keyCode : event.which);
        if (keycode == '13') {
            onSubmit();
        }
    });
    $(".btn-login .btn").click(function (event) {
        onSubmit(event);
    })
});
//end_reponsive
//action when click or enter
function onSubmit(event) {


    var username = $("#username").val();
    var password = $("#password").val();


    if (username.length == 0 || password.length == 0) {
        alert("username or password is blank!");
        event.preventDefault(event);
        return;
    }

    const loginForm = {
        "username": username,
        "password": password
    };

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/v1/public/login",
        data: JSON.stringify(loginForm),
        cache: false,
        timeout: 300000,
        success: function (data) {
            document.cookie = "token="+data;
            if (data == "fail") {
                alert("login fail");
            }
            location.href = "home";
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
            alert("error");
            event.preventDefault(event);
        }
    });
}


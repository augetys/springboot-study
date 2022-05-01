function setUserInfo() {
    $.ajax({
        type: 'GET',
        url: 'user/getUserInfo',
        contentType: "application/json",
        dataType: 'json',
        success: function (data) {
            console.log("获取用户信息...");
            if (data.code === 200) {
                var userInfo = data.data;
                userId = userInfo.userId;
                $("#username").html(userInfo.username);
                $("#avatarUrl").attr("src", userInfo.icon);
            } else {
                alert(data.message);
            }
        }
    });
}

function logout() {
    // 1. 关闭websocket连接

    // 2. 注销登录状态
    $.ajax({
        type: 'GET',
        url: 'user/logout',
        dataType: 'json',
        async: true,
        success: function (data) {
            if (data.code === 200) {
                // 3. 注销成功，进行页面跳转
                console.log("注销成功！");
                window.location.href = "login";
            } else {
                alert(data.message);
            }
        }
    });
}
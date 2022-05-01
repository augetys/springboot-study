function login() {
    var user = {
        username: $.trim($("#username").val()),
        password: $.trim($("#password").val())
    };
    $.ajax({
        type: 'POST',
        url: 'user/login',
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify(user),
        async: false,
        success: function (data) {
            if (data.code === 200) {
                if (data.data.type === 1) {
                    window.location.href = "call";
                }
                if (data.data.type === 0) {
                    window.location.href = "user";
                }
            } else {
                alert(data.message);
            }
        }
    });
}



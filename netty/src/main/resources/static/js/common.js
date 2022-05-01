var ws = {
    register: function () {
        if (!window.WebSocket) {
            return;
        }
        if (socket.readyState == WebSocket.OPEN) {
            var data = {
                "userId": user.id,
                "type": "0"
            };
            socket.send(JSON.stringify(data));
        } else {
            alert("Websocket连接没有开启！");
        }
    },

    singleSend: function (fromUserId, toUserId, content) {
        if (!window.WebSocket) {
            return;
        }
        if (socket.readyState == WebSocket.OPEN) {
            var data = {
                "fromUserId": fromUserId,
                "toUserId": toUserId,
                "content": content,
                "type": "1"
            };
            socket.send(JSON.stringify(data));
        } else {
            alert("Websocket连接没有开启！");
        }
    }
}

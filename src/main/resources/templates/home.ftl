<!DOCTYPE html>
<html>
<head>
    <title>Hello WebSocket</title>
    <script src="//cdn.jsdelivr.net/sockjs/0.3.4/sockjs.min.js"></script>
</head>
<body>
<noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript being enabled. Please enable
    Javascript and reload this page!</h2></noscript>

<script>
    var sock = new SockJS('/hello');
    sock.onopen = function() {
        console.log('open');
    };
    sock.onmessage = function(e) {
        console.log('message', e.data);
    };
    sock.onclose = function() {
        console.log('close');
    };

    sock.send('test');
    sock.close();
</script>

<div>
    <div>
        <button id="connect" onclick="connect();">Connect</button>
        <button id="disconnect" disabled="disabled" onclick="disconnect();">Disconnect</button>
    </div>
</div>
</body>
</html>
<script>
//    var sock = new SockJS('/hello');
//    sock.onopen = function() {
//        console.log('open');
//    };
//    sock.onmessage = function(e) {
//        console.log('message', e.data);
//    };
//    sock.onclose = function() {
//        console.log('close');
//    };
//
//    sock.send('test');
//    sock.close();
</script>

<div>
    <div>
        <button id="connect" onclick="connect();">Connect</button>
        <button id="disconnect" disabled="disabled" onclick="disconnect();">Disconnect</button>
    </div>
</div>
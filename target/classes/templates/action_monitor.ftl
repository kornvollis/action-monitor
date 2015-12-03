<!DOCTYPE html>
<html>
<head>
    <title>Hello WebSocket</title>

    <#include "header.ftl">

    <script type="text/javascript">
        var ws = null;

        function connect() {
            var target = "/echo";
            ws = new SockJS(target);
            ws.onopen = function () {
                debugger;
                log('Info: WebSocket connection opened.');
            };
            ws.onmessage = function (event) {
                var currentTime = new Date();
                log(currentTime + " " + event.data);
            };
            ws.onclose = function () {
                log('Info: WebSocket connection closed.');
            };
        }

        function disconnect() {
            if (ws != null) {
                ws.close();
                ws = null;
            }
            setConnected(false);
        }

        function log(message) {
            var console = document.getElementById('console');
            var p = document.createElement('p');
            p.style.wordWrap = 'break-word';
            p.appendChild(document.createTextNode(message));
            console.appendChild(p);
            while (console.childNodes.length > 25) {
                console.removeChild(console.firstChild);
            }
            console.scrollTop = console.scrollHeight;
        }
    </script>

</head>
<body>

<#include "navigation.ftl">

<noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript being enabled. Please enable
    Javascript and reload this page!</h2></noscript>

    <div id="connect-container">
        <div id="websocket-status"></div>
        <div>
            <#--<textarea id="message" style="width: 350px">Here is a message!</textarea>-->
        </div>
    </div>
    <div id="console-container">
        <div id="console"></div>
    </div>

    <script>
        connect();
    </script>
</body>
</html>
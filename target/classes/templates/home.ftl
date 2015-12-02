<#import "/spring.ftl" as spring />
<#assign home><@spring.url relativeUrl="/"/></#assign>
<!DOCTYPE html>
<html>
<head>
    <title>Hello WebSocket</title>

    <#assign bootstrapCss><@spring.url relativeUrl="/css/bootstrap.min.css"/></#assign>
    <#assign bootstrapJs><@spring.url relativeUrl="/js/bootstrap.min.js"/></#assign>
    <#assign jquery><@spring.url relativeUrl="/js/jquery-2.1.4.min.js"/></#assign>

    <#--JQuery -->
    <script src="${jquery}"></script>

    <#--SockJS-->
    <script src="//cdn.jsdelivr.net/sockjs/0.3.4/sockjs.min.js"></script>

    <#--Bootstrap-->
    <link rel="stylesheet" href="${bootstrapCss}" />
    <script src="${bootstrapJs}"></script>

    <script type="text/javascript">
        var ws = null;

        function setConnected(connected) {
            document.getElementById('connect').disabled = connected;
            document.getElementById('disconnect').disabled = !connected;
            document.getElementById('echo').disabled = !connected;
        }

        function connect() {
            var target = document.getElementById('target').value;
            ws = new SockJS(target);
            ws.onopen = function () {
                setConnected(true);
                log('Info: WebSocket connection opened.');
            };
            ws.onmessage = function (event) {
                log('Received: ' + event.data);
            };
            ws.onclose = function () {
                setConnected(false);
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

        function echo() {
            if (ws != null) {
                var message = document.getElementById('message').value;
                log('Sent: ' + message);
                ws.send(message);
            } else {
                alert('WebSocket connection not established, please connect.');
            }
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
<noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript being enabled. Please enable
    Javascript and reload this page!</h2></noscript>

    <div id="connect-container">
        <div>
            <input id="target" type="text" size="40" style="width: 350px" value="/echo"/>
        </div>
        <div>
            <button id="connect" onclick="connect();">Connect</button>
            <button id="disconnect" disabled="disabled" onclick="disconnect();">Disconnect</button>
        </div>
        <div>
            <textarea id="message" style="width: 350px">Here is a message!</textarea>
        </div>
        <div>
            <button id="echo" onclick="echo();" disabled="disabled">Echo message</button>
        </div>
    </div>
    <div id="console-container">
        <div id="console"></div>
    </div>

</body>
</html>
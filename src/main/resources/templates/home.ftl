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

</head>
<body>
<noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript being enabled. Please enable
    Javascript and reload this page!</h2></noscript>

    <#include "employees.ftl">

<#--<ul class="nav nav-tabs">-->
    <#--<li role="presentation" class="active"><a href="#">Activity monitor</a>-->
        <#--&lt;#&ndash;<#include "acitivityMonitor.ftl">&ndash;&gt;-->
        <#--xxxx-->
    <#--</li>-->
    <#--<li role="presentation"><a href="#">Employees</a>-->
        <#--&lt;#&ndash;<#include "employees.ftl">&ndash;&gt;-->
        <#--yyyy-->
    <#--</li>-->
<#--</ul>-->

</body>
</html>
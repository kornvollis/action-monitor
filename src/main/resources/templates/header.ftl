<#import "/spring.ftl" as spring />
<#assign home><@spring.url relativeUrl="/"/></#assign>

<#--<#assign bootstrapCss><@spring.url relativeUrl="/css/bootstrap.min.css"/></#assign>-->
<#assign bootstrapJs><@spring.url relativeUrl="/js/bootstrap.min.js"/></#assign>
<#assign jquery><@spring.url relativeUrl="/js/jquery-2.1.4.min.js"/></#assign>

<#--JQuery -->
<script src="${jquery}"></script>

<#--SockJS-->
<script src="//cdn.jsdelivr.net/sockjs/0.3.4/sockjs.min.js"></script>

<#--Bootstrap-->
<#--<link rel="stylesheet" href="${bootstrapCss}" />-->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<script src="${bootstrapJs}"></script>
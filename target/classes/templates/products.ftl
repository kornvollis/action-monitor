<#import "/spring.ftl" as spring />
<!DOCTYPE html>

<html lang="en">
<head>
<title>Home</title>
<#assign home><@spring.url relativeUrl="/"/></#assign>
<#assign bootstrap><@spring.url relativeUrl="/css/bootstrap.min.css"/></#assign>
<link rel="stylesheet" href="${bootstrap}" />
</head>

<body>

Szevaszka

<table class="table">
    <thead>
    <tr>
        <th>Name</th>
    </tr>

    <tbody>

    <#list products as i>
    <tr>
        <td>${i?index}: ${i.getName()}</td>
    </tr>
    </#list>
    </tbody>
</table>


</body>

</html>
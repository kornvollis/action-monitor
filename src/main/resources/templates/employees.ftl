<table class="table">
    <thead>
    <tr>
        <th>#</th>
        <th>First name</th>
        <th>Last name</th>
        <th>email</th>
        <th>actions</th>
    </tr>

    <tbody>

    <#list employes as i>
    <tr>
        <#--<td>${i?index}</td>-->
        <td>${i.getId()}</td>
        <td>${i.getFirstName()}</td>
        <td>${i.getLastName()}</td>
        <td>${i.getEmail()}</td>
        <th><a href="/user/delete/${i.getId()}">delete</a> | update</th>
    </tr>
    </#list>
    </tbody>
</table>
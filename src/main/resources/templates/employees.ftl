<table id="employees" class="table">
    <thead>
    <tr>
        <th>#</th>
        <th>First name</th>
        <th>Last name</th>
        <th>email</th>
        <th>actions</th>
    </tr>

    <tbody>

    <#list employees as i>
    <tr data-id="${i.getId()}">
        <td>${i.getId()}</td>
        <td>${i.getFirstName()}</td>
        <td>${i.getLastName()}</td>
        <td>${i.getEmail()}</td>
        <td>
            <button class="delete-button" data-id="${i.getId()}">delete</button> |
            <button class="update-button" data-id="${i.getId()}" data-toggle="modal" data-target="#updateModal" >update</button>
        </td>
    </tr>
    </#list>

    </tbody>
</table>

<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#addModal">
    Add new employee
</button>

<div id="addModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">New employee</h4>
            </div>
            <div class="modal-body">

                <form id="addEmployeeForm" class="form-horizontal" action="/employee/add" method="post">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">First name</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="firstName" placeholder="Steve">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Last name</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="lastName" placeholder="Jobs">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Email</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="email" placeholder="sjobs@gmail.com">
                        </div>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button id="formSubmissonBtn" type="button" class="btn btn-primary">Add</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div id="updateModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Update employee</h4>
            </div>
            <div class="modal-body">

                <form id="updateEmployeeForm" class="form-horizontal" action="/employee/update" method="post">

                    <input type="text" name="id" style="display: none" />

                    <div class="form-group">
                        <label class="col-sm-2 control-label">First name</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="firstName" placeholder="Steve">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Last name</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="lastName" placeholder="Jobs">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Email</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="email" placeholder="sjobs@gmail.com">
                        </div>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button id="updateFormSubmissonBtn" type="button" class="btn btn-primary">Update</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script>

function updateEmployee(employee) {
    var cols = $("tr[data-id = "+employee.id + "] td");
    $(cols[1]).text(employee.firstName);
    $(cols[2]).text(employee.lastName);
    $(cols[3]).text(employee.email);
}

$("#updateFormSubmissonBtn").on("click", function() {

    var url = "/employee/update/" + $("#updateEmployeeForm input[name ='id']" ).val(); // the script where you handle the form input.

    $.ajax({
        type: "POST",
        url: url,
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify($("#updateEmployeeForm").serializeObject()), // serializes the form's elements.
        success: function(data)
        {
            $("#updateModal").modal("hide");
            updateEmployee(data);
        }
    });
});

$("body").on("click", ".update-button", function() {
    var employeeId = $(this).data("id");
    var cols = $("tr[data-id = " + employeeId + "] td");

    var id = $(cols[0]).text();
    var firstName = $(cols[1]).text();
    var lastName = $(cols[2]).text();
    var email = $(cols[3]).text();

    var populateUpdateForm = function() {
        $("#updateEmployeeForm input[name='id']").val(id);
        $("#updateEmployeeForm input[name='firstName']").val(firstName);
        $("#updateEmployeeForm input[name='lastName']").val(lastName);
        $("#updateEmployeeForm input[name='email']").val(email);
    }

    populateUpdateForm();
});

$("#formSubmissonBtn").click(function() {
    var url = "/employee/add"; // the script where you handle the form input.

    $.ajax({
        type: "POST",
        url: url,
        data: $("#addEmployeeForm").serialize(), // serializes the form's elements.
        success: function(data)
        {
            $("#addModal").modal("hide");

            var row = $("<tr>").attr("data-id", data.id);
            var id = $("<td>").text(data.id);
            var firstName = $("<td>").text(data.firstName);
            var lastName = $("<td>").text(data.lastName);
            var email = $("<td>").text(data.email);
            var submitButton = $("<td>").append($("<button>").attr("class", "delete-button").attr("data-id", data.id).text("delete"))
                    .append(" | ").append($("<button>").attr("class", "update-button").attr("data-id", data.id).attr("data-toggle", "modal").attr("data-target", "#updateModal").text("update"));

            row.append(id).append(firstName).append(lastName).append(email).append(submitButton);

            $("#employees tbody").append(row);
        }
    });
});

$("body").on("click", ".delete-button", function () {
    var button = $(this);
    var id = button.closest("tr").data("id");
    $.get("/employee/delete/" + id, function () {
        button.closest("tr").remove();
    })
});

$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};
</script>
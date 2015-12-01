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

    <#list employes as i>
    <tr>
        <#--<td>${i?index}</td>-->
        <td>${i.getId()}</td>
        <td>${i.getFirstName()}</td>
        <td>${i.getLastName()}</td>
        <td>${i.getEmail()}</td>
        <td><button class="delete-button" data-id="${i.getId()}">delete</button> | update</td>
            <#--<a href="/employee/delete/${i.getId()}">delete</a>-->
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
                            <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Steve">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Last name</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Jobs">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Email</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="email" name="email" placeholder="sjobs@gmail.com">
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


<script>
//$('#add-modal').on('shown.bs.modal', function () {
//    $('#add-modal').focus()
//})
$("#formSubmissonBtn").click(function() {
    var url = "/employee/add"; // the script where you handle the form input.

    $.ajax({
        type: "POST",
        url: url,
        data: $("#addEmployeeForm").serialize(), // serializes the form's elements.
        success: function(data)
        {
            debugger;
            $("#addModal").modal("hide");

            var row = $("<tr>");
            var id = $("<td>").text(data.id);
            var firstName = $("<td>").text(data.firstName);
            var lastName = $("<td>").text(data.lastName);
            var email = $("<td>").text(data.email);
            var submitButton = $("<td>").append($("<button>").attr("class", "delete-button").attr("data-id", data.id).text("delete"));

            row.append(id).append(firstName).append(lastName).append(email).append(submitButton);

            $("#employees tbody").append(row);
        }
    });

})

$("body").on("click", ".delete-button", function () {
    debugger;
    var button = $(this);
    var id = button.data("id");
    $.get("/employee/delete/" + id, function () {
        button.parent().parent().remove();
        //alert("success");
    })
            .done(function () {
                //alert("second success");
            })
            .fail(function () {
                //alert("error");
            })
})
</script>
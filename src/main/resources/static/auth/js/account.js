$(document).ready(function () {
  var table = $("#account-list").DataTable({
    responsive: true,
    lengthMenu: [5, 10, 25, 50, 100],
    columns: [
      { data: "#" },
      { data: "id" },
      { data: "userName" },
      { data: "firstName" },
      { data: "lastName" },
      { data: "email" },
      { data: "phoneNumber" },
      { data: "action" },
    ],
    columnDefs: [{ targets: [7], orderable: false }],
  });

  var tableBody = "#account-list tbody";

  $(tableBody).on("click", "tr", function () {
    var row = table.row(this);
    var data = row.data();

    $("#update-form").find("input[name='id']").val(data.id);
    $("#update-form").find("input[name='userName']").val(data.userName);
    $("#update-form").find("input[name='firstName']").val(data.firstName);
    $("#update-form").find("input[name='lastName']").val(data.lastName);
    $("#update-form").find("input[name='email']").val(data.email);
    $("#update-form").find("input[name='phoneNumber']").val(data.phoneNumber);

    $("#delete-form").find("input[name='id']").val(data.id);
  });

  $('[data-toggle="tooltip"]').tooltip();

  var errorOnAdd = $("#add-form").attr("data-display");

  if (errorOnAdd == "true") {
    $("#addAccountModal").modal("show");
  }

  var errorOnUpdate = $("#update-form").attr("data-display");

  if (errorOnUpdate == "true") {
    $("#editAccountModal").modal("show");
  }
});

$(document).ready(function () {
  const datatable = $("#account-list").DataTable({
    rowReorder: {
      selector: "td:first-child",
    },
    responsive: true,
    lengthMenu: [5, 10, 25, 50, 100],
    columns: [
        { data: 'id' },
        { data: 'username' },
        { data: 'firstname' },
        { data: 'lastname' },
        { data: 'gender' },
        { data: 'email' },
        { data: 'phonenumber' },
        { data: 'image' },
        { data: 'isCustommer' },
        { data: 'isManager' },
        { data: 'isAdmin' }
    ],
    "columnDefs": [ {
      "targets"  : 'no-sort',
      "orderable": false,
    }]
  });
});

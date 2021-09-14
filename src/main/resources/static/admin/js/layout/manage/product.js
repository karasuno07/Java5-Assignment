$(document).ready(function () {
  /* DataTable */
  const datatable = $("#product-list").DataTable({
    rowReorder: {
      selector: "td:first-child",
    },
    responsive: true,
    lengthMenu: [5, 10, 25, 50, 100],
    columns: [
      { data: "productId" },
      { data: "productName" },
      { data: "categoryId" },
      { data: "categoryName" },
      { data: "originId" },
      { data: "originName" },
      { data: "supplierId" },
      { data: "supplierName" },
      { data: "price" },
      { data: "discount" },
      { data: "quantity" },
      { data: "image" },
      { data: "description" },
    ],
  });

  const tableBody = "#product-list tbody";
  const form = "#productInfo";
  const product_image_src = "../../ecommerce/images/products/";

  function setForm(data) {
    $(form).find("input[name='productId']").val(data.productId);
    $(form).find("input[name='name']").val(data.productName);
    $(form).find("select[name='categoryId']").val(data.categoryId);
    $(form).find("select[name='originId']").val(data.originId);
    $(form).find("select[name='supplierId']").val(data.supplierId);
    $(form)
      .find("input[name='price']")
      .val(parseFloat(data.price.replace(/,/g, "")));
    $(form)
      .find("input[name='discount']")
      .val(parseFloat(data.discount.replace(/,/g, "")));
    $(form).find("input[name='quantity']").val(data.quantity);
    $(form).find("input[name='image']").val(data.image);

    $(".image-upload-wrap").hide();
    $(".file-upload-image").attr("src", product_image_src + data.image);
    $(".btn-text").html("Change Image");
    $(".file-upload-content").show();
  }

  function clearForm() {
    $(form).find("input[name='productId']").val("");
    $(form).find("input[name='name']").val("");
    $(form).find("select[name='categoryId']").val("");
    $(form).find("select[name='originId']").val("");
    $(form).find("select[name='supplierId']").val("");
    $(form).find("input[name='price']").val("");
    $(form).find("input[name='discount']").val("");
    $(form).find("input[name='quantity']").val("");
    $(form).find("input[name='image']").val("");

    $(".image-upload-wrap").show();
    $(".file-upload-image").attr("src", "");
    $(".btn-text").html("Add Image");
    $(".file-upload-content").hide();
  }

  $(tableBody).on("dblclick", "tr", function () {
    $("#nav-update-tab").trigger("click");

    datatable.rows(".selected").deselect();
    datatable.row(this).select();

    var data = datatable.row(this).data();

    setForm(data);
  });

  $("#first").on("click", function () {
    datatable.rows(".selected").deselect();
    datatable.row(":first").select();
    datatable.row(":first").show().draw(false);
    var data = datatable.row(":first").data();
    $("#add-btn").attr("disabled", true);
    setForm(data);
  });

  $("#previous").on("click", function () {
    var index = datatable.row(".selected").index();

    if (index == null) index = 1;

    if (index <= 0) return;

    datatable.rows(".selected").deselect();
    datatable.row(--index).select();
    datatable.row(index).show().draw(false);
    $("#add-btn").attr("disabled", true);

    var data = datatable.row(index).data();

    setForm(data);
  });

  $("#next").on("click", function () {
    var index = datatable.row(".selected").index();

    if (index == null) index = -1;

    if (index === datatable.row(":last").index()) return;

    datatable.rows(".selected").deselect();
    datatable.row(++index).select();
    datatable.row(index).show().draw(false);
    $("#add-btn").attr("disabled", true);

    var data = datatable.row(index).data();

    setForm(data);
  });

  $("#last").on("click", function () {
    datatable.rows(".selected").deselect();
    datatable.row(":last").select();
    datatable.row(":last").show().draw(false);
    var data = datatable.row(":last").data();
    $("#add-btn").attr("disabled", true);
    setForm(data);
  });

  $("#clearForm").on("click", function () {
    datatable.rows(".selected").deselect();
    datatable.row(0).show().draw(false);
    $("#add-btn").attr("disabled", false);
    clearForm();
  });

  /* trigger select on first row at load page */
  const fr = datatable.row(":first");
  $(fr).ready(function () {
    fr.select();
    setForm(fr.data());
  });

  /* Form validate */
  $("#productInfo").validate({
    rules: {
      name: "required",
      categoryId: "required",
      originId: "required",
      supplierId: "required",
      price: "required",
      quantity: "required",
    },

    messages: {
      name: "Enter product name",
      categoryId: "Choose category type",
      originId: "Choose product's origin",
      supplierId: "Choose product's supplier",
      price: "Enter product's price",
      quantity: "Enter product's quantity",
    },

    errorPlacement: function (error, element) {
      var placement = $(element).data("error");
      if (placement) {
        $(placement).append(error);
      } else {
        error.insertAfter(element);
      }
    },

    showErrors: function (errorMap, errorList) {
      this.defaultShowErrors();
      $("input").removeClass("error");
      $("select").removeClass("error");
    },
  });
});

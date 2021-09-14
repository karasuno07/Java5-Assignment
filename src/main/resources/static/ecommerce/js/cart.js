$(document).ready(function () {
  /* Custom input number */
  $(".btn-plus, .btn-minus").on("click", function (e) {
    const isNegative = $(e.target).closest(".btn-minus").is(".btn-minus");
    const input = $(e.target).closest(".input-group").find("input");
    if (input.is("input")) {
      var value = input.val();
      if (value == 1 && isNegative) {
        $("#removeModal").modal("show");
      }
      input[0][isNegative ? "stepDown" : "stepUp"]();
    }
  });

  /* Zoom effect on hover image */
  $(".cart-product-image img").hover(
    function () {
      $(this).addClass("transition");
    },
    function () {
      $(this).removeClass("transition");
    }
  );

  /* page steps */
  $(document).on("click", ".btn-checkout", function () {
    /* replace active steps */
    $(".steps .cart").removeClass("active");
    $(".steps .checkout").addClass("active");
    $("#cart-section").hide(600);
    $("#checkout-section").show(600);
    $(".btn-shopping").html(
      '<i class="fas fa-chevron-left"></i><span class="ms-1">Back</span>'
    );
    $(".btn-shopping").addClass("btn-back");
    $(".btn-shopping").removeClass("btn-shopping");
    $(this).html(
      '<i class="fas fa-check"></i><span class="ms-1">Finish</span>'
    );
    $(this).addClass("btn-finish");
    $(this).removeClass("btn-checkout");
  });

  $(document).on("click", ".btn-back", function () {
    /* replace active steps */
    $(".steps .cart").addClass("active");
    $(".steps .checkout").removeClass("active");
    $("#checkout-section").hide(600);
    $("#cart-section").show(600);
    $(this).html(
      '<i class="fas fa-shopping-bag"></i><span class="ms-1">Shopping more</span>'
    );
    $(this).addClass("btn-shopping");
    $(this).removeClass("btn-back");
    $(".btn-finish").html(
      '<i class="fas fa-money-check"></i><span class="ms-1">Checkout</span>'
    );
    $(".btn-finish").addClass("btn-checkout");
    $(".btn-finish").removeClass("btn-finish");
  });

  $(document).on("click", ".btn-shopping", function () {
    window.location.href = "home";
  });
});

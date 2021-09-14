$(document).ready(function () {
  /* Image Upload */
  function readURL(input) {
    if (input.files && input.files[0]) {
      var reader = new FileReader();

      reader.onload = function (e) {
        $(".image-upload-wrap").hide();
        $(".file-upload-image").attr("src", e.target.result);
        $(".file-upload-content").show();
        $(".file-upload-content").addClass("d-flex");
        $(".btn-text").html("Change Image");
      };

      reader.readAsDataURL(input.files[0]);
    } else {
      removeUpload();
    }
  }

  function removeUpload() {
    $(".file-upload-input").replaceWith($(".file-upload-input").clone());
    $(".file-upload-content").hide();
    $(".file-upload-content").removeClass("d-flex");
    $(".image-upload-wrap").show();
    $(".btn-text").html("Add Image");
  }

  $(".image-upload-wrap").bind("dragover", function () {
    $(".image-upload-wrap").addClass("image-dropping");
  });

  $(".image-upload-wrap").bind("dragleave", function () {
    $(".image-upload-wrap").removeClass("image-dropping");
  });

  $(".file-upload").on("change", ".file-upload-input", function () {
    readURL(this);
  });

  /* Zoom effect on hover image */
  $("#product-image").hover(
    function () {
      $("#product-image").addClass("transition");
    },
    function () {
      $("#product-image").removeClass("transition");
    }
  );
});

var ChartColor = [
    "#5D62B4",
    "#54C3BE",
    "#EF726F",
    "#F9C446",
    "rgb(93.0, 98.0, 180.0)",
    "#21B7EC",
    "#04BCCC",
];
var primaryColor = getComputedStyle(document.body).getPropertyValue(
    "--primary"
);
var secondaryColor = getComputedStyle(document.body).getPropertyValue(
    "--secondary"
);
var successColor = getComputedStyle(document.body).getPropertyValue(
    "--success"
);
var warningColor = getComputedStyle(document.body).getPropertyValue(
    "--warning"
);
var dangerColor = getComputedStyle(document.body).getPropertyValue("--danger");
var infoColor = getComputedStyle(document.body).getPropertyValue("--info");
var darkColor = getComputedStyle(document.body).getPropertyValue("--dark");
var lightColor = getComputedStyle(document.body).getPropertyValue("--light");


(function ($) {
    "use strict";

    // off canvas
    $(function () {
        $('[data-toggle="offcanvas"]').on("click", function () {
            $(".sidebar-offcanvas").toggleClass("active");
        });
    });

    $(function () {
        var body = $("body");
        var contentWrapper = $(".content-wrapper");
        var scroller = $(".container-scroller");
        var footer = $(".footer");
        var sidebar = $("#sidebar");

        //Close other submenu in sidebar on opening any
        $("#sidebar > .nav > .nav-item > a[data-toggle='collapse']").click(
            function () {
                $("#sidebar > .nav > .nav-item")
                    .find(".collapse.show")
                    .collapse("hide");
                console.log($(this).attr("aria-expanded"));
            }
        );

        //checkbox and radios
        $(".form-check label,.form-radio label").append(
            '<i class="input-helper"></i>'
        );
    });

})(jQuery);

$(document).ready(function () {

    var errorMsg = "";

    $("#login_form")
        .css({"color": "red"})
        .find("input[type='submit']").bind("click", function (e) {

            e.stopPropagation();

            errorMsg = "";

            var emailStr = $(this).parent().find("[name=email]").val();
            var firstNameStr = $(this).parent().find("[name=first_name]").val();

            if (!val.isOverMin(firstNameStr.length)) {
                errorMsg = " : First Name is too short";
            }
            if (!val.isEmail(emailStr)) {
                console.log("is not valid");
                errorMsg += " : Email is Not Valid";
            }
            if (errorMsg == "") {
                submitForm();
            }
            $("#msg").html(errorMsg);

            return false;
        });

});

function submitForm() {
    console.log("submit form");
    $("#msg").html("posting form");

    $.ajax({
        url: "form_data",
        context: "{\"a\":1}",
        type:"post"
    }).done(function(data) {
        console.log("done");
        $("#msg").html("form posted : " + data );

        $("#login_form").find("input[name=email]").val("");
        $("#login_form").find("input[name=first_name]").val("");

    });

}

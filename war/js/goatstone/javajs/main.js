
$(document).ready(function () {

    $("#login_form")
        .css({"color": "red"})
        .find("input[type='submit']").bind("click", function (e) {
            e.stopPropagation();

            var emailStr = $(this).parent().find("[name=email]").val();
            var firstNameStr = $(this).parent().find("[name=first_name]").val();

            if (val.isUnderMax(firstNameStr)) {
                $("#msg").html("First Name is too short");

            }
            if (val.isEmail(emailStr)) {
                console.log("is valid");
                $("#msg").html("Email is Valid");

            }
            else {
                console.log("is not valid");
                $("#msg").html("Email is Not Valid");
            }

            return false;
        });

});


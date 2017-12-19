$(document).ready(function () {

	$("usercheck").click(function(){
	    alert("The paragraph was clicked.");
	    fire_ajax_submit();
	});
});

function fire_ajax_submit() {

    var search = {}
    search["username"] = $("#username").val();

    //$("#btn-search").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/getUser",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            var json = "<h4>Ajax Response</h4><pre>"
                + JSON.stringify(data, null, 4) + "</pre>";
            $('#message').html(json);

            console.log("SUCCESS : ", data);
            $("#btn-search").prop("disabled", false);

        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#message').html(json);

            console.log("ERROR : ", e);
            //$("#btn-search").prop("disabled", false);

        }
    });

}
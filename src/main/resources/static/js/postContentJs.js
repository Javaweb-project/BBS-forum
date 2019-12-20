function edit() {
    var text = document.getElementById("text").innerText;

    document.getElementById("text").innerHTML =
        "<textarea class='col-lg-12 col-md-12 col-sm-12 col-xs-12' style='height: 300px;resize: none;' id='textarea'> "  + text + "</textarea>";
    document.getElementById("edit").style.visibility = "hidden";
    document.getElementById("submit").style.visibility= "visible";
    document.getElementById()
}

function submit() {
    var text = document.getElementById("textarea").innerText;

}
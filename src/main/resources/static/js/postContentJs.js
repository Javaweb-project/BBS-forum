


// function editPost() {
//     var text = document.getElementById("text").innerText;
//     document.getElementById("myTextarea").readOnly=true;
//
//
//     document.getElementById("textarea")
// }
$("#edit").click(function(){
    document.getElementById("textarea").readOnly=false;
    document.getElementById("edit").style.visibility = "hidden";
    document.getElementById("submit").style.visibility= "visible";
});

function submitPost() {
    var text = $("#textarea").val();
    var post_id = $("#postId").val();
    console.log(post_id);
    console.log(text);
    $.ajax({
        type: 'post',
        url: 'updatePostContent',
        data: {post_id : post_id,post_Content : text},
        dataType: 'json',
        success: function(res) {
            console.log("成功");
            location.reload();
        },
        error:function (res) {
            console.log("失败");
            location.reload();
        }
    })
}
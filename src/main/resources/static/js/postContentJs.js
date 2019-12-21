
$("#edit").click(function(){
    document.getElementById("textarea").readOnly=false;
    document.getElementById("edit").style.visibility = "hidden";
    document.getElementById("submit").style.visibility= "visible";
});

function refresh(url) {
    $("#showComments").load(url);    
}

function submitPost() {
    var text = $("#textarea").val();
    var post_id = $("#showComments").attr("value");
    console.log(post_id);
    console.log(text);
    $.ajax({
        type: 'post',
        url: '/updatePostContent',
        data: {post_id : post_id,post_content : text},
        dataType: 'json',
        success: function(data) {
            console.log("成功");
            location.reload();
        },
        error:function (data) {
            console.log("失败");
            location.reload();
        }
    })
}

function loadCommens() {
    var post_id = $("#showComments").attr("value");
    var url = "/comment/findByPostId?post_id="+post_id;
    refresh(url);
}

var user_id = $("#userName").attr("value");
$.ajax({
    type: 'post',
    url: '/user/findNameById',
    data: {user_id: user_id},
    dataType: 'json',
    async: false,
    success: function(data) {
        $("#userName").text(data.user_name);
    }
})

loadCommens();
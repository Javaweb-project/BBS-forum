$(".nav-tabs li").click(function() {
    $(".nav-tabs .active").removeClass("active");
    $(this).addClass("active");
})

/*局部刷新帖子列表*/
function refresh(url) {
    $("#list").load(url);
}

window.onload = function() {
    refresh("user/myInfo?user_id=1");
}

$("#myInfo").click(function () {
    var url = "user/myInfo?user_id=1";
    refresh(url);
})

$('#myPost').click(function () {
    var url = "myPost?user_id=1";
    refresh(url);
})
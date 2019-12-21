$(".nav-tabs li").click(function() {
    $(".nav-tabs .active").removeClass("active");
    $(this).addClass("active");
})

/*局部刷新帖子列表*/
function refresh(url) {
    $("#list").load(url);
}

window.onload = function() {
    refresh("/userInfo");
}

$("#myInfo").click(function () {
    var url = "/userInfo";
    refresh(url);
})

$('#myPost').click(function () {
    var url = "/myPosts";
    refresh(url);
})
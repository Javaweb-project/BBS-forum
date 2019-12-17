
/* 设置顶部导航栏的切换效果 */
$(".nav-tabs li").click(function() {
    $(".active").removeClass("active");
    $(this).addClass("active");
})

/*局部刷新帖子列表*/
function refresh(url) {
    $("#list").load(url);
}

window.onload = function() {
    refresh("postsByTime");
}

$("#new").click(function() {
    var url = "postsByTime";
    refresh(url);
})

$("#hot").click(function() {
    var url="postsByView";
    refresh(url);
})

$("#highlight").click(function() {
    var url = "highLightPosts";
    refresh(url);
})

$("#demand").click(function () {
    var url = "demandPosts";
    refresh(url);
})

$("#query").click(function() {
    var title = $("#title").value;
    console.log(title);
    // var url = "query?title="+title;
    // refresh(url);
})


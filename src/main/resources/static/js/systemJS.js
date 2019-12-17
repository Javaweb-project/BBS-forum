
/* 设置顶部导航栏的切换效果 */
function changeTab() {
    var url = window.location.href;
    var str = new Array();
    str = url.split('/');
    var page = str[str.length-1];
    $(".active").removeClass("active");
    switch(page) {
        case "system":
        case "postsByTime": $("#new").addClass("active"); break;
        case "postsByView": $("#hot").addClass("active"); break;
        case "highLightPosts": $("#highlight").addClass("active"); break;    
        case "demandPosts": $("#demand").addClass("active");
    }
}

window.onload(changeTab());

$("#query").click(function() {
    var title = $("#title").value;
    if(title != "") {
        $.ajax({
            type: 'post',
            url: '/post/query',
            data: {title: title.trim()},
            cache: false,
            success: function(data) {
                alert("成功查询");
            }
        })    
    }
})


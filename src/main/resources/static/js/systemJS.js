
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

$("#query").click(function() {
    var title = $("#title").value;
    if(title != "") {
        $.ajax({
            type: 'post',
            url: '/query',
            data: {title: title.trim()},
            cache: false,
            success: function(data) {
                alert("成功查询");
            }
        })    
    }
})

// function high(val) {
//     var par = this.parents('article');
//     var post_id = par.id;
//     $.ajax({
//         type: 'post/updateHigh',
//         url: '',
//         data: {post_id: post_id},
//     })
// }

/* 管理员按钮点击功能 */
function btnThing() {
    var btn_high = $(".high");
    var btn_top = $(".top");
    /* 加精按钮功能 */
    for(var i in btn_high) {
        btn_high.eq(i).click(function() {
            var par = $(this).eq(i).parents('article');
            var post_id = par.id;
            $.ajax({
                type: 'post',
                url: 'updateHigh',
                data: {post_id: post_id},
            })
        })
    }

    /* 置顶按钮功能 */
    for(var i in btn_top) {
        btn_top.eq(i).click(function() {
            var par = $(this).parents('article');
            var post_id = par.id;
            $.ajax({
                type: 'post',
                url: 'updateTop',
                data: {post_id: post_id},
            })
        })
    }
}

window.onload = function() {
    changeTab();
    btnThing();
}
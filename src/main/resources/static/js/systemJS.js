
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
    console.log(title);
    console.log($("#title"));
    if(title != "") {
        $.ajax({
            type: 'post',
            url: '/query',
            data: {title: title},
            success: function(data) {
                alert("成功查询");
            }
        })    
    }
})

/* 管理员按钮点击功能 */
function btnThing() {
    var btn_high = $(".high");
    var btn_top = $(".top");
    var btn_del = $(".del");
    /* 加精按钮功能 */
    for(var i in btn_high) {
        btn_high.eq(i).click(function() {
            var par = $(this).parents("article");
            var post_id = par.eq(0).attr("id");
            $.ajax({
                type: 'post',
                url: 'updateHigh',
                data: {post_id: post_id},
                success: function(data) {
                    alert(data.res);
                    window.location.href="/postsByTime";
                }
            })
        })
    }

    /* 置顶按钮功能 */
    for(var i in btn_top) {
        btn_top.eq(i).click(function() {
            var par = $(this).parents('article');
            var post_id = par.eq(0).attr("id");
            alert(post_id);
            $.ajax({
                type: 'post',
                url: 'updateTop',
                data: {post_id: post_id},
                dataType: 'json',
                success: function(data) {
                    alert(data.res);
                    window.location.href="/postsByTime";
                }
            })
        })
    }

    /* 删除按钮功能 */
    for(var i in btn_del) {
        btn_del.eq(i).click(function() {
            var par = $(this).parents('article');
            var post_id = par.eq(0).attr("id");
            alert(post_id);
            $.ajax({
                type: 'post',
                url: 'delPost',
                data: {post_id: post_id},
                dataType: 'json',
                success: function(data) {
                    alert(data.res);
                    window.location.href="/postsByTime";
                }
            })    
        })
    }
}

window.onload = function() {
    changeTab();
    btnThing();
}
/* 根据当前的标签页，决定加载哪一个页面 */
function freshByTab(text) {
    switch(text) {
        case '最新': refresh("postsByTime"); break;
        case '最热': refresh("postsByView"); break;
        case '精华帖': refresh("highLightPosts"); break;
        case '需求帖': refresh("demandPosts");
    }
}

/* 管理员按钮点击功能 */
function btnThing() {
    var btn_high = $(".high");
    var btn_top = $(".top");
    var btn_del = $(".del");
    var active = $(".active");
    var text = active.text();
    
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
                    freshByTab(text);
                }
            })
        })
    }

    /* 置顶按钮功能 */
    for(var i in btn_top) {
        btn_top.eq(i).click(function() {
            var par = $(this).parents('article');
            var post_id = par.eq(0).attr("id");
            $.ajax({
                type: 'post',
                url: 'updateTop',
                data: {post_id: post_id},
                dataType: 'json',
                success: function(data) {
                    alert(data.res);
                    freshByTab(text);
                }
            })
        })
    }

    /* 删除按钮功能 */
    for(var i in btn_del) {
        btn_del.eq(i).click(function() {
            var par = $(this).parents('article');
            var post_id = par.eq(0).attr("id");
            $.ajax({
                type: 'post',
                url: 'delPost',
                data: {post_id: post_id},
                dataType: 'json',
                success: function(data) {
                    alert(data.res);
                    freshByTab(text);
                }
            })
        })
    }
}

/* 设置同步是为了success可以改变html标签 */
function setUserName() {
    var names = $(".name");
    for(var i in names) {
        var name = names.eq(i);
        var text = name.text();
        if(text == "发帖人") {
            var user_id = name.attr("id");
            console.log("成功进啦！");
            $.ajax({
                type: 'post',
                url: '/user/findNameById',
                data: {user_id: user_id},
                dataType: 'json',
                async: false,
                success: function(data) {
                    name.text(data.user_name);
                }
            })
        }
    }
}

btnThing();
setUserName();

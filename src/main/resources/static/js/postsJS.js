/* 根据当前的标签页，决定加载哪一个页面 */
function freshByTab(text) {
    switch(text) {
        case '最新': refresh("postsByTime"); break;
        case '最热': refresh("postsByView"); break;
        case '精华帖': refresh("highLightPosts"); break;
        case '需求帖': refresh("demandPosts");
    }
}

/* 按分页进行刷新 */
function freshByPage(text,page) {
    switch(text) {
        case '最新': refresh("postsByTime?page="+page); break;
        case '最热': refresh("postsByView?page="+page); break;
        case '精华帖': refresh("highLightPosts?page="+page); break;
        case '需求帖': refresh("demandPosts?page="+page);
    }        
}

/* 管理员按钮点击功能 */
function btnThing() {
    var btn_high = $(".high");
    var btn_top = $(".top");
    var btn_del = $(".del");
    var active = $(".nav-tabs .active");
    var text = active.text().trim();
    
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

/* 动态显示发帖人姓名，设置同步刷新是为了让success可以改变html标签 */
function setUserName() {/* 选中帖子类型，显示输入积分input */

    var names = $(".name");
    for(var i in names) {
        var name = names.eq(i);
        var text = name.text();
        if(text == "发帖人") {
            var user_id = name.attr("id");
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

/* 设置翻页功能 */
function pageHelper() {
    var active = $(".nav-tabs .active");
    var text = active.text().trim();
    var pageNow = $('.text-center .active').children().eq(0).text();
    $('#pre').click(function() {
        freshByPage(text,pageNow-1);
    });   
    
    $('#next').click(function() {
        freshByPage(text,parseInt(pageNow)+1); /*注意加号有歧义,一定要转化为数字再相加*/
    });
    
    var pages = $('.page');
    for(var i in pages) {
        pages.eq(i).click(function() {
            var pageNum = $(this).children().eq(0).text();
            console.log("pageNum="+pageNum);
            freshByPage(text,pageNum);
        })
    }
}

/* 改变翻页插件的选中状态 */
$(".text-center ul li").click(function() {
    var active = $(".text-center .active");
    active.removeClass("active");
    if($(this).attr("id") == "pre") 
        active.prev().addClass("active");
    else if($(this).attr("id") == "next")
        active.next().addClass("active");
    else
        $(this).addClass("active");
})

btnThing();
setUserName();
pageHelper();
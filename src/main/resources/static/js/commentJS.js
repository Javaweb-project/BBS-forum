function setCommentUserName() {
    var users = $(".user");
    for(var i in users) {
        var user = users.eq(i);
        var text = user.text();
        if(text == "评论用户名") {
            var user_id = user.attr("value");
            $.ajax({
                type: 'post',
                url: '/user/findNameById',
                data: {user_id: user_id},
                dataType: 'json',
                async: false,
                success: function(data) {
                    user.text(data.user_name);
                }
            })
        }
    }
        
}

/* 获得当前时间，转成yyyy-MM-dd HH:mm:SS的格式 */
function getFormatDate(){
    var nowDate = new Date();
    var year = nowDate.getFullYear();
    var month = nowDate.getMonth() + 1 < 10 ? "0" + (nowDate.getMonth() + 1) : nowDate.getMonth() + 1;
    var date = nowDate.getDate() < 10 ? "0" + nowDate.getDate() : nowDate.getDate();
    var hour = nowDate.getHours()< 10 ? "0" + nowDate.getHours() : nowDate.getHours();
    var minute = nowDate.getMinutes()< 10 ? "0" + nowDate.getMinutes() : nowDate.getMinutes();
    var second = nowDate.getSeconds()< 10 ? "0" + nowDate.getSeconds() : nowDate.getSeconds();
    return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second;
}

/*回复功能*/
$("#reply button").click(function() {
    var content = $("#content").val();
    var curtime = getFormatDate();
    var post_id = $("#reply").attr("value");
    if(content == "")
        alert("回复不能为空");
    else {
        $.ajax({
            type: 'post',
            url: '/comment/addComment',
            data: {
                post_id: post_id,
                content: content,
                time: curtime
            },
            dataType: 'json',
            success: function(data) {
                var res = data.res;
                alert(res);
                if(res == "回复成功") 
                    location.reload();
            }
        })
    }
    $("#content").text("");
})

/*需求帖主人采纳留言*/
function setAccept() {
    var accept = $(".accept");
    for(var i in accept) {
        accept.eq(i).click(function() {
            var par = $(this).parents(".panel-body");
            var heading = par.eq(0).prev();
            var time = heading.children().children(".font-5").eq(0).children(".time").eq(0).attr("value");
            var post_id = $("#reply").attr("value");
            var user_id = heading.children().children(".font-5").eq(0).children("a").eq(0).children(".user").attr("value");
            // console.log(user_id);
            // console.log(time);
            $.ajax({
                type: 'post',
                url: '/comment/accept',
                data: {
                    post_id: post_id,
                    user_id: user_id,
                    time: time
                },
                dataType: 'json',
                success: function(data) {
                    var res = data.res;
                    alert(res);
                    if(res == "采纳成功") {
                        addPoint(user_id);
                        location.reload();    
                    }
                        
                }
            })     
        })
    }
}

/*给被采纳留言的用户增加积分*/
function addPoint(user_id) {
    var point = $("#allComments").attr("value");
    $.ajax({
        type: 'post',
        url: '/user/addPoint',
        data: {
            user_id: user_id,
            point: point
        },
        async: false,
        dataType: 'json',
        success: function(data) {
            alert(data.res);    
        }
    })
}

setCommentUserName();
setAccept();
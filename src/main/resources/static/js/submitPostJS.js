/* 表单内容传输给后端 */
function submitPost() {
    var title=$("#inputTitle").val();
    var integral=($("#inputIntegral").val())?($("#inputIntegral").val()):0;
    var content=$("#inputContent").val();
    $.ajax({
        type: 'post',
        url: '/post/submitPost',
        data: {
                title: title,
                integral: integral,
                content: content
        },
        dataType: 'json',
        async: false,
        success: function(data) {
            alert(data.res);
        }
    })
}
/*
	Button switching
*/
(function() {
        var ttJsActiveBtn = $('#tt-pageContent .tt-js-active-btn');
        if (ttJsActiveBtn.length) {
            ttJsActiveBtn.on('click', '.tt-icon', function(e) {
                $(this).closest(ttJsActiveBtn).find('.tt-icon').removeClass('active');
                $(this).addClass('active');
                return false;
            });
        }
        ;
    }
)();
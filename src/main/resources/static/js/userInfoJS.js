function freshByTab(text) {
    switch(text) {
        case '个人资料': refresh("myInfo?user_id=1");
    }
}

$('#update').click(function () {

    $('#phone').attr("readonly",false);
    $('#workplace').attr("readonly",false);
    $('#job').attr("readonly",false);


    $('#update').attr("hidden",true);
    $('#submit').attr("hidden",false);
})

$('#submit').click(function () {
    var phone=$('#phone').val();
    var workplace=$('#workplace').val();
    var job=$('#job').val();
    $.ajax({
        type: 'post',
        url: 'user/updateUser',
        data: {
            user_phone: phone,
            user_workplace: workplace,
            user_job: job},
        success: function(data) {
            alert("修改成功");
            $('#phone').attr("readonly",true);
            $('#workplace').attr("readonly",true);
            $('#job').attr("readonly",true);

            $('#update').attr("hidden",false);
            $('#submit').attr("hidden",true);
        }
    })

})

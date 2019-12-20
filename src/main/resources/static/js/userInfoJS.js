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
    var user_id=1;
    var user_phone=$('#phone').value;
    var user_workplace=$('#workplace').value;
    var user_job=$('#job').value;
    $.ajax({
        type: 'post',
        url: 'user/updateUser?user_id=1',
        data: {
            user_phone:user_phone,
            user_workplace:user_workplace,
            user_job:user_job},
        success: function(data) {
            alert("修改成功");
            // freshByTab(text);
            $('#phone').attr("readonly",true);
            $('#workplace').attr("readonly",true);
            $('#job').attr("readonly",true);

            $('#update').attr("hidden",false);
            $('#submit').attr("hidden",true);
        }
    })

})

function regitjs(){
    var params = {};
    params.username = $("#user_name").val();
    params.password = $("#user_password").val();
    params.useradmin=$("#user_admin").val();
    params.userPhone=$("#userPhone").val();
    params.userWorkPlace=$("#userWorkPlace").val();
    params.userJob=$("#userJob").val();
    params.userPoint=$("#userPoint").val();
    $.ajax({
        type:"GET",
        url:"/user/register",
        data:params,
        dataType:"json",
        success:function(data){
            alert(data);
        },
        error:function(data){
            alert(data)
        }
    })
}
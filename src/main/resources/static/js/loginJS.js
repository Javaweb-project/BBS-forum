function loginJS(){
    var params = {};
    params.username = $("#user_name").val();
    params.password = $("#user_password").val();
    params.useradmin=$("#user_admin").val();
    $.ajax({
        type:"GET",
        url:"/user/login",
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
var login = {
    actionURL: {
        login: function (username,password) {
            return '/user/login/'+username+'/'+password;
        },
        success: function () {
            return '/seckill/list';
        }
    }
};

//登录验证
function loginCheck(username,password) {

    if (username==""){
        alert("用户名不得为空");
        return;
    }else if (username.length<5){
        alert("密码不能少于五位英文加数字");
        return;
    }else if (password==null){
        alert("密码不得为空");
        return;
    }else if (password.length<5){
        alert("密码不能少于五位英文加数字");
        return;
    }
}

//用户登录
function userLogin(){
    var username = $("#username").val();
    var password = $("#password").val();

    loginCheck(username, password);

    alert(username+'-->'+password);
    var loginUrl = login.actionURL.login(username,password);

    $.post(loginUrl, {}, function (result) {
        if (result && result['success']) {
            window.location.href = login.actionURL.success();

        } else {
            alert(result['error']);
            console.log('result:' + result);
        }
    });
}
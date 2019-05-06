// 判断时候在Iframe框架内,在则刷新父页面
if (self != top) {
    parent.location.reload(true);
    if (!!(window.attachEvent && !window.opera)) {
        document.execCommand("stop");
    } else {
        window.stop();
    }
}

$(function () {
    // 验证码
    $("#captcha").click(function() {
        var $this = $(this);
        var url = $this.data("src") + new Date().getTime();
        $this.attr("src", url);
    });
    // 登录
    $('#loginform').form({
        url: basePath + '/login',
        onSubmit : function() {
            progressLoad();
            var isValid = $(this).form('validate');
            if(!isValid){
                progressClose();
            }
            return isValid;
        },
        success:function(result){
            progressClose();
            result = $.parseJSON(result);
            if (result.success) {
                window.location.href = basePath + '/index';
            }else{
                // 刷新验证码
                $("#captcha")[0].click();
                showMsg(result.msg);
            }
        }
    });
});
function submitForm(){
    $('#loginform').submit();
}
function clearForm(){
    $('#loginform').form('clear');
}
//回车登录
function enterlogin(){
    if (event.keyCode == 13){
        event.returnValue=false;
        event.cancel = true;
        $('#loginform').submit();
    }
}

function onBridgeReady(){

    var appId = $("#appId").val()+"";
    var timeStamp = $("#timeStamp").val()+"";
    var nonceStr = $("#nonceStr").val()+"";
    var pg = $("#package").val()+"";
    var signType = "MD5";
    var paySign = $("#sign").val()+"";
    alert(JSON.stringify({
        "appId":appId,
        "timeStamp":timeStamp,
        "nonceStr":nonceStr,
        "package":"prepay_id="+pg,
        "signType":"MD5",
        "paySign":paySign
    }));
    WeixinJSBridge.invoke(
        'getBrandWCPayRequest', {
            "appId":appId,
            "timeStamp":timeStamp,
            "nonceStr":nonceStr,
            "package":"prepay_id="+pg,
            "signType":"MD5",
            "paySign":paySign
        },

        function(res){
            var str = JSON.stringify(res);
            alert(str);
            if(res.err_msg == "get_brand_wcpay_request:ok" ) {

                alert("支付成功");
            }     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
        }
    );
}


function pay(){

    if (typeof WeixinJSBridge == "undefined"){
        if( document.addEventListener ){
            document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
        }else if (document.attachEvent){
            document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
            document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
        }
    }else{
        onBridgeReady();
    }

}
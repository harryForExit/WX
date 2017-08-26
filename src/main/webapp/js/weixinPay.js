/**
 * Created by Administrator on 2017/8/19.
 */
function onBridgeReady(){
    var appid = $("#appid").val();
    var paySign = $("#sign").val();
    var prepay_id = $("#prepay_id").val();
    alert(appid);
    alert(paySign);
    WeixinJSBridge.invoke(
        'getBrandWCPayRequest', {
            "appId":appid,     //公众号名称，由商户传入
            "timeStamp":"1395712654",         //时间戳，自1970年以来的秒数
            "nonceStr":"e61463f8efa94090b1f366cccfbbb444", //随机串
            "package":"prepay_id="+prepay_id,
            "signType":"MD5",         //微信签名方式：
            "paySign":paySign //微信签名
        },
        function(res){
            if(res.err_msg == "get_brand_wcpay_request:ok" ) {
                alert("支付成功")
            }     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
        }
    );
}

function pay() {
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

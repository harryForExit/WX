package servletdepartment;

import WeiXinPay.sdk.*;
import config.MealIdConfig;
import util.IpUtil;
import util.OpenIdUtils;
import util.PayUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2017/8/19.
 */
public class weixinPay extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        WXPay wxpay;
        WXPayConfigImpl config;
        String out_trade_no;
        String total_fee;
        try {

            if ((String) request.getSession().getAttribute("openid") == null){
                return;
            }

           String number = request.getParameter("number");

            String mealid = request.getParameter("mealid");


            request.getSession().setAttribute("mealid",mealid);
            request.getSession().setAttribute("number",number);

           int money = MealIdConfig.getPrice(mealid);
            System.out.println("应付金额"+money);

            config = WXPayConfigImpl.getInstance();
            wxpay = new WXPay(config);
            total_fee = "1";


            final String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            out_trade_no = uuid;
            HashMap<String, String> data = new HashMap<String, String>();
            data.put("body", "腾讯充值中心-QQ会员充值");
            data.put("out_trade_no", out_trade_no);
            data.put("device_info", "");
            data.put("fee_type", "CNY");
            data.put("total_fee", total_fee);
            data.put("spbill_create_ip", IpUtil.getRemortIP(request));
            data.put("notify_url", "http://wuyishiliu.com/notify");
            data.put("trade_type", "JSAPI");
            data.put("product_id", uuid);
            System.out.println("openid ============ "+(String) request.getSession().getAttribute("openid"));
            data.put("openid", (String) request.getSession().getAttribute("openid"));
            request.getSession().setAttribute("orderId",out_trade_no);
            Map<String, String> r = wxpay.unifiedOrder(data);
            System.out.println(r.toString());
            String timeStamp = Long.toString(System.currentTimeMillis() / 1000);
            String nonceStr = PayUtil.create_nonce_str();
            Map<String,String> map = new HashMap<String,String>();
            map.put("appId",r.get("appid"));
            map.put("timeStamp",timeStamp);
            map.put("nonceStr",nonceStr);
            map.put("package","prepay_id="+r.get("prepay_id"));
            map.put("signType", "MD5");

            request.getSession().setAttribute("prepay_id",r.get("prepay_id"));

            String sign = WXPayUtil.generateSignature(map,config.getKey(), WXPayConstants.SignType.MD5);

            System.out.println("sign==========="+sign);

            request.setAttribute("appId",r.get("appid"));
            request.setAttribute("sign",sign);
            request.setAttribute("pg",r.get("prepay_id"));
            request.setAttribute("nonceStr", nonceStr);
            request.setAttribute("timeStamp",timeStamp);

            request.getRequestDispatcher("/paying.jsp").forward(request, response);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

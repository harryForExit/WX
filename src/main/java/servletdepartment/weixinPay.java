package servletdepartment;

import WeiXinPay.sdk.WXPay;
import WeiXinPay.sdk.WXPayConfig;
import WeiXinPay.sdk.WXPayConfigImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
            config = WXPayConfigImpl.getInstance();
            wxpay = new WXPay(config);
            total_fee = "1";
            out_trade_no = "201613091059590000003433-asd002";

            final String uuid = UUID.randomUUID().toString().replaceAll("-", "");

            HashMap<String, String> data = new HashMap<String, String>();
            data.put("body", "腾讯充值中心-QQ会员充值");
            data.put("out_trade_no", out_trade_no);
            data.put("device_info", "");
            data.put("fee_type", "CNY");
            data.put("total_fee", total_fee);
            data.put("spbill_create_ip", "123.12.12.123");
            data.put("notify_url", "http://test.letiantian.me/wxpay/notify");
            data.put("trade_type", "NATIVE");
            data.put("product_id", uuid);

            Map<String, String> r = wxpay.unifiedOrder(data);
            System.out.println(r);

        }catch (Exception e){
            e.printStackTrace();
        }
        request.getRequestDispatcher("/paying.jsp").forward(request, response);
    }
}

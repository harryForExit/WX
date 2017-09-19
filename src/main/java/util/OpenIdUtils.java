package util;

import com.alibaba.fastjson.JSON;
import entity.menu.MenuManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class OpenIdUtils {
    private static Logger log = LoggerFactory.getLogger(OpenIdUtils.class);

    // 第三方用户唯一凭证
    private static String appId = "wxba50d95321107e8f";
    // 第三方用户唯一凭证密钥
    private static String appSecret = "ea9ae1c3cae3086f6e6eb2e427fb519b";

    private static String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    public static void requireOpenId(HttpServletRequest request){
        if (request.getSession().getAttribute("openid") !=null) return;

        HttpClientUtil httpClientUtil = new HttpClientUtil();

        String str = httpClientUtil.doGet(getUrl(request.getParameter("code")),null);

        log.info("获取openid返回"+str);

        Map map = JSON.parseObject(str, Map.class);

        log.info("openid="+map.get("openid"));

        request.getSession().setAttribute("openid",map.get("openid"));

        request.getSession().setAttribute("access_token",map.get("access_token"));
    }


    private static String getUrl(String code){
        String herfUrl = url.replace("APPID",appId).replace("SECRET",appSecret).replace("CODE",code);

        log.info("url为"+herfUrl);
        return herfUrl;
    }

}

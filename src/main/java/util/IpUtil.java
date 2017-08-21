package util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/8/21.
 */
public class IpUtil {

    public static String getRemortIP(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }
}

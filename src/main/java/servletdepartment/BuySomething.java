package servletdepartment;

import com.toft.utils.json.JSONException;
import com.toft.utils.json.JSONObject;
import util.HttpClientUtil;
import util.HttpClientUtils;
import util.OpenIdUtils;
import util.TokenProcessor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BuySomething extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mealid = (String) request.getSession().getAttribute("mealid");
        String number = (String) request.getSession().getAttribute("number");
        request.getSession().removeAttribute("mealid");
        request.getSession().removeAttribute("number");
        if (mealid == null || number ==null){
            request.setAttribute("message","购买失败");
            request.getRequestDispatcher("/buyFail_result.jsp").forward(request, response);
        }
        String token = TokenProcessor.getInstance().getToken(request);
        HashMap map = new HashMap();
        map.put("mealid",mealid);
        map.put("number",number);
        map.put("appid","ceab5f92-703c-11e7-999a-1866da5cf0bc");
        map.put("token",token);
        try {
            String str =new HttpClientUtil().doPost("http://202.103.25.123:8080/meal/buy",map,"UTF-8");
            JSONObject jsonObject = new JSONObject(str);
            /*JSONObject jsonObject = HttpClientUtils.getConnect(map,"/meal/buy");*/
            System.out.println("返回的信息为："+str);
            if (jsonObject.get("status").toString().equals("1")){
                request.setAttribute("status",1);
            }else {
                request.setAttribute("status",-1);
            }
        } catch (JSONException e) {
            request.setAttribute("status",-1);
            e.printStackTrace();
        }
        request.getRequestDispatcher("/buy_result.jsp").forward(request, response);
    }
}

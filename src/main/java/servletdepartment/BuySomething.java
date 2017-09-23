package servletdepartment;

import com.toft.utils.json.JSONException;
import com.toft.utils.json.JSONObject;
import config.MealIdConfig;
import util.*;

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
        String token = util.Constants.token;
        HashMap map = new HashMap();
        map.put("mealid",mealid);
        map.put("number",number);
        map.put("appid", Constants.appid);
        map.put("token",token);
        try {
            String str =new HttpClientUtil().doPost("http://202.103.25.123:8080/meal/buy",map,"UTF-8");
            JSONObject jsonObject = new JSONObject(str);
            /*JSONObject jsonObject = HttpClientUtils.getConnect(map,"/meal/buy");*/
            System.out.println("返回的信息为："+str);
            if (jsonObject.get("status").toString().equals("1")){
                request.setAttribute("status",1);
                request.setAttribute("price", MealIdConfig.getPrice(mealid));
                request.setAttribute("desc", MealIdConfig.getDetail(mealid));
                request.setAttribute("number", number);
                request.setAttribute("date", DateUtil.now());
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

package servletdepartment;

import config.MealIdConfig;
import util.OpenIdUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/8/19.
 */
public class buy extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       /* OpenIdUtils.requireOpenId(request);*/
        System.out.println(MealIdConfig.getMonthMeals().size());
        request.setAttribute("monthMeals", MealIdConfig.getMonthMeals());
        request.setAttribute("addMeals", MealIdConfig.getAddMeals());

        request.getRequestDispatcher("/flow_result.jsp").forward(request, response);
    }
}

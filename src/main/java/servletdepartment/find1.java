package servletdepartment;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.HttpClientUtils;

import com.toft.utils.json.JSONObject;

//import com.lanxin.test.bean.bh;
//import com.lanxin.test.bean.dabh;
//import com.lanxin.test.service.clsbdhservice;
//import com.lanxin.test.service.clsbdhserviceimpl;
//import com.lanxin.test.service.dabhservice;
//import com.lanxin.test.service.dabhserviceimpl;

public class find1 extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
			doPost(request,response);
}

public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");  
    response.setCharacterEncoding("UTF-8"); 
    
	String number=request.getParameter("number");
	
	//dabhservice dabhse=new dabhserviceimpl();
	try {
	
		
		JSONObject json2=new JSONObject();
		HashMap writemap = new HashMap();
		writemap.put("appid",util.Constants.appid);
		writemap.put("token","$2y$12$FXYsWoY.1OoUtui49Ec1RueV7wQpTpkTncCZQqBHm4Ctl/BSMZZca");
		writemap.put("number",number);
		json2=HttpClientUtils.getConnect1(writemap,"/meal/use");
		System.out.println(json2.toString());
		request.getRequestDispatcher("/flow_result1.jsp").forward(request, response);
		//System.out.println(list.get(0));
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}

}

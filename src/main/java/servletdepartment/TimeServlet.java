package servletdepartment;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.HttpClientUtils;

import com.sun.org.apache.bcel.internal.Constants;
import com.toft.utils.json.JSONObject;

public class TimeServlet
extends HttpServlet
implements ServletContextListener {
public TimeServlet() {
}
private java.util.Timer timer = null;
public void contextInitialized(ServletContextEvent event) {
timer = new java.util.Timer(true);
event.getServletContext().log("定时器已启动");
timer.schedule(new MyTask(event.getServletContext()), 0,  60*60*1000);
event.getServletContext().log("已经添加任务调度表");
}
public void contextDestroyed(ServletContextEvent event) {
timer.cancel();
event.getServletContext().log("定时器销毁");
}

public class MyTask extends TimerTask {
	  private static final int C_SCHEDULE_HOUR = 0;
	  
	  private static final boolean isRunning = false;
	  private ServletContext context = null;
	  public MyTask() {
	  }
	  public MyTask(ServletContext context) {
	    this.context = context;
	  }
	  public void run() {
		  	/*String appid="ceab5f92-703c-11e7-999a-1866da5cf0bc";
			String sk="34661299b76cde8569e71816a18cc35c5c90f068";*/
			//dabhservice dabhse=new dabhserviceimpl();
			final String token_url = "/access/token/appid/APPID/sk/SK";  
			String requestUrl = token_url.replace("APPID", util.Constants.appid).replace("SK", util.Constants.sk);  
	        System.out.println(requestUrl);
		    
			try {
			
					
				JSONObject json1=new JSONObject();
				HashMap skmap = new HashMap();
				/*skmap.put("appid",appid);
				skmap.put("sk",sk);*/
				json1=HttpClientUtils.getConnect1(skmap,requestUrl);
				
				util.Constants.token=json1.getString("token");
				//System.out.println(token);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	}

		
}
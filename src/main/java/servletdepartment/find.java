package servletdepartment;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.lanxin.test.bean.bh;
//import com.lanxin.test.service.clsbdhservice;
//import com.lanxin.test.service.clsbdhserviceimpl;
//import com.lanxin.test.webservice.bean.WsQueryClxxOutBean;

public class find extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
			doPost(request,response);
}

public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	
	 request.setCharacterEncoding("UTF-8");  
     response.setCharacterEncoding("UTF-8"); 
     
	String xm=request.getParameter("xm");
	String cardno=request.getParameter("cardno");
	String sfzmhm=request.getParameter("sfzmhm");

	//System.out.println(hm);
	//clsbdhservice clsbdhse=new clsbdhserviceimpl();
	try {
		
		request.setAttribute("xm", xm);
		request.setAttribute("cardno", cardno);
		request.setAttribute("sfzmhm", sfzmhm);
		request.getRequestDispatcher("/from_smrz_2.jsp").forward(request, response);
		
	} catch (Exception e) {
		
		request.getRequestDispatcher("/from_smrz_2.jsp").forward(request, response);
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}

}

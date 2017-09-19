package servletdepartment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.HttpClientUtils;

import com.toft.utils.json.JSONArray;
import com.toft.utils.json.JSONException;
import com.toft.utils.json.JSONObject;

//import com.lanxin.test.bean.bh;
//import com.lanxin.test.bean.dabh;
//import com.lanxin.test.service.clsbdhservice;
//import com.lanxin.test.service.clsbdhserviceimpl;
//import com.lanxin.test.service.dabhservice;
//import com.lanxin.test.service.dabhserviceimpl;

public class sc extends HttpServlet{
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
	String zm=request.getParameter("zm");
	String fm=request.getParameter("fm");
	String sc=request.getParameter("scc");
	
	//dabhservice dabhse=new dabhserviceimpl();
	String status="";
	
	try {
	
		
		JSONObject json2=new JSONObject();
		HashMap writemap = new HashMap();
		writemap.put("appid",util.Constants.appid);
		writemap.put("token",util.Constants.token);
		writemap.put("number",cardno);
		writemap.put("name",xm);
		writemap.put("idcard",sfzmhm);
		writemap.put("resourcezm",zm);
		writemap.put("resourcefm",fm);
		writemap.put("resourcesc",sc);
		json2=HttpClientUtils.getConnect(writemap,"/auth/submit");
		//System.out.println(json2.toString());
		status=json2.getString("status");
		if(status.equals("1")){
			
		
		
		}
		
	
		request.setAttribute("status", status);
		
		//request.setAttribute("dw", dw);
		request.getRequestDispatcher("/from_smrz_3.jsp").forward(request, response);
		//System.out.println(list.get(0));
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		request.setAttribute("status", status);
		request.getRequestDispatcher("/from_smrz_3.jsp").forward(request, response);
		e.printStackTrace();
	}
	
	
}

public static List jsonTransitionList(JSONObject json){
	List<Object> list = new ArrayList<Object>();
	 try {
		JSONArray jsonArray=json.getJSONArray("results");
		list=jsonReflectList(jsonArray.toString());
	} catch (JSONException e) {
		
		e.printStackTrace();
	}
	
	return list;
}

/**
 * 将JSON数组转换为list集合
 * @param json
 * @return
 * @throws JSONException 
 */
public static List jsonReflectList(String jsonStr) throws JSONException{
	JSONArray array=new JSONArray(jsonStr);
    List<Object> list = new ArrayList<Object>();
    for(int i=0;i<array.length();i++){
    	Object o=array.get(i);
        if(o instanceof JSONArray)
            list.add(jsonReflectList(((JSONArray) o).toString()));
        else if(o instanceof JSONObject)
            list.add(jsonReflectMap(((JSONObject) o).toString()));
        else
            list.add(o);
    }
    return list;
}

/**
 * 将JSON对象转换为map
 * @param json
 * @return
 * @throws JSONException 
 */
public static HashMap jsonReflectMap(String jsonObj) throws JSONException{
	JSONObject json=new JSONObject(jsonObj);
    HashMap<String, Object> map = new HashMap<String, Object>();
    Iterator keys=json.keys();
    while(keys.hasNext()){
    	String key=(String)keys.next();
        Object o;
		try {
			o = json.get(key);
			if(o instanceof JSONArray)
				map.put((String) key, jsonReflectList(((JSONArray) o).toString()));
			else if(o instanceof JSONObject)
				map.put((String) key, jsonReflectMap(((JSONObject) o).toString()));
			else
				map.put((String) key, o);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
    }
    return map;
}

}

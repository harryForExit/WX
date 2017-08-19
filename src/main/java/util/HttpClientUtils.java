package util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;











import com.toft.utils.json.JSONException;
import com.toft.utils.json.JSONObject;

public class HttpClientUtils {
	
	/**
	 * 此部分为使用连接池方案
	 */
	
	//链接路由
    private static PoolingHttpClientConnectionManager connectionManager = null;
    private static HttpClientBuilder httpBulder = null;
    private static RequestConfig requestConfig = null;
    
    //第一台主机配置
    private static String IP1 = "202.103.25.123";//正式环境 10.2.2.59   测试 219.140.177.172 http://yingyan.baidu.com/api/v3/entity/add
    private static int PORT1 =8080 ;//8111   测试 8052 
    
    
    static {
        //设置http的状态参数
        requestConfig = RequestConfig.custom()
                .setSocketTimeout(30000)
                .setConnectTimeout(30000)
                .setConnectionRequestTimeout(30000)
                .build();
 
        //连接池目标主机第一个
        HttpHost target1 = new HttpHost(IP1,PORT1);
        
        //连接池配置
        connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(40);// 连接池大小 为MaxPerRoute的倍数
        connectionManager.setDefaultMaxPerRoute(10);//每个目标主机初始化连接数，此必须比MaxPerRoute与MAXCONNECTION小
        connectionManager.setMaxPerRoute(new HttpRoute(target1), 40);//将目标主机的最大连接数,因为只有一台那么就最大就为连接池最大数
        httpBulder = HttpClients.custom().setConnectionManager(connectionManager).setConnectionManagerShared(true);
    }
 
    private static CloseableHttpClient getConnection() {
        CloseableHttpClient httpClient = httpBulder.build();
        httpClient = httpBulder.build();
        return httpClient;
    }
 
 
    private static HttpUriRequest getRequestMethod(Map<String, String> map, String url, String method) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        for (Map.Entry<String, String> e : entrySet) {
            String name = e.getKey();
            String value = e.getValue();
            NameValuePair pair = new BasicNameValuePair(name, value);
            params.add(pair);
        }
        HttpUriRequest reqMethod = null;
        if ("post".equals(method)) {
            reqMethod = RequestBuilder.post().setUri(url)
                    .addParameters(params.toArray(new BasicNameValuePair[params.size()]))
                    .setConfig(requestConfig).build();
        } else if ("get".equals(method)) {
            reqMethod = RequestBuilder.get().setUri(url)
                    .addParameters(params.toArray(new BasicNameValuePair[params.size()]))
                    .setConfig(requestConfig).build();
        }
        return reqMethod;
    }
    
    
    /**
     * apache http post 请求方式
     * @param map  参数值
     * @param path 请求url
     * @return  JsonObject 
     * @throws IOException
     * @throws JSONException
     */
    public JSONObject doPost(HashMap map,String path) throws IOException, JSONException{
    	CloseableHttpClient client = getConnection();
    	JSONObject obj= null;
    	try{
	    	HttpUriRequest post = getRequestMethod(map,"http://"+this.IP1+":"+this.PORT1+path, "post");
	    	CloseableHttpResponse response = client.execute(post);
	    	try{
	            if (response.getStatusLine().getStatusCode() == 200) {//响应成功
	                HttpEntity entity = response.getEntity();
	                 obj=new JSONObject( EntityUtils.toString(entity, "utf-8"));
	            	return obj;
	            } else {
	            	obj=new JSONObject();
	            	obj.put("ErrorMessage", "请求失败");
	               return obj;
	            }	
	    	}catch(Exception e1){
	    		e1.printStackTrace();
	    		return null;
	    	}finally{
	    		response.close();
	    	}
    	}catch(Exception e){
    		e.printStackTrace();
    		obj=new JSONObject();
    		obj.put("ErrorMessage", "请求超时 ("+e.getMessage()+")");
    		return obj;
    	}finally{
    		client.close();
    	}
    }
    
    
    public JSONObject doPost1(HashMap map,String path) throws IOException, JSONException{
    	CloseableHttpClient client = getConnection();
    	JSONObject obj= null;
    	try{
	    	HttpUriRequest post = getRequestMethod(map,"http://"+this.IP1+":"+this.PORT1+path, "get");
	    	CloseableHttpResponse response = client.execute(post);
	    	try{
	            if (response.getStatusLine().getStatusCode() == 200) {//响应成功
	                HttpEntity entity = response.getEntity();
	                 obj=new JSONObject( EntityUtils.toString(entity, "utf-8"));
	            	return obj;
	            } else {
	            	obj=new JSONObject();
	            	obj.put("ErrorMessage", "请求失败");
	               return obj;
	            }	
	    	}catch(Exception e1){
	    		e1.printStackTrace();
	    		return null;
	    	}finally{
	    		response.close();
	    	}
    	}catch(Exception e){
    		e.printStackTrace();
    		obj=new JSONObject();
    		obj.put("ErrorMessage", "请求超时 ("+e.getMessage()+")");
    		return obj;
    	}finally{
    		client.close();
    	}
    }
    /**
     * 通过Apache httpClient 获取连接
     * @param map 参数  
     * @param url  请求地址
     * @return JSONObject
     * @throws JSONException 
     */
    public static JSONObject  getConnect(HashMap map,String url) throws JSONException{
    	JSONObject json=null;
    	try {
    		json=new HttpClientUtils().doPost(map, url);
    		System.out.println(json);
		} catch (IOException e) {
			e.printStackTrace();
			e.getMessage();
		}
    	return json;
    }
    
    public static JSONObject  getConnect1(HashMap map,String url) throws JSONException{
    	JSONObject json=null;
    	try {
    		json=new HttpClientUtils().doPost1(map, url);
    		System.out.println(json);
		} catch (IOException e) {
			e.printStackTrace();
			e.getMessage();
		}
    	return json;
    }
    
    public static void main(String args[]) throws IOException, InterruptedException, JSONException {
		      HashMap map = new HashMap();
		      map.put("json", "{\"merId\":\"777290058110048\",\"txnAmt\":\"10\"}");
//		      map.put("hphm", "鄂AHF581");
//		      map.put("hpzl", "02");
////          map.put("ccdjrq", "2015-05-05");//请求参数
////          map.put("sfzmhm", "420106198811118431");
////          map.put("dzjcxh", "4201070011604380");
////          map.put("jszh", "420104199305172410");
////          map.put("dabh", "420102897711");
////          map.put("jkbj", "1");
	        
		      /*for(int i=0;i<100;i++){
              	TestThread test=new TestThread();
              	test.start();  
          }*/
//		      map.put("code", "C105");
//		      map.put("json", "{\"sfzmhm\":\"AHF581\"}");
//		      map.put("license", "VbOOTGWNS5");
        HttpClientUtils.getConnect(map,"/proServer/dock/dockAction!pay.do");
		    
    }
    

    
//    public static void timer() {
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new TimerTask() {
//            public void run() {
//            	for(int i=0;i<100;i++){
//                	TestThread test=new TestThread();
//                	test.start();  
//                }
//            }
//        }, 1000, 3000);
//    }
/******
 * 下面为非连接池写法    
 */
 //   private Charset charset = Charset.forName("UTF-8");  
//	/**
//	 * 模拟post提交
//	 * @param url
//	 * @param path
//	 * @param pList
//	 * @return jsonobj
//	 */
//	public JSONObject doPost( String host,String path,List<Parameter> pList){
//		CloseableHttpClient httpclient = HttpClients.createMinimal(connectionManager);
//		try{
//			URIBuilder ub = new URIBuilder()
//				 .setScheme("http").setCharset(charset)
//				 .setHost(host)
//				 .setPath(path); 
//			 for(int i=0;i<pList.size();i++){
//				 Parameter p=(Parameter)pList.get(i);
//				 ub.setParameter(p.getKey(), p.getValue());
//			 }
//			 URI uri=ub.build();
//			 HttpPost post=new HttpPost(uri);
// 
//		   CloseableHttpResponse response = httpclient.execute(post);
//            try {
//            	StatusLine statusLine = response.getStatusLine();
//            	HttpEntity entity = response.getEntity();
//            	if (statusLine.getStatusCode() >= 300) {//返回参数
//	            	throw new HttpResponseException(statusLine.getStatusCode(),statusLine.getReasonPhrase());
//            	}
//            	if (entity == null) {
//            		throw new ClientProtocolException("未能获取对应的文本");
//            	}
//
//            	ContentType contentType = ContentType.getOrDefault(entity);
//            	Charset charset = contentType.getCharset();
//            	InputStreamReader reader =new InputStreamReader(entity.getContent(), charset);
//            	StringBuffer sb=new StringBuffer();
//            	try{
//                	BufferedReader bf=new BufferedReader(reader);
//                	 String line;
//            	    while((line = bf.readLine())!=null){
//                	     if(!line.trim().equals("")){
//                	    	 sb.append(line);
//                	     }
//            	    }
//                	
//            	}catch(Exception e1){
//            		e1.printStackTrace();
//            	}finally{
//            		reader.close();
//            	}
//
//            	
//            	JSONObject obj=new JSONObject(sb.toString());
//            	return obj;
////            	Gson gson = new GsonBuilder().create();
////            	JSONObject obj=gson.fromJson(reader,new TypeToken<Map<String,Object>>(){}.getType());
//            } finally {
//                response.close();
//            }
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//
//		return null;
//	}
//	
//	public static void main(String[] args) {
//		List pList =new ArrayList();
//		Parameter p=new Parameter("code","1111");
//		pList.add(p);
//		JSONObject obj=(new HttpClientUtils()).doPost("127.0.0.1:8080","/proServer/inf/inf!loadData.do",pList);
//		System.out.println(obj.toString());
//		//{"remark":"测试","code":"1111"}
//	}
  
}

package org.springrain.lottery.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.net.ssl.SSLContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.CookieStore;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;

/**
 * http请求工具类，主要针对java对于https的请求缺少证书报错的问题，该工具类包含了get和post请求
 * @author Administrator
 *
 */


public class SSLClientGetAndPost{
	
	
	public static CookieStore cookieStore = null;
	
	private static final Log LOGGER = LogFactory.getLog(SSLClientGetAndPost.class);  
	/**
	 * 用 GET 方法提交数据
	 * 作者：龚国君
	 * @date 2014年1月23日 上午8:50:27
	 * @param @param url
	 * @param @param ParamMap
	 * @param @param headerParamMap
	 * @param @param debug
	 * @param @return
	 * @param @throws ClientProtocolException
	 * @param @throws IOException
	 * @return String
	 * @throws
	 * @param url
	 * @param ParamMap
	 * @param headerParamMap
	 * @param debug
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String get(String url,Map<String,Object> ParamMap,Map<String,String> headerParamMap){
		try {
			CloseableHttpResponse execute;
		    CloseableHttpClient client;
		    
		    if(ParamMap != null && ParamMap.size() > 0){
		    	url = formatGetParameter(url, ParamMap);
		    }
		    
		    
		    HttpGet get = new HttpGet(url);
		    if(headerParamMap != null && headerParamMap.size() > 0){
		    	for(String key: headerParamMap.keySet()){
		    		get.addHeader(key,headerParamMap.get(key));
		    	}
		    }
		    
		    client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		            
		    if(url.startsWith("https")){
		    	client = createSSLClientDefaultGet();
		    }
		    //System.out.println("cookie store:" + cookieStore.getCookies());
		    
		    
	        execute = client.execute(get);
	       
	        String content = EntityUtils.toString(execute.getEntity(),"UTF-8");
        	LOGGER.debug(url);
        	Charset charset = ContentType.getOrDefault(execute.getEntity()).getCharset();
        	LOGGER.debug(charset);
        	LOGGER.debug(execute.getStatusLine().getStatusCode());
        	LOGGER.debug(content);
	        try {
	        	execute.close();
	 	        client.close();
			} catch (Exception e) {}
	        return content;
		} catch (Exception e) {
			LOGGER.error(e);
			return null;
		}
	}
	/**
	 * 用POST方式提交数据
	 * @param @param url
	 * @param @param body
	 * @param @param ParamMap
	 * @param @param headerParamMap
	 * @param @param debug
	 * @param @return
	 * @return String
	 * @throws
	 * @param url
	 * @param body
	 * @param ParamMap
	 * @param headerParamMap
	 * @param debug
	 * @return
	 */
	public static String postMethodUrl(String url,String body,Map<String,Object> paramMap,Map<String,String> headerParamMap){
	    return postMethodUrl(url, body, paramMap, headerParamMap,null,null,null);
	}
	public static String postMethodUrl1(String url,String body,Map<String,Object> paramMap,Map<String,String> headerParamMap){
	    return postMethodUrl1(url, body, paramMap, headerParamMap,null,null,null);
	}
	public static String putMethodUrl(String url,String body,Map<String,Object> paramMap,Map<String,String> headerParamMap){
	    return putMethodUrl(url, body, paramMap, headerParamMap,null,null,null);
	}
	public static String httpPostUpload(String url,Map<String,String> paramMap,FileBody bin,Map<String,String> headerParamMap){
	    return postMethodUrl(url, null, null, headerParamMap,bin,paramMap,null);
	}
	/**
	 * 第一次对请求，为了认证，保存下了CookieStore
	 * @param url
	 * @param body
	 * @param paramMap
	 * @param headerParamMap
	 * @param bin
	 * @param othersParams
	 * @param closeableHttpClient
	 * @return
	 */
	public static String postMethodUrl(String url,String body,Map<String,Object> paramMap,Map<String,String> headerParamMap,FileBody bin,Map<String,String> othersParams,CloseableHttpClient closeableHttpClient){
		try {
			CloseableHttpResponse execute;
		    CloseableHttpClient client;
		    
		    HttpPost post = new HttpPost(url);
		    if(headerParamMap != null && headerParamMap.size() > 0){
		    	for(String key: headerParamMap.keySet()){
		    		post.addHeader(key,headerParamMap.get(key));
		    	}
		    }
		    if(paramMap != null && paramMap.size() > 0){
		    	List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		    	for(String key: paramMap.keySet()){
		    		Object value = paramMap.get(key);
		    		if (value != null) {
		    			nvps.add(new BasicNameValuePair(key,String.valueOf(value)));
					}
		    	}
		    	post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
		    }
		    if(bin!=null){
		    	 MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create().addPart("file", bin);
		         if (null != othersParams && !othersParams.isEmpty()) {
		              for (Map.Entry<String, String> entry : othersParams.entrySet()) {
		                  multipartEntityBuilder.addPart(entry.getKey(), new StringBody(entry.getValue(), ContentType.create("text/plain","utf-8")));
		              }
		         }
		         HttpEntity reqEntity = multipartEntityBuilder.build();
		         post.setEntity(reqEntity);
		    }
		    if(body != null && !"".equals(body)){
		    	post.setEntity(new StringEntity(body, "UTF-8"));
		    }
		    if(closeableHttpClient == null){
		        client = HttpClients.custom()
	                    .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:41.0) Gecko/20100101 Firefox/41.0")  
	                    .setDefaultRequestConfig(
	                            RequestConfig.custom()
	                                .setSocketTimeout(60000)
	                                .setConnectTimeout(60000)
	                                .setConnectionRequestTimeout(60000)
	                                .build()).build();
	            if(url.startsWith("https")){
	                client = createSSLClientDefaultPost();
	            }
		    }else{
		        client = closeableHttpClient;
		    }
		    
	        execute = client.execute(post);
	        //TODO
//	        String setCookie = execute.getFirstHeader("Set-Cookie").getValue();
//	        System.out.println("response.cookie: "+setCookie);
	       
	        String content = EntityUtils.toString(execute.getEntity());
	        LOGGER.debug(url);
        	Charset charset = ContentType.getOrDefault(execute.getEntity()).getCharset();
        	LOGGER.debug(charset);
        	LOGGER.debug(execute.getStatusLine().getStatusCode());
        	LOGGER.debug(content);
	        execute.close();
	        client.close();
	        return content;
		} catch (Exception e) {
			LOGGER.error(e);
			return null;
		}
	}
	/**
	 * 认证之后的post,在Post请求中直接set之前存好的CookieStore,不用新建
	 * @param url
	 * @param body
	 * @param paramMap
	 * @param headerParamMap
	 * @param bin
	 * @param othersParams
	 * @param closeableHttpClient
	 * @return
	 */
	public static String postMethodUrl1(String url,String body,Map<String,Object> paramMap,Map<String,String> headerParamMap,FileBody bin,Map<String,String> othersParams,CloseableHttpClient closeableHttpClient){
		try {
			CloseableHttpResponse execute;
		    CloseableHttpClient client;
		    
		    HttpPost post = new HttpPost(url);
		    if(headerParamMap != null && headerParamMap.size() > 0){
		    	for(String key: headerParamMap.keySet()){
		    		post.addHeader(key,headerParamMap.get(key));
		    	}
		    }
		    if(paramMap != null && paramMap.size() > 0){
		    	List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		    	for(String key: paramMap.keySet()){
		    		Object value = paramMap.get(key);
		    		if (value != null) {
		    			nvps.add(new BasicNameValuePair(key,String.valueOf(value)));
					}
		    	}
		    	post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
		    }
		    if(bin!=null){
		    	 MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create().addPart("file", bin);
		         if (null != othersParams && !othersParams.isEmpty()) {
		              for (Map.Entry<String, String> entry : othersParams.entrySet()) {
		                  multipartEntityBuilder.addPart(entry.getKey(), new StringBody(entry.getValue(), ContentType.create("text/plain","utf-8")));
		              }
		         }
		         HttpEntity reqEntity = multipartEntityBuilder.build();
		         post.setEntity(reqEntity);
		    }
		    if(body != null && !"".equals(body)){
		    	post.setEntity(new StringEntity(body, "UTF-8"));
		    }
		    if(closeableHttpClient == null){
		        client = HttpClients.custom()
	                    .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:41.0) Gecko/20100101 Firefox/41.0")  
	                    .setDefaultRequestConfig(
	                            RequestConfig.custom()
	                                .setSocketTimeout(60000)
	                                .setConnectTimeout(60000)
	                                .setConnectionRequestTimeout(60000)
	                                .build()).build();
	            if(url.startsWith("https")){
	                client = createSSLClientDefaultPost1();
	            }
		    }else{
		        client = closeableHttpClient;
		    }
		    
	        execute = client.execute(post);
	        //TODO
//	        String setCookie = execute.getFirstHeader("Set-Cookie").getValue();
//	        System.out.println("response.cookie: "+setCookie);
	       
	        String content = EntityUtils.toString(execute.getEntity());
	        LOGGER.debug(url);
        	Charset charset = ContentType.getOrDefault(execute.getEntity()).getCharset();
        	LOGGER.debug(charset);
        	LOGGER.debug(execute.getStatusLine().getStatusCode());
        	LOGGER.debug(content);
	        execute.close();
	        client.close();
	        return content;
		} catch (Exception e) {
			LOGGER.error(e);
			return null;
		}
	}
	/**
	 * 还车所需的put请求
	 * @param url
	 * @param body
	 * @param paramMap
	 * @param headerParamMap
	 * @param bin
	 * @param othersParams
	 * @param closeableHttpClient
	 * @return
	 */
	public static String putMethodUrl(String url,String body,Map<String,Object> paramMap,Map<String,String> headerParamMap,FileBody bin,Map<String,String> othersParams,CloseableHttpClient closeableHttpClient){
		try {
			CloseableHttpResponse execute;
		    CloseableHttpClient client;
		    
		    HttpPut put = new HttpPut(url);
		    if(headerParamMap != null && headerParamMap.size() > 0){
		    	for(String key: headerParamMap.keySet()){
		    		put.addHeader(key,headerParamMap.get(key));
		    	}
		    }
		    if(paramMap != null && paramMap.size() > 0){
		    	List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		    	for(String key: paramMap.keySet()){
		    		Object value = paramMap.get(key);
		    		if (value != null) {
		    			nvps.add(new BasicNameValuePair(key,String.valueOf(value)));
					}
		    	}
		    	put.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
		    }
		    if(bin!=null){
		    	 MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create().addPart("file", bin);
		         if (null != othersParams && !othersParams.isEmpty()) {
		              for (Map.Entry<String, String> entry : othersParams.entrySet()) {
		                  multipartEntityBuilder.addPart(entry.getKey(), new StringBody(entry.getValue(), ContentType.create("text/plain","utf-8")));
		              }
		         }
		         HttpEntity reqEntity = multipartEntityBuilder.build();
		         put.setEntity(reqEntity);
		    }
		    if(body != null && !"".equals(body)){
		    	put.setEntity(new StringEntity(body, "UTF-8"));
		    }
		    if(closeableHttpClient == null){
		        client = HttpClients.custom()
	                    .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:41.0) Gecko/20100101 Firefox/41.0")  
	                    .setDefaultRequestConfig(
	                            RequestConfig.custom()
	                                .setSocketTimeout(60000)
	                                .setConnectTimeout(60000)
	                                .setConnectionRequestTimeout(60000)
	                                .build()).build();
	            if(url.startsWith("https")){
	                client = createSSLClientDefaultPost1();
	            }
		    }else{
		        client = closeableHttpClient;
		    }
		    
	        execute = client.execute(put);
	        //TODO
//	        String setCookie = execute.getFirstHeader("Set-Cookie").getValue();
//	        System.out.println("response.cookie: "+setCookie);
	       
	        String content = EntityUtils.toString(execute.getEntity());
	        LOGGER.debug(url);
        	Charset charset = ContentType.getOrDefault(execute.getEntity()).getCharset();
        	LOGGER.debug(charset);
        	LOGGER.debug(execute.getStatusLine().getStatusCode());
        	LOGGER.debug(content);
	        execute.close();
	        client.close();
	        return content;
		} catch (Exception e) {
			LOGGER.error(e);
			return null;
		}
	}
	/**
	 * 格式化GET参数
	 * 作者：龚国君
	 * @date 2014年1月16日 下午6:04:42
	 * @param @param url
	 * @param @param argsMap
	 * @param @return
	 * @return String
	 * @throws
	 * @param url
	 * @param argsMap
	 * @return
	 */
	public static String formatGetParameter(String url,Map<String, Object> argsMap){
		if (url!=null && url.length()>0) {
			if (!url.endsWith("?")) {
				url = url +"?";
			}
			if (argsMap!=null && !argsMap.isEmpty()) {
				Set<Entry<String, Object>> entrySet = argsMap.entrySet();
				Iterator<Entry<String, Object>> iterator = entrySet.iterator();
				while(iterator.hasNext()){
						Entry<String, Object> entry = iterator.next();
						if (entry!=null) {
							String key = entry.getKey();
							String value = (String) entry.getValue();
							url = url + key + "=" + value;
							if (iterator.hasNext()) {
								url = url +"&";
							}
						}
				}
			}
		}
		return url;
	}
	
	public static CloseableHttpClient createSSLClientDefaultGet() {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				// 信任所有
				public boolean isTrusted(X509Certificate[] chain,String authType) throws CertificateException {
					return true;
				}
			}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
			//System.out.println(cookieStore);
			return HttpClients
					.custom()
					.setDefaultCookieStore(cookieStore)
					.setSSLSocketFactory(sslsf)
					.setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:41.0) Gecko/20100101 Firefox/41.0")
					.setDefaultRequestConfig(RequestConfig.custom()
		                    .setSocketTimeout(10000)
		                    .setConnectTimeout(10000)
		                    .setConnectionRequestTimeout(10000)
                            .build())
					.build();
		} catch (KeyManagementException e) {
			LOGGER.error(e);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error(e);
		} catch (KeyStoreException e) {
			LOGGER.error(e);
		}
		return null;
	}
	//保存CookieStore
	public static CloseableHttpClient createSSLClientDefaultPost() {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				// 信任所有
				public boolean isTrusted(X509Certificate[] chain,
				String authType) throws CertificateException {
					return true;
				}
			}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
			
			cookieStore = new BasicCookieStore();
			//System.out.println(cookieStore);
			return HttpClients
					.custom()
					.setDefaultCookieStore(cookieStore)
					.setSSLSocketFactory(sslsf)
					.setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:41.0) Gecko/20100101 Firefox/41.0")
					.setDefaultRequestConfig(RequestConfig.custom()
		                    .setSocketTimeout(10000)
		                    .setConnectTimeout(10000)
		                    .setConnectionRequestTimeout(10000)
                            .build())
					.build();
		} catch (KeyManagementException e) {
			LOGGER.error(e);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error(e);
		} catch (KeyStoreException e) {
			LOGGER.error(e);
		}
		return null;
	}
	public static CloseableHttpClient createSSLClientDefaultPost1() {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				// 信任所有
				public boolean isTrusted(X509Certificate[] chain,
				String authType) throws CertificateException {
					return true;
				}
			}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
			
			return HttpClients
					.custom()
					.setDefaultCookieStore(cookieStore)
					.setSSLSocketFactory(sslsf)
					.setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:41.0) Gecko/20100101 Firefox/41.0")
					.setDefaultRequestConfig(RequestConfig.custom()
		                    .setSocketTimeout(10000)
		                    .setConnectTimeout(10000)
		                    .setConnectionRequestTimeout(10000)
                            .build())
					.build();
		} catch (KeyManagementException e) {
			LOGGER.error(e);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error(e);
		} catch (KeyStoreException e) {
			LOGGER.error(e);
		}
		return null;
	}
	
	
	public static void main(String[] args) {
		//https://test.lima.adonging.com/v1/documentation#!/auth/login_0
		//{"username":"ant","password":"ant123"}
		//认证请求Post
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username","ant");
		map.put("password", "ant123");
		String postMethodUrl = SSLClientGetAndPost.postMethodUrl("https://test.lima.adonging.com/v1/cooper-auth", null, map, null);
		System.out.println("返回的认证信息： "+postMethodUrl);
		
		//扫码解锁请求Post
//		Map<String, Object> map1= new HashMap<String, Object>();
//		map1.put("code", "002669929");
//		//map1.put("code", "MDAyNjY5OTI5");
//		String postMethodUrl1 = SSLClientGetAndPost.postMethodUrl1("https://test.lima.adonging.com/v1/order", null, map1, null);
//		if(postMethodUrl1.contains("msg")){
//			System.out.println("问题车辆，请使用其他车辆!");
//		}else{
//			Long id = JSON.parseObject(postMethodUrl1).getLong("id");
//			System.out.println("id = "+id);
//		}
//		System.out.println("解锁成功"+postMethodUrl1);
		//2277
		//中途上锁开锁请求Post
//		Map map2 =new HashMap();
//		map2.put("motor_id", "4965");//车辆id
//		map2.put("command", "3");//指令号 3 - 锁车 5 - 启动
//		String postMethodUrl2 = SSLClientGetAndPost.postMethodUrl1("https://test.lima.adonging.com/v1/command", null, map2, null);
//		System.out.println(postMethodUrl2);
		
		//还车请求Put
//		Map<String, Object> map3 =new HashMap<String, Object>();
//		map3.put("end_time", new Date().getTime()); 
//		//map3.put("return_user_lng", "120.197216");
//		//map3.put("return_user_lat", "30.193310");
//		String id ="2431";
//		String putMethodUrl = SSLClientGetAndPost.putMethodUrl("https://test.lima.adonging.com/v1/order/"+id, null, map3, null);
//		if(JSON.parseObject(putMethodUrl).containsKey("msg")){
//			System.out.println("问题车辆，请使用其他车辆!");
//		}
//		System.out.println("还车成功 ："+putMethodUrl);
		
		//获取附近车辆请求Get
		String json = SSLClientGetAndPost.get("https://test.lima.adonging.com/v1/motors/120.197216,30.193310", null, null);
		System.out.println("返回的车辆信息: "+json);
		List<Object> list1 = new ArrayList<Object>();
		if(!json.contains("msg")){
			JSONArray parseArray = JSON.parseArray(json);
			for (int i = 0; i < parseArray.size(); i++) {
				JSONObject jsonObject = parseArray.getJSONObject(i);
//				
//				String string = "{" + "lnglat:[" + jsonObject.getString("lng") + "," + jsonObject.getString("lat") + "],name:" + jsonObject.getString("serial")
//						+ ",electricity:" + "100" + ",state:"
//						+ "1" + ",o_id:" + "2"
//						+ "}";
				Map<String, Object> map3 = new HashMap<String, Object>();
				List<String> list = new ArrayList<String>();
				list.add(jsonObject.getString("lng") );
				list.add(jsonObject.getString("lat"));
				map3.put("lnglat",list );
				map3.put("name", jsonObject.getString("serial"));
				map3.put("electricity", "100");
				map3.put("state", "1");
				map3.put("o_id", "2");
				list1.add(JSON.toJSON(map3));
			}
		}
		System.out.println(list1);
	}
}

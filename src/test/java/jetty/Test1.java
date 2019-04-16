package jetty;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springrain.frame.util.SecUtils;
import org.springrain.lottery.utils.MD5Util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Test1 {
	// <?xml version="1.0" encoding="utf-8"?>
	// <content>
	// <head>
	// <version>协议版本号(默认输入 1.0)</version>
	// <merchant>接入商代码</merchant>
	// <command>业务代码</command>
	// <messageid> 消息序列号</messageid>
	// <timestamp>发起请求的时间 (yyyyMMddHHmmss)</timestamp>
	// </head>
	// <body>加密消息体
	// <message>
	// <content>
	// <merchant>80005</merchant>
	// </content>
	// <message/>
	// </body>
	// <signature> 内容校验码</signature>
	// </content>
	private static String key = "123456789ABCDEA";
	private static String url = "http://47.93.87.34/lottery/b2b/bet";

	public static void main(String[] args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();

		String merchant = "80015"; // 接入商代码
		String command = "806";// 余额查询 //业务代码
		String messageid = merchant + command + sdf.format(date);
		String timestamp = sdf.format(date); 
		String signature = SecUtils.encoderByMd5With32Bit(command + timestamp + merchant + key);

		String body = "<message><content><merchant>80015</merchant></content></message>";
		// 对消息体加密
		String bodys = SecretUtilTools.encryptForDES(body, key);
		StringBuffer bf = new StringBuffer();
		bf.append("<?xml version='1.0' encoding='utf-8'?>");
		bf.append("<content><head>");
		bf.append("<version>1.0</version>");
		bf.append("<merchant>"+merchant+"</merchant>");
		bf.append("<command>"+command+"</command>");
		bf.append("<messageid>"+messageid+"</messageid>");
		bf.append("<timestamp>"+timestamp+"</timestamp>");
		bf.append("</head>");
		bf.append("<body>"+bodys+"</body>");
		bf.append("<signature>"+signature+"</signature>");
		bf.append("</content>");
		
		String xml = bf.toString();
		
		String xmlHttpProxy = HttpXmlUtils.xmlHttpProxy(url, xml,"Post", "utf-8");
		System.out.println("响应信息 :"+xmlHttpProxy);
		Document document = DocumentHelper.parseText(xmlHttpProxy);
		Element root=document.getRootElement();
//		listNodes(root);
		Element element =root.element("body");
		String text=element.getText();
		System.out.println(text);
		System.out.println(SecretUtilTools.decryptForDES(text, key));
//		System.out.println(DecryptString(text,key));
	}



	
	
	
	 public static void listNodes(Element node){  
	        System.out.println("当前节点的名称：" + node.getName());  
	        //首先获取当前节点的所有属性节点  
	        List<Attribute> list = node.attributes();  
	        //遍历属性节点  
	        for(Attribute attribute : list){  
	            System.out.println("属性"+attribute.getName() +":" + attribute.getValue());  
	        }  
	        //如果当前节点内容不为空，则输出  
	        if(!(node.getTextTrim().equals(""))){  
	             System.out.println( node.getName() + "：" + node.getText());    
	        }  
	        //同时迭代当前节点下面的所有子节点  
	        //使用递归  
	        Iterator<Element> iterator = node.elementIterator();  
	        while(iterator.hasNext()){  
	            Element e = iterator.next();  
	            listNodes(e);  
	        }  
	    }
}



	

package org.springrain.lottery.utils.daifu;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import ch.qos.logback.core.net.server.Client;


/**
 * properties帮助类 默认加载yeePay.properties
 */
public class YeePayConfig {


	private static final String config = "yeePay.properties";

	private static Map<String, String> config_map = new HashMap<String, String>();

	static {
		load(config);
	}

	public static String getProperty(String key) {
		if (StringUtils.isBlank(key)) {
			return null;
		}
		return config_map.get(key);
	}
	
	public static Boolean isExistKey(String key) {
		if (StringUtils.isBlank(key)) {
			return false;
		}
		return config_map.containsKey(key);
	}
	
	
	private static void load(String name) {
		Properties p = new Properties();
		try {
			p.load(new InputStreamReader(Client.class.getClassLoader().getResourceAsStream(config), "UTF-8"));    
			if (config.equals(name)) {
				for (Map.Entry<Object,Object> e : p.entrySet()) {
					config_map.put((String) e.getKey(), (String) e.getValue());
				}
			}

		} catch (IOException e) {
		}
	}

}

package jetty;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;



public class A {
	public static void main(String[] args) throws ParseException, ClientProtocolException, IOException {
//		String merchant = "80015";
//		String privatekey="123456789ABCDEA";
//		String url ="http://47.93.87.34/lottery/b2b/bet";
//		String xml = "<message><orderlist><order><orderid>zy2014090234322</orderid></order></orderlist></message>";
//		String httpsRequest = HttpXmlUtils.xmlHttpProxy(url,xml, "POST","UTF-8");
//		System.out.println(httpsRequest);
		System.out.println("MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALlkSmZHI1OrN+PJNRk9B9Kay6U9ZXwwyP25scH5XqkgMxYm/e8R/2BJwBg8rm+A5i31ANToNR/TAXjOua/SpNPDvxOdGPSbgY4G2V6a6iNCEPJkcgpXo1e1gNJkIAT1QEoBTH2MXaVvsGjy1h2ZOU+hHIgejD2DtbtT5SY5Wp8TAgMBAAECgYBXtoKY12l4aZa81/KeaUitNP3KxTyhwHcnzFa03qYRwKFLBtG37bz0spAHJ5akPMkqzzNAiEiyMOQOY0hpTnEazIeJo1bOdjOTDoZY6Gn0tcuTfwRg1SfLdKJKMitWCOUpgxLorn0H/MFMDy56XP5zSxJrEBNQKUCuk8TMaGDdcQJBAPSaOuT2+fVwStmQpmG5vD6E3RR3UT/ALyIJ/PpIZO5l2HZOZvbk2ZiUGlAo+zLmM0YexMZWhzaSSiaQN+Z0vRsCQQDCB8Iyhvh4EEcdQWSOmcAgBT2W6u4eVItKuI3tbvfDdNCJbbsHECMAs/VmA3kkydlcPYZLTWF0uE+g38pf9x1pAkAkBiLuXcFqeGNNr5QRiH1E5+R3gysgLHnElZwAcHEM+0rqAZoAeqvhYM6PR7nXQTgD97wbSdsYGmt5SnwxILhJAkEAu/hMOCrtl1MdscfiJQvqRpsNGaGq4ZNCBjdYt8ajHKWUSS8W2zbU0h+FAzkmKW+qtWOdMGJcoXjVG6LyKZn60QJARcKMoFpcF+4sd9XX/MNr3IDX5qUu8pIn6ohVUPQi988h9NQ8UB5PogD7lKLSnrkFLptH/+n9xM2DlO2pUmV++A==".length());
		System.out.println("MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCmydNtBoyfT93xGJqqWg1fj+HthGJXs+o0suQhgLFR0qYLe0Z1O72kIBjh4u41OA9/u9VA4ojAYDcEZXv3qQHvBAxlHH1aY486sXP12Ep1D343rRsK5ahs1TDQq/agGWRwSxX9RN8fMcmdZvwXVDgv7TGMBTy/P+CSepyTJjozvoV4YWxwvcXzWrAA70OEu03T4uN3X6uLpWypYMPHRi6jyRdzazWiMpyPBBKZ1lA/08ttQcIBSMlTFQOjaqs3owJJBY4/1n8IHH4EiT3Qf9xvmgaRnbyy6LF96HO/wSL/tiwTGbz4xtKXXRjiHuZmCDJbuoeuPXTSwcU4/WzjBcVlAgMBAAECggEAAuLPI73PRRpurrNMSdJRFOw6bo+x2+6jCNJnnCTL7KyGbwBtEa4889ASXiY9FPKpYf5wDDVfRGTlzs+qu3hTLj2VsVIy4ROD8PINHZ3me+wOtQvrwd6DcY6mz5WKKO8Hi+HguYctjS6hXHXhus2cQ0gJaXDVwkFqs14nw2wP6cFFCNDhNM20jnQEVrp4Ia8DZ84CyWkJvVyIMPIBDHC+n4o7i0V4mRzSAJjUoSDwTXnYVbwL5fvXOOQRlWes/begUxPOVK7A7oLBpC0iPySA1J8qrYtEpVUFgL78RApZTnSwZH3qznD0vsK3ThdFgc6tWkn548JC35qgUt0CX/vxKQKBgQDZtCk0+OHV3gMJXcD7Bqf7dIqpHjB4SrQmf1u5xkyMyseBQwEOn9zH9fSzn5G4QYJkJ7HNEG8sPbnWuGh0EG2575xyDIYVpLfSLPOxUhPao+KJgiezxnUd9I6mWLBnINaiN1knsvNM3J5/fFcajLJTgRpPebEgwu7MSdMWLc8/DwKBgQDEIMvaFFLTmme54jVQIimmnjwZ+JpluzzGggSWxWyGPfLk3Kh9HRriRj9nkLbzjdyCAUzELA4qsGFjqxAfXFh9d6GuYDgeJ2oODvGPI/PqEFhhW8bPcp8u55qW/boxh7whS6d1Iqm3dVXWKvSV4ISQhsxtFyqpbPIkZLSrXYz0SwKBgQCIpONEY37kjRAc3eLV7OydER5DXjaQGluO2luJdGTx7glhIdxVAJREUiXAZMTDASNFmACqtMkC3M97bY3qY7FrhnEMyL2UcioK2rour1TU5A65vp8K4OL75VdwI7S9VkhB0zm7iD/cG5HEdRf7CxqW/S61ea1q4c+fHCtI/YHakwKBgQCWjmqOdU2DvKLlkd3FLwR0MEII2cuETrB/3i0xpEoD3yhHQZBhwubxTmW71d5rprngXqkUapFReea5AQc2W8heQGiwGJLbqvwB4LzUWhAS3QtLaY17kAzcj9QfFpVJEl573Lym8b4A5CTJbF4nc5uv6pqhH8iTGNvkzh3W8tCScwKBgGCO28O8+To0f7BYqyvsGcUUldG+Z1ROGR3yNk/VzTnj9hcXo3RkreazHq/p8qUjJWxI29bGz6ZZUjCKyKI0O6WG+FZf5DNpZbI/k54UqE03bPEaPOmxGSyIMoVMqI6slff/OK2GgtqhDuDFHo97pcO8q2rMgCKUZ52h5Mwceau2".length());
		System.out.println("A101,sdfsd,1A101,sdfsd".indexOf(","));
		System.out.println();
	}
}

package org.springrain.pay.web;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springrain.frame.controller.BaseController;
import org.springrain.pay.utils.HttpApi;
import org.springrain.pay.utils.JUtil;
import org.springrain.pay.utils.SecurityUtil;
import org.springrain.pay.utils.TdExpBasicFunctions;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-11-07 13:43:08
 * @see org.springrain.lottery.web.BalancePaymentController
 */
@Controller
@RequestMapping(value="/balancePayment")
public class BalancePaymentController   extends BaseController {
	
//	@Value("#{configProperties['daohe.prvKey']}")
//	String prvKey;
//	@Value("#{configProperties['daohe.desKey']}")
//	String desKey;
//	//平台公钥
//	@Value("#{configProperties['daohe.pubKey']}")
//	String pubKey;
//	@Value("#{configProperties['daohe.md5Key']}")
//	String md5Key;
//	@Value("#{configProperties['daohe.agtId']}")
//    String agtId;
//	@Value("#{configProperties['daohe.merId']}")
//	String merId;
//	@Value("#{configProperties['daohe.url']}")
//	String url;
//	@Value("#{configProperties['daohe.notifyUrl']}")
//	String notifyUrl;
	
	String prvKey="MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCGIZJV0TZtSk3LQFVNBapQNmmEsxjZrBexPWk59Zpim5b4kbM3m1cqqLEiqyD6ayutuXgqQYRJ4I6omLJp0a8A+iNvc9XHxZdZtoPpN1dKWvmKXWYn2ccjzzpHrYmCOCl0XrLbqEpbNTzkeFcOmI1Ydi456DiCzawt33+YXzYCqST1tUUwX0OZPsWeR/eoqA2e8OnGKYkOvAW7awkvjNJL3NPw7Z6fG9pacQQjySCoyjxfigrX+Ddr6TEHYQS4Q1hqHZXNsr9U/NyJ3o0Htxs27vE5I8Ou+kqw9Y3BbyFWSB9mc+f9cQjsCaxV4UCtlWVBKnF+U4qQAoUu8b3kwVMLAgMBAAECggEAKxq80TFToVmgmHHLN95bNj3WORvpH4Krdnz6xPKFs6y2npkMFBGquH3PtwlxpqgsOD8DO+kkHy24CrxPkawd5l18Z8O87t8z3VPHHnp3ZeDIksujGall0r80xnYhW6S6xbftGKQDiT4ZnH6D05/4xHXuMxPKnVaFP5S0h34JeF715xhLTuEX9+fLfpK1wWyTE7C1q+idg0Ryf+WI47q2LJdc/Biebvuqi6cgcfqZcFvW4qDuyfXETGRrF6MEAn4hcuVSVIcemnMK179UCbThLGlgVZNYbZZH4BN80JFAT6zEavSOGQZrjs1aIOHO3Jpd7EMuBldKGImq/8rwvX3MIQKBgQC++hn0g9gSH8BQesQhCR6YdwqidwC6Gv06t80eHAMuztpurzwl2JRNiwzop6SXxKEw2RkoyAqcLdvcB5JBA2Uv2rAGGhS/HnIb6/n29qCxKyPeyWQAkwZgYj9kkKEkH+TjiuaxC+ihvY3Nhtj01uh8bPU3zRCKqhph4aMOwM0cEQKBgQCzzK9zi4WdbLWpVzh46tiqsjc9XvUn7exm7J5ubxt9WpdvaaoM+eVhquSfbZFwoUqXHx/NkeWIQQuyhTvQEwUg7S7+9a/tmLk+DGrzuM455VoXwtVi2m2FqIiqWc2gPQw2vmEp5NlDqVZWBhV82vm/gYC97zBAQwSr9Pn6/t/JWwKBgBw0dcbd/fhXRiDcsMCsRx9/XUu3RZ02xTV51cpfmry8AhicusZgIS7+nU5zC+T6PlAnkAas3JxKTZFGcPw3EyepJZwRGAVN6s5i4IzvybXAyFBr7+AB1sw/Vlt03MeoCjbs2btGJfpVM28qPjqSzs0pjmHKYGoKS1EvcNSJ9wDxAoGAHUUsJuZGyTsCU+AUHHM29IrwvTfxNx/fhV3t6NKDqiOOVZ/hBB3GfuDgOSgQLkLDrthtR/0BXe4g5l4UP6ppAlQ6e10p18cuGPDSlHy4beptgULBlEc03zZxkTHvbK+jhmH11WkhCYfOu1BgPSQ01j1R5RnERCHk582IyJine9MCgYAgrYudt53dP95KYDXtYB8OGRsZDXE/5vqIZhvS/2Xdw4yNKzJ/OgilcEEDAxv8qzhuuWRxmrUGgM4V87poX1nuo1RlOAxrpZ/U9fQPMXa6XBguZUPZWVCikx200d//MedjYg8jUn47cTsOzBqn23E3lke2ZzxQsOD9wwTLy6NnLw==";
	String desKey="12456789012345678900234567890ab";
	String pubKey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAloiANzadGTuPbPl6yAZG7uBJt32yNLB5D4PTpkUJTFcVV42szRIjBXouRynvEwFVI1pFsAuS1K0FrkjRJZMKM/Z1TADWWWUVN4Ih3DyfLpixOU0+kgdP9m+D35KVMW1gJMCiEXntU/of0l/1fxeetOuta6B7nVeAUUIoN8RnVuD9NF11jG5LhFnI2GeZRdzZhcgu9aNkFl1lhatzTzgtFHn0fG/F9p/jD05RQCtBFO1KC2EjG9SVpX9IbQr388khQXYTS7gZEaJByIMB+JAp6ytWXglXakTYKtNnOpyJIRnyYjBcJDxhMv6MMc6VKzLrCFe2nKow3ilwOZ0y7TsCwQIDAQAB";
	String md5Key="jiGtc068bOFoApUVaXszgBIFhLdXgzYi";
	String agtId="18040068";
	String merId="1804006841";
	String url="http://47.75.70.115:18098/webwt/pay/gateway.do";
	String notifyUrl="http://api1.hqjc888.com/api/v2/CPPAY/merNotify";

	/**
	 * 商户余额代付
	 */
	@RequestMapping(value="/get/payfor")
	@ResponseBody
	public void payfor(@PathVariable("ordId") String ordId) {
		HttpApi http = new HttpApi(url, HttpApi.POST);
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> hdata = new HashMap<String, Object>();
		data.put("agtId", agtId);
		data.put("merId", merId);
		data.put("tranCode", "2101");
		data.put("nonceStr", TdExpBasicFunctions.RANDOM(16, "0"));
		data.put("tranDate", TdExpBasicFunctions.GETDATE());
//		String ordId = TdExpBasicFunctions.RANDOM(19, "2");
		data.put("orderId", ordId);
		data.put("txnAmt", "10655");
		data.put("accountNo", "6214850212605205");// 卡号
		data.put("certNum", "411024199101017012");// 身份证号
		data.put("bankCode", "CMB");// 银行编码
		data.put("bankName", TdExpBasicFunctions.STR2HEX("招商银行"));// 银行名称
		data.put("accountName", TdExpBasicFunctions.STR2HEX("王光伟"));// 持卡人
		data.put("bankProv", "310000");// 开户省
		data.put("bankCity", "310100");// 开户市
		data.put("cnaps", "308290003298");// 联行号
		data.put("bankBranch", TdExpBasicFunctions.STR2HEX("招商银行股份有限公司上海天山支行"));// 支行
		data.put("accountType", "1");
		data.put("mobile", "13661773442");

		data.put("nonceStr", TdExpBasicFunctions.RANDOM(16, "0"));
		data.put("orderId", ordId);
		data.put("tranDate", TdExpBasicFunctions.GETDATE());

		String sign = HttpApi.getSign(data, md5Key);
		System.out.println(sign);
		try {
			sign = SecurityUtil.sign(sign, prvKey, true);
		} catch (Exception e) {
			e.printStackTrace();
		}

		hdata.put("sign", sign);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("REQ_BODY", data);
		map.put("REQ_HEAD", hdata);
		String string = JUtil.toJsonString(map);
		System.out.println(string);
		String rdata = http.post(string);
		System.out.println(":" + rdata);
		Map<String, Object> rmap = JUtil.toMap(rdata);
		System.out.println(rmap);

		Map<String, Object> _body = (Map<String, Object>) rmap.get("REP_BODY");
		Map<String, Object> _head = (Map<String, Object>) rmap.get("REP_HEAD");
		System.out.println(rmap);
		String vsign = HttpApi.getSign(_body, md5Key);
		System.out.println(vsign);

		String _sign = _head.get("sign").toString();
		try {
			System.out.println("验证签名:"
					+ SecurityUtil.verify(vsign, _sign, pubKey, true));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	/**
	 * 商户余额代付查询
	 */
	@RequestMapping(value="/get/payforQuery")
	@ResponseBody
	public void payforQuery(@PathVariable("ordId") String ordId) {
		HttpApi http = new HttpApi(url, HttpApi.POST);
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> hdata = new HashMap<String, Object>();
		data.put("agtId", agtId);
		data.put("merId", merId);
		data.put("tranCode", "2102");
		data.put("nonceStr", TdExpBasicFunctions.RANDOM(16, "0"));
		data.put("orderId", ordId);
		data.put("tranDate", TdExpBasicFunctions.GETDATE());

		String sign = HttpApi.getSign(data, md5Key);
		System.out.println(sign);
		try {
			sign = SecurityUtil.sign(sign, prvKey, true);
		} catch (Exception e) {
			e.printStackTrace();
		}

		hdata.put("sign", sign);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("REQ_BODY", data);
		map.put("REQ_HEAD", hdata);
		String string = JUtil.toJsonString(map);
		System.out.println(string);
		String rdata = http.post(string);
		System.out.println(":" + rdata);
		Map<String, Object> rmap = JUtil.toMap(rdata);
		System.out.println(rmap);

		Map<String, Object> _body = (Map<String, Object>) rmap.get("REP_BODY");
		Map<String, Object> _head = (Map<String, Object>) rmap.get("REP_HEAD");
		System.out.println(rmap);
		String vsign = HttpApi.getSign(_body, md5Key);
		System.out.println(vsign);

		String _sign = _head.get("sign").toString();
		try {
			System.out.println("验证签名:"
					+ SecurityUtil.verify(vsign, _sign, pubKey, true));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	/**
	 * 商户余额查询
	 */
	@RequestMapping(value="/get/memberQuery")
	@ResponseBody
	public Double memberQuery() {
		HttpApi http = new HttpApi(url, HttpApi.POST);
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> hdata = new HashMap<String, Object>();
		data.put("agtId", agtId);
		data.put("merId", merId);
		data.put("tranCode", "2103");
		data.put("nonceStr", TdExpBasicFunctions.RANDOM(16, "0"));

		String sign = HttpApi.getSign(data, md5Key);
		System.out.println(sign);
		try {
			sign = SecurityUtil.sign(sign, prvKey, true);
		} catch (Exception e) {
			e.printStackTrace();
		}

		hdata.put("sign", sign);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("REQ_BODY", data);
		map.put("REQ_HEAD", hdata);
		String string = JUtil.toJsonString(map);
		System.out.println(string);
		String rdata = http.post(string);
		System.out.println(":" + rdata);
		Map<String, Object> rmap = JUtil.toMap(rdata);
		System.out.println(rmap);

		Map<String, Object> _body = (Map<String, Object>) rmap.get("REP_BODY");
		Map<String, Object> _head = (Map<String, Object>) rmap.get("REP_HEAD");
		System.out.println(rmap);
		String vsign = HttpApi.getSign(_body, md5Key);
		System.out.println(vsign);

		String _sign = _head.get("sign").toString();
		try {
			System.out.println("验证签名:"
					+ SecurityUtil.verify(vsign, _sign, pubKey, true));
			return 1.0;
		} catch (Exception e) {
			e.printStackTrace();
			return 2.0;
		}

	}

}

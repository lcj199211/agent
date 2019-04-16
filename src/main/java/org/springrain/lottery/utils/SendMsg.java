package org.springrain.lottery.utils;

import java.text.DecimalFormat;

/**
 * @Title: zmw
 * @date 2017-8-10
 */
public class SendMsg {

	// 用户名
	private final static String Uid = "hqjc88";

	// 接口安全秘钥
	private final static String Key = "9a1e22b0714fced2a96d";

	// 短信内容
	private static String smsText = "验证码：";

	public static String send(String smsMob) {

		HttpClientUtil client = HttpClientUtil.getInstance();
		String smsCode = new DecimalFormat("0000").format(Math.random() * 10000);

		// UTF发送
		int result = client.sendMsgUtf8(Uid, Key, smsText + smsCode, smsMob);
		if (result > 0) {
			System.out.println("UTF8成功发送条数==" + result);
			return smsCode;
		} else {
			System.out.println(client.getErrorMsg(result));
			return null;
		}
	}

	public static String send(String smsMob, String company) {

		if (company == null || "".equals(company)) {
			return null;
		}
		String[] uidAndKey = company.split(":");
		HttpClientUtil client = HttpClientUtil.getInstance();
		String smsCode = new DecimalFormat("0000").format(Math.random() * 10000);

		// UTF发送
		int result = client.sendMsgUtf8(uidAndKey[0], uidAndKey[1], smsText + smsCode, smsMob);
		if (result > 0) {
			System.out.println("UTF8成功发送条数==" + result);
			return smsCode;
		} else {
			System.out.println(client.getErrorMsg(result));
			return null;
		}
	}

	/**
	 * 发送短信
	 * 
	 * @param smsMob 
	 *            手机号码
	 * @param smsString
	 *            短信内容
	 * @param company
	 *            用户名 秘钥
	 * @return
	 */
	public static Integer send(String smsMob, String smsString, String company) {

		if (company == null || "".equals(company)) {
			return null;
		}
		String[] uidAndKey = company.split(":");
		HttpClientUtil client = HttpClientUtil.getInstance();

		// UTF发送
		int result = client.sendMsgUtf8(uidAndKey[0], uidAndKey[1], smsString, smsMob);
		if (result > 0) {
			System.out.println("UTF8成功发送条数==" + result);
			return result;
		} else {
			System.out.println(client.getErrorMsg(result));
			return 0;
		}
	}
}

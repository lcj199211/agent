package org.springrain.pay.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.pay.entity.AlipayUrl;
import org.springrain.pay.entity.BetAgentProxies;
import org.springrain.pay.service.IBetAgentProxiesService;
import org.springrain.pay.utils.HttpApi;
import org.springrain.pay.utils.JUtil;
import org.springrain.pay.utils.SecurityUtil;
import org.springrain.pay.utils.TdExpBasicFunctions;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.system.service.BaseSpringrainServiceImpl;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2018-01-26 10:15:07
 * @see org.springrain.lottery.service.impl.BetAgentDrawcash
 */
@Service("betAgentProxiesService")
public class BetAgentProxiesServiceImpl extends BaseSpringrainServiceImpl implements IBetAgentProxiesService {

	//乐彩
//	String prvKey="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCKzc+fsYMuP3Y/lAHGK/P4YyTjUrm8YSut40YCC6VIbLa1LM2WWqwtfqZIbHP3IDMofzuUVKMhy6pOS+LpKI8FCBc33sk352V4iz94uMptFU5RTyk3QVQxZctRZKej10vLmktVc1eDkGD/rxOkgH8OIcURgzy3hqeT6g+zK9zisRMc44rNoIrvqg/QYmmltrtNr8HBZeHFRzBj737QXc+WCKcUiDEQljPmWzxaGGQgAA/sUG64IN3pz9/HOgKge85wChdWlxmNMsNClAl7NwXeFOQM+HUAvmpxgOOUc3a3TFt3PJwcQJkuYOm+pX1HSgj9nxAtl5CxMbjY9p6tS9obAgMBAAECggEAPdvhFuNOehLwSP9WFxJJhT3yF5kri2emxrUN4yV7gJ5qgq235bT9HL1fr9EdkVn5Uqf5Tm610P7qJECvnIdlzbFAX7ba6B4o26L7I3r/Q8QGBjiIPsbPlcAu71QzXdI6Rj+6KRfhPZHfmLNWxq45cXvdTqO1MHlDP6lFW+1FYIUvX+B8FXoE2OkssvvtP/R84whlwFEgyN4L/928AAops0/wKfgjYq++4zy0ubyEdWDpGqK4OqpbBM4E8of98pZuD03xzFYFM2JQCmaFWKnWFHMO0Fxq8EbpiLI28wfNyzO/DulEvmsch/IJvM++2ESxhuLMKBQo+Zh6ZdX4AHRg6QKBgQDLuQDd21NaAyZ3zD/oMJhIDgsY+Wl8Lrxl73gnpPQpgDdHlDui1N61vLZw4ZK4jtFjLgxj6QFANXDM1Cu1cEzB3XYa1T36dU7FjUR5GcPOCilSXlHTSF6bq3eWofitWblaz7D9/n2TFXM7dDg/QmHd9nSdH4a+yQLkuScNHuDITwKBgQCubCbwGrvSBE+V6UF4cHeOic9xefX0IjbZcaMs8RI/Chopkh/cEXWeX9CsPvOL+piUDCCEzljYez2aQk9KOrUPhRsvmBhO5OrgawW2TigapYRXUi/Ivqj60j1MPC2YNhMFrgmS884PNmZ084asL8WcAQlGAPoMRn/VwrSeGGlSdQKBgBZI8YhTUqXZDF80b5U1o6LWLm6X8esMrb002d70U2UqymFBHNjuEkKtFwP4GPWpYcxIoEBZn7JnZiRx3Som066tTBsZUoNQS8KedmwM3nducyC4gpjrJLZApn9Kzoof83+0knbmXNQ3F3jceL6qw0vlhtIdPb8m+/MLMfNkelBPAoGAPqnaX16rZato0Enp3JRDT7FRFsqjQ19bZ0l9PzMow/8kIRU/Xvfi0tBHjupa40pXsLCeqjfcEYJNQnNaOyfWTsoaV0IEUavKem/YsINpwxD998UuJ6ff26TWouHn24xpzxuUkvgGas63hoLLOmc7Tm3M4BqPuwNFAA4gh7/rbtUCgYEAnp/3vZRmwp2pD6bTxzgbAuab4L/Z4eE94YAV9tFpilxkS5I00aWm+gkG9EtK3dvmxw7h0k1yH2xvdZqzfCKR7HbG7aOVd/aP0N/DSrZEJjbt/ViYACApI0f7tIVz8azmCgquUJfj5PV50Kvh9pxW62Asx5Fy5RBhvGXQiiNgst0=";
//	String desKey="12456789012345678900234567890ab";
//	String pubKey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyI2kgR46zIfqnIIHnwyxwRpkRuCpQcfV10oq6knybXqyor2Opjo0IhM+5Kkdel/v58ftP3cC/NKt3/dTrFVB+KEdLyStrV2bkonVDVQNMSzR1cHB0p40b6/Tfw+SQWErM1kkLlI8NCzYHXoxO77iNT2m+UeMeUrHO7NCyx3d+p26CjklwDMG1drxFgpFKcCH7mqXHCKsp5irivWpgiyczaMVKBcVVXsvmyYyfblDl4Q1lOqkMUzNQAtBPSguoix8wHx+5mQke8h5dDo6S94GeG+YXqGXxP/U6xVGWpLL0Hzk9D0jnrDaKsexKaHwHVRFElvmQquLezd1/vPFcPJcvQIDAQAB";
//	String md5Key="JReUDzi3PiyLhjYHPAIHQ6vrxxIQKYRF";
//	String agtId="18060101";
//	String merId="1806010184";
	
	//微彩               
//	String prvKey="MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCewc/jqbH3ZC1F60Y/ltHWGRSvbjKM5JK8c2fSbQI+RfLNr7alaFeD/YdgcIKKNLr76OEO4ZvFSN0/qrnUkhsZ/kC2ZKBwDyLfLTsJrcT66bliSf+FDRlnP6EJSoiSDmzgA16X3O1VWi20EYppLzshQBvlICubBmkCxUiF8Nu5j6W5TuIvJ7tc+PACA+xY8t8AdbO1EXmdsFq73qZKcWNMAhPvmHnxcsbUuPbOgYyJ8GBFFStNP+DY+2kPHY/fxLyJFP/rpKbfu/RLRaNekc0QO0jcd3+MEpObG1guhyaOdl5pC324DzSDFxBuKAxZhDx4aVMgabF+4LzMBFfKXQlZAgMBAAECggEBAJwAt9hvmIIR3qsmVxGFkw1f+AOrteDde5PTk+IjXGD1O3MDh+dicR0ytST/akeFKXkBjqag7AEhaMh3a8SHDjqsrcqNd1qmhQ3p1GneaAJoSdNcA8sq5BuhWw1WQH3FzxcSOJHidxW4V9Dqq6hQ0ToLVV4CDtd3pRkj4/A6jq/3+Kxd3nH3oqwoQBT3CK4+odH8oTPcd1gzAOB1URj9azveRoU+ric2E8zll91P/FNxlL/G5In8shJb896taJS2dODG2aFhaOZIxX+5Hu23ZApNTlrA4ZHVTfNCUJOviNyjVQVUpWoVuFCoO1m5hblWTFPflvRMn2KcDv199XyxMUECgYEA5fgmpLDBsEKVZKyYpDfBOeNfN67aJ27szCwaX05pOSkfltr+K1p0OGGuWEqgQOgwN8f+sbCa8hR/o1poA7MiwPYQFkCTs31MNtCPM927KCoK6Wahak+SRc19eZUMShXipU8ne+XVNyMwEjfTW/C/jUlmbJP/HFSXFIyeNOeSNp8CgYEAsLoiiV9F15HrCFLYwED3Ujy0VUnVtdRHr8/ad+bLaE116N9pEK5iHTmie6ZB/xq7GdrKpMtjHqn4FyNvnpxSkZVz/CTSMqNufCA2N+M01Rpmt/aB0wPuZpL5YNVknTOlpwDriHkC9sxXph1xYEI9soOebeEOmU1qFo9cAvqNlQcCgYEAyHHSJOxX9q0ij/yMk15xkmZnoR5L0eEyjwfF+NpchtEcUp4QunL+KAKkNxrcsZZwKL6udeysMWLCmfx7+vWRzkTrYqno06Lb4k9I8nlK30g9+JBXE1XF9geVXXsXmh+XTGVA0hJTFNsb1FVKabHfFGQs3WjmDKiCgwvJ/ez2GZsCgYBN8fqTjiK2vlPkOu4AdWbfrYh36XXJmZP4lfssUO271hZAHHExe0gozae9U4neIwMMlzQzVMUl3B9gzS9IzHeaYElklzTiQrTTKD3hjmNZ7igJ0T5XPG+kbARzUgrrTsEsFDrN2VmWUnACAxBjPTQLDQQOQGn48WzuAFK7uZhCdwKBgQCwprDtmS6Rqbzn8wd3Cgm96bCc/UjwbOCcFwTBOGZ7CIgE9trM5AF38cWDpE41OGN4/4jXooV/EzoQQS7SZXg49L3ZChWSFmAodtst3EQOSbCKBeiJU88FysB6oXDZDhCBmM5jf1g7eYQHDFEtl3BsXQMdURjRGIm0JrP4gawZSQ==";
//	String desKey="2456789012345678900234567890ab";
//	String pubKey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA26i4drTA2J/I3U5uqSIht2kQKGXXEF0MSbB9lgQBl+ndlKrJ2f386Z8w1Y+C1iPnIa9FGoKLyiRJj12XgoxhC92nglODMFmeQdvt3Qye/YBhxlmnnn5DmQp72iCPp8zEtjteLdzU2BNJygCdnRxMMTDWLYXmaBLRLY5pOvUn1FXp+cuHWS5pVhUhNDVatleMpwtGDJJC07/iyBiS1jrLkCGja1Qgmiwl3aXHUaHbb/eSx9BhsE7HiyPN+ENqoHHCGx+GtBt8eIZCpDrKxmnpXI5nvo2/6yhwjFr8K/jaUzWOF9yuK7BAc++Jt/l9vLjHsiOik1GOxic8tEFR8GZ0xQIDAQAB";
//	String md5Key="6l2j32W1sPOR3mQNXbi65vlPcp2fXbLu";
//	String agtId="18070113";
//	String merId="1807011385";
	
//	String prvKey=PropertyUtils.getProperty("daohe.alipay.prvKey");
//	String desKey=PropertyUtils.getProperty("daohe.alipay.desKey");
//	String pubKey=PropertyUtils.getProperty("daohe.alipay.pubKey");
//	String md5Key=PropertyUtils.getProperty("daohe.alipay.md5Key");
//	String agtId=PropertyUtils.getProperty("daohe.alipay.agtId");
//	String merId=PropertyUtils.getProperty("daohe.alipay.merId");
//	String url=PropertyUtils.getProperty("daohe.alipay.url");
   
//	String url="http://47.75.108.4:8343/webwt/pay/gateway.do";
	
	@Resource
	private IBetAgentService betAgentService;
	
    @Override
	public String  save(Object entity ) throws Exception{
    	BetAgentProxies betAgentProxies=(BetAgentProxies) entity;
	       return super.save(betAgentProxies).toString();
	}

    @Override
	public String  saveorupdate(Object entity ) throws Exception{
    	BetAgentProxies betAgentProxies=(BetAgentProxies) entity;
		 return super.saveorupdate(betAgentProxies).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
		BetAgentProxies betAgentProxies=(BetAgentProxies) entity;
	     return super.update(betAgentProxies);
    }
	
    @Override
	public BetAgentProxies findIBetAgentProxiesById(Object id) throws Exception{
	 return super.findById(id,BetAgentProxies.class);
	}
	
/**
 * 列表查询,每个service都会重载,要把sql语句封装到service中,Finder只是最后的方案
 * @param finder
 * @param page
 * @param clazz
 * @param o
 * @return
 * @throws Exception
 */
        @Override
    public <T> List<T> findListDataByFinder(Finder finder, Page page, Class<T> clazz,
			Object o) throws Exception{
			 return super.findListDataByFinder(finder,page,clazz,o);
			}
	/**
	 * 根据查询列表的宏,导出Excel
	 * @param finder 为空则只查询 clazz表
	 * @param ftlurl 类表的模版宏
	 * @param page 分页对象
	 * @param clazz 要查询的对象
	 * @param o  querybean
	 * @return
	 * @throws Exception
	 */
		@Override
	public <T> File findDataExportExcel(Finder finder,String ftlurl, Page page,
			Class<T> clazz, Object o)
			throws Exception {
			 return super.findDataExportExcel(finder,ftlurl,page,clazz,o);
		}

	@Override
	public Map<String, Object> payfor(BetAgentProxies betAgentProxies) {
		// TODO Auto-generated method stub
		try{
			String agentId = SessionUser.getAgentId();
			String company  = betAgentService.queryForObject(new Finder("select company from bet_agent where agentid = :agentid").setParam("agentid", agentId),String.class);
			AlipayUrl alipayurl =queryForObject(new Finder("select * from alipay_url where company = :company").setParam("company", company),AlipayUrl.class);
			String prvKey=alipayurl.getPrvKey();
			String desKey=alipayurl.getDesKey();
			String pubKey=alipayurl.getPubKey();
		    String md5Key=alipayurl.getMd5Key();
		    String agtId=alipayurl.getAgtId();
			String merId=alipayurl.getMerId();
			String url=alipayurl.getUrl();

			HttpApi http = new HttpApi(url, HttpApi.POST);
			Map<String, Object> data = new HashMap<String, Object>();
			Map<String, Object> hdata = new HashMap<String, Object>();
			double txnAmts = betAgentProxies.getMoney();
			int txnAmt = (int)txnAmts*100;
			data.put("agtId", agtId);
			data.put("merId", merId);
			data.put("tranCode", "2101");
			data.put("nonceStr", TdExpBasicFunctions.RANDOM(16, "0"));
			data.put("tranDate", TdExpBasicFunctions.GETDATE());
//			String ordId = TdExpBasicFunctions.RANDOM(19, "2");
			data.put("orderId", betAgentProxies.getOrderid());
			data.put("txnAmt", String.valueOf(txnAmt));
			data.put("accountNo", betAgentProxies.getAccountno());// 卡号
			data.put("certNum", betAgentProxies.getCertnum());// 身份证号
			data.put("bankCode", betAgentProxies.getBankcode());// 银行编码
			data.put("bankName", TdExpBasicFunctions.STR2HEX(betAgentProxies.getBankname()));// 银行名称0
			data.put("accountName", TdExpBasicFunctions.STR2HEX(betAgentProxies.getAccountname()));// 持卡人
			data.put("cnaps", "105793000108");// 联行号
//			data.put("bankBranch", TdExpBasicFunctions.STR2HEX(betAgentProxies.getBankbranch()));// 支行
			data.put("accountType", "1");

			data.put("nonceStr", TdExpBasicFunctions.RANDOM(16, "0"));
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
				return _body;
			} catch (Exception e) {
				e.printStackTrace();
				return _body;
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Map<String, Object> payforQuery(String ordId) {
		// TODO Auto-generated method stub
		try{
		String agentId = SessionUser.getAgentId();
		String company  = betAgentService.queryForObject(new Finder("select company from bet_agent where agentid = :agentid").setParam("agentid", agentId),String.class);
		AlipayUrl alipayurl =queryForObject(new Finder("select * from alipay_url where company = :company").setParam("company", company),AlipayUrl.class);
		String prvKey=alipayurl.getPrvKey();
		String desKey=alipayurl.getDesKey();
		String pubKey=alipayurl.getPubKey();
	    String md5Key=alipayurl.getMd5Key();
	    String agtId=alipayurl.getAgtId();
		String merId=alipayurl.getMerId();
		String url=alipayurl.getUrl();
		
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
			return _body;
		} catch (Exception e) {
			e.printStackTrace();
			return _body;
		}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Double memberQuery() {
		// TODO Auto-generated method stub
		try{
			String agentId = SessionUser.getAgentId();
			String company  = betAgentService.queryForObject(new Finder("select company from bet_agent where agentid = :agentid").setParam("agentid", agentId),String.class);
			AlipayUrl alipayurl =queryForObject(new Finder("select * from alipay_url where company = :company").setParam("company", company),AlipayUrl.class);
			String prvKey=alipayurl.getPrvKey();
			String desKey=alipayurl.getDesKey();
			String pubKey=alipayurl.getPubKey();
		    String md5Key=alipayurl.getMd5Key();
		    String agtId=alipayurl.getAgtId();
			String merId=alipayurl.getMerId();
			String url=alipayurl.getUrl();
			
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
				Double acBal = Double.parseDouble(_body.get("acBal").toString());
				return acBal/100;
			} catch (Exception e) {
				e.printStackTrace();
				return 0.0;
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0.0;
		}
		
	}

}

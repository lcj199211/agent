package org.springrain.weixin.entity;

import javax.persistence.Id;
import javax.persistence.Transient;

import org.springrain.frame.entity.BaseEntity;
import org.springrain.weixin.sdk.common.api.IWxCpConfig;

public class WxCpConfig   extends BaseEntity implements IWxCpConfig {
	private static final long serialVersionUID = 1L;
	
	   private volatile String id;
	
	  private volatile String appId;
	  private volatile String secret;
	  private volatile String partnerId;
	  private volatile String partnerKey;
	  private volatile String token;
	  private volatile String aesKey;
	 

	  private volatile String oauth2redirectUri;

	  private volatile String httpProxyHost;
	  private volatile Integer httpProxyPort;
	  private volatile String httpProxyUsername;
	  private volatile String httpProxyPassword;

	  
	  
	  private volatile String certificateFile ;
	  private volatile String tmpDirFile;
	  
	  private volatile String corpId;
	  private volatile String corpSecret;
	  private volatile Integer agentId;
	  
	  
	  
	  private volatile String accessToken;
	  private volatile Long accessTokenExpiresTime=0L;
	  
	  private volatile String jsApiTicket;
	  private volatile Long jsApiTicketExpiresTime=0L;
	  
	  private volatile String cardApiTicket;
	  private volatile Long cardApiTicketExpiresTime=0L;
	  
	  
	  @Override
	@Id
	public String getId() {
		return id;
	}
	@Override
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String getAppId() {
		return appId;
	}
	@Override
	public void setAppId(String appId) {
		this.appId = appId;
	}
	@Override
	public String getSecret() {
		return secret;
	}
	@Override
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public String getPartnerKey() {
		return partnerKey;
	}
	public void setPartnerKey(String partnerKey) {
		this.partnerKey = partnerKey;
	}
	@Override
	public String getToken() {
		return token;
	}
	@Override
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String getAccessToken() {
		return accessToken;
	}
	@Override
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	@Override
	public String getAesKey() {
		return aesKey;
	}
	@Override
	public void setAesKey(String aesKey) {
		this.aesKey = aesKey;
	}
	
	
	
	
	@Override
	public String getOauth2redirectUri() {
		return oauth2redirectUri;
	}
	@Override
	public void setOauth2redirectUri(String oauth2redirectUri) {
		this.oauth2redirectUri = oauth2redirectUri;
	}
	@Override
	public String getHttpProxyHost() {
		return httpProxyHost;
	}
	@Override
	public void setHttpProxyHost(String httpProxyHost) {
		this.httpProxyHost = httpProxyHost;
	}
	@Override
	public Integer getHttpProxyPort() {
		return httpProxyPort;
	}
	@Override
	public void setHttpProxyPort(Integer httpProxyPort) {
		this.httpProxyPort = httpProxyPort;
	}
	@Override
	public String getHttpProxyUsername() {
		return httpProxyUsername;
	}
	@Override
	public void setHttpProxyUsername(String httpProxyUsername) {
		this.httpProxyUsername = httpProxyUsername;
	}
	@Override
	public String getHttpProxyPassword() {
		return httpProxyPassword;
	}
	@Override
	public void setHttpProxyPassword(String httpProxyPassword) {
		this.httpProxyPassword = httpProxyPassword;
	}
	@Override
	public String getJsApiTicket() {
		return jsApiTicket;
	}
	@Override
	public void setJsApiTicket(String jsApiTicket) {
		this.jsApiTicket = jsApiTicket;
	}
	
	@Override
	public String getCardApiTicket() {
		return cardApiTicket;
	}
	@Override
	public void setCardApiTicket(String cardApiTicket) {
		this.cardApiTicket = cardApiTicket;
	}
	
	
	@Override
	public String getCertificateFile() {
		return certificateFile;
	}
	@Override
	public void setCertificateFile(String certificateFile) {
		this.certificateFile = certificateFile;
	}
	@Override
	public String getTmpDirFile() {
		return tmpDirFile;
	}
	@Override
	public void setTmpDirFile(String tmpDirFile) {
		this.tmpDirFile = tmpDirFile;
	}
	
	
	@Transient
	public Long getAccessTokenExpiresTime() {
		return accessTokenExpiresTime;
	}
	@Override
	public void setAccessTokenExpiresTime(Long accessTokenExpiresTime) {
		this.accessTokenExpiresTime =  System.currentTimeMillis() + (accessTokenExpiresTime - 600) * 1000L;//预留10分钟
	}
	
	
	@Transient
	public Long getCardApiTicketExpiresTime() {
		return cardApiTicketExpiresTime;
	}
	@Override
	public void setCardApiTicketExpiresTime(Long cardApiTicketExpiresTime) {
		//预留10分钟
		this.cardApiTicketExpiresTime = System.currentTimeMillis() + (cardApiTicketExpiresTime - 600) * 1000L;//预留10分钟
	}

	@Transient
	public Long getJsApiTicketExpiresTime() {
		return jsApiTicketExpiresTime;
	}
	@Override
	public void setJsApiTicketExpiresTime(Long jsapiTicketExpiresTime) {
		this.jsApiTicketExpiresTime =  System.currentTimeMillis() + (jsApiTicketExpiresTime - 600) * 1000L;//预留10分钟
	}
	
	
	
	@Override
	@Transient
	public boolean isAccessTokenExpired() {
		 return System.currentTimeMillis() > this.accessTokenExpiresTime;
	}
	@Override
	@Transient
	public boolean isJsApiTicketExpired() {
	    return System.currentTimeMillis() > this.jsApiTicketExpiresTime;
	  }
	@Override
	@Transient
	public boolean isCardApiTicketExpired() {
	    return System.currentTimeMillis() > this.cardApiTicketExpiresTime;
	  }
	@Override
	@Transient
	public boolean autoRefreshToken() {
	    return true;
	  }
	@Override
	public String getCorpId() {
		return corpId;
	}
	@Override
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	@Override
	public String getCorpSecret() {
		return corpSecret;
	}
	@Override
	public void setCorpSecret(String corpSecret) {
		this.corpSecret = corpSecret;
	}
	@Override
	public Integer getAgentId() {
		return agentId;
	}
	@Override
	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

}

package com.diagnostico.api.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("diagnosis")
public class DiagnosisApiProperty {

	private String originPermitted;
	
	private final Security security = new Security();
	
	private final Mail mail = new Mail();
	
	public String getOriginPermitted() {
		return originPermitted;
	}

	public void setOriginPermitted(String originPermitted) {
		this.originPermitted = originPermitted;
	}

	public Security getSecurity() {
		return security;
	}
	
	public Mail getMail() {
		return mail;
	}
	
	public static class Security {

		private boolean enableHttps;
		
		private String client;
		
		private String clientSecret;
		
		private String signingKey;
	
		public boolean isEnableHttps() {
			return enableHttps;
		}
	
		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}

		public String getClient() {
			return client;
		}

		public void setClient(String client) {
			this.client = client;
		}

		public String getClientSecret() {
			return clientSecret;
		}

		public void setClientSecret(String clientSecret) {
			this.clientSecret = clientSecret;
		}

		public String getSigningKey() {
			return signingKey;
		}

		public void setSigningKey(String signingKey) {
			this.signingKey = signingKey;
		}

	}
	
	public static class Mail {
		
		private String host;
		
		private Integer port;
		
		private String username;
		
		private String password;
		
		public String getHost() {
			return host;
		}

		public void setHost(String host) {
			this.host = host;
		}

		public Integer getPort() {
			return port;
		}

		public void setPort(Integer port) {
			this.port = port;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

	}
	
}

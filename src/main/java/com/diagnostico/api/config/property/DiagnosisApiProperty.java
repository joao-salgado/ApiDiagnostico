package com.diagnostico.api.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("diagnosis")
public class DiagnosisApiProperty {

	private String originPermitted = "http://localhost:4200";
	
	private final Security security = new Security();
	
	public String getOriginPermitted() {
		return originPermitted;
	}

	public void setOriginPermitted(String originPermitted) {
		this.originPermitted = originPermitted;
	}

	public Security getSecurity() {
		return security;
	}
	
	public static class Security {

		private boolean enableHttps;
	
		public boolean isEnableHttps() {
			return enableHttps;
		}
	
		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}

	}
	
}

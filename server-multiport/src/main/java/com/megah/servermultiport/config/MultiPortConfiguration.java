package com.megah.servermultiport.config;

import java.util.HashSet;
import java.util.Set;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.TomcatServletWebServerFactoryCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
public class MultiPortConfiguration {

	@Value("${server.port:8080}")
	private String serverPort;

	@Value("${management.port:${server.port:8080}}")
	private String managementPort;

	@Value("${server.second-port:null}")
	private String secondPort;

	@Bean
	public WebServerFactoryCustomizer servletContainer() {

		Connector[] additionalConnectors = this.additionalConnector();

		ServerProperties serverProperties = new ServerProperties();
		return (WebServerFactoryCustomizer) new TomcatMultiConnectorServletWebServerFactoryCustomizer(serverProperties,
				additionalConnectors);
	}

//    @SuppressWarnings("deprecation")
	private Connector[] additionalConnector() {

//        if (StringUtils.isEmpty(this.secondPort) || "null".equals(secondPort)) {
		if (!StringUtils.hasLength(this.secondPort) || "null".equals(secondPort)) {
			return null;
		}

		Set<String> defaultPorts = new HashSet<>();
		defaultPorts.add(serverPort);
		defaultPorts.add(managementPort);

		if (!defaultPorts.contains(secondPort)) {
			Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
			connector.setScheme("http");
			connector.setPort(Integer.valueOf(secondPort));
			return new Connector[] { connector };
		} else {
			return new Connector[] {};
		}
	}

	private class TomcatMultiConnectorServletWebServerFactoryCustomizer
			extends TomcatServletWebServerFactoryCustomizer {

		private final Connector[] additionalConnectors;

		TomcatMultiConnectorServletWebServerFactoryCustomizer(ServerProperties serverProperties,
				Connector[] additionalConnectors) {
			super(serverProperties);
			this.additionalConnectors = additionalConnectors;
		}

		@Override
		public void customize(TomcatServletWebServerFactory factory) {
			super.customize(factory);

			if (additionalConnectors != null && additionalConnectors.length > 0) {
				factory.addAdditionalTomcatConnectors(additionalConnectors);
			}
		}
	}
}

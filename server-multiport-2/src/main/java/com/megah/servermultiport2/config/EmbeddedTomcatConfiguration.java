package com.megah.servermultiport2.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.TomcatServletWebServerFactoryCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.google.common.collect.Sets;

@Configuration
public class EmbeddedTomcatConfiguration {

	@Value("${server.port}")
	private String serverPort;

	@Value("${management.port:${server.port}}")
	private String managementPort;

	@Value("${server.additional-ports:null}")
	private String additionalPorts;

//	@Bean
////	public EmbeddedServletContainerFactory servletContainer() {
//	public WebServerFactory servletContainer() {
////		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
//		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
//		Connector[] additionalConnectors = this.additionalConnector();
//		if (additionalConnectors != null && additionalConnectors.length > 0) {
//			tomcat.addAdditionalTomcatConnectors(additionalConnectors);
//		}
//		
//		return tomcat;
//	}

	@Bean
	public WebServerFactoryCustomizer servletContainer() {

		Connector[] additionalConnectors = this.additionalConnector();

		ServerProperties serverProperties = new ServerProperties();
		return (WebServerFactoryCustomizer) new TomcatMultiConnectorServletWebServerFactoryCustomizer(serverProperties,
				additionalConnectors);
	}

	private Connector[] additionalConnector() {

		if (!StringUtils.hasLength(this.additionalPorts)) {
			return null;
		}

		Set<String> defaultPorts = Sets.newHashSet(this.serverPort, this.managementPort);
		String[] ports = this.additionalPorts.split(",");
		List<Connector> result = new ArrayList<>();

		for (String port : ports) {
			if (!defaultPorts.contains(port)) {
				Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
				connector.setScheme("http");
				connector.setPort(Integer.valueOf(port));
				result.add(connector);
			}
		}

		return result.toArray(new Connector[] {});
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

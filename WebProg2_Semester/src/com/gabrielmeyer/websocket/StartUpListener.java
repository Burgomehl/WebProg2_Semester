package com.gabrielmeyer.websocket;

import java.util.Arrays;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerEndpointConfig;

import com.gabrielmeyer.decoder.MessageDecoder;
import com.gabrielmeyer.encoder.MessageEncoder;
import com.gabrielmeyer.encoder.NodeEncoder;

public class StartUpListener implements ServletContextListener {

	private final static String uri = "/websocket";

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO auto-generated

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();
		ServerContainer container = (ServerContainer) servletContext.getAttribute(ServerContainer.class.getName());

		try {
			ServerEndpointConfig config = ServerEndpointConfig.Builder.create(WebSocket.class, uri)
					.decoders(Arrays.asList(MessageDecoder.class))
					.encoders(Arrays.asList(MessageEncoder.class, NodeEncoder.class)).build();

			container.addEndpoint(config);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

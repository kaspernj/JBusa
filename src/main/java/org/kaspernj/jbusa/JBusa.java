package org.kaspernj.jbusa;

import easy.sockets.EasySocketServer;

public class JBusa {
	private int port;
	private EasySocketServer server;
	private JBusaClientAcceptor clientAcceptor;
	private JBusaRoutes routes;

	public void setPort(int port) {
		this.port = port;
	}

	public void start() {
		server = new EasySocketServer(port);
		clientAcceptor = new JBusaClientAcceptor(this, server);
	}
	
	public void loop(){
		clientAcceptor.join();
	}

	public void setRoutes(JBusaRoutes routesIn) {
		routes = routesIn;
	}

	public void close() {
		server.close();
	}

	public JBusaRoutes getRoutes() {
		return routes;
	}
}

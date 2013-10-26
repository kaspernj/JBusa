package org.kaspernj.jbusa;

import java.util.ArrayList;

import easy.sockets.EasySocketClient;
import easy.sockets.EasySocketServer;
import easy.threads.EasyThread;

public class JBusaClientAcceptor implements Runnable {
	private EasySocketServer server;
	private EasyThread acceptorThread;
	private ArrayList<JBusaClient> clients = new ArrayList<JBusaClient>();
	private JBusa busa;

	public JBusaClientAcceptor(JBusa busaIn, EasySocketServer serverIn) {
		busa = busaIn;
		server = serverIn;
		acceptorThread = new EasyThread(this);
	}
	
	public void run(){
		EasySocketClient socketClient;
		
		while((socketClient = server.accept()) != null){
			JBusaClient client = new JBusaClient(busa, socketClient);
			clients.add(client);
		}
	}

	public void join() {
		acceptorThread.join();
	}
  
  public void stop(){
    server.close();
  }
}

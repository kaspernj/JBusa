package org.kaspernj.jbusa;

import org.kaspernj.jbusa.request.RequestResolver;

import easy.http.HttpRequest;
import easy.http.HttpRequestParser;
import easy.sockets.EasySocketClient;
import easy.threads.EasyThread;

public class JBusaClient implements Runnable{
  private EasySocketClient socket;
  private EasyThread thread;
  private HttpRequest request;
  private JBusa busa;
  private RequestResolver resolver;
  
  public JBusaClient(JBusa busaIn, EasySocketClient socketClient){
    busa = busaIn;
    socket = socketClient;
    thread = new EasyThread(this);
  }
  
  public void run(){
    while(true){
      parseRequest();
      handleRequest();
    }
  }
  
  private void parseRequest(){
    HttpRequestParser parser = new HttpRequestParser(socket);
    request = parser.getRequest();
    resolver = new RequestResolver(busa.getRoutes(), request);
  }
  
  private void handleRequest(){
    System.out.println("Path: " + request.path);
    
    throw new RuntimeException("handleRequest stub!");
  }
}

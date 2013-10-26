package org.kaspernj.jbusa.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import easy.http.HttpCookie;
import easy.http.HttpHeader;
import easy.sockets.EasySocketClient;

public class BasicResponse{
  static Map<Integer, String> STATUS_CODES = new HashMap<Integer, String>(){
    private static final long serialVersionUID = -4635134079297350327L;

    {
      put(100, "Continue");
      put(200, "OK");
      put(201, "Created");
      put(202, "Accepted");
      put(204, "No Content");
      put(205, "Reset Content");
      put(206, "Partial Content");
      put(301, "Moved Permanently");
      put(302, "Found");
      put(303, "See Other");
      put(304, "Not Modified");
      put(307, "Temporary Redirect");
      put(400, "Bad Request");
      put(401, "Unauthorized");
      put(403, "Forbidden");
      put(404, "Not Found");
      put(408, "Request Timeout");
      put(500, "Internal Server Error");
    }
  };

  private ArrayList<HttpHeader> headers = new ArrayList<HttpHeader>();
  private ArrayList<HttpCookie> cookies = new ArrayList<HttpCookie>();
  protected EasySocketClient socket;
  private int statusCode;
  protected ContentBuffer cBuffer;
  
  public void setSocket(EasySocketClient socketIn){
    socket = socketIn;
    statusCode = 200;
    cBuffer = new ContentBuffer();
  }
  
  public int getStatusCode(){
    return statusCode;
  }
  
  public String getStatusCodeStr(){
    return STATUS_CODES.get(statusCode);
  }

  public void addHeader(HttpHeader header){
    headers.add(header);
  }
  
  public ArrayList<HttpHeader> getHeaders(){
    return headers;
  }
  
  public void addCookie(HttpCookie cookie){
    cookies.add(cookie);
  }
  
  public ArrayList<HttpCookie> getCookies(){
    return cookies;
  }

  public void write(String str){
    socket.write(str);
  }

  public void puts(String str){
    socket.puts(str);
  }
}

package org.kaspernj.jbusa.response;

import easy.http.HttpCookie;
import easy.http.HttpHeader;

public class ChunkedHttp11Response extends BasicResponse{
  public void write(){
    socket.write("HTTP/1.1 " + getStatusCode() + " " + getStatusCodeStr() + "\r\n");

    addHeader(new HttpHeader("Transfer-Encoding", "Chunked"));
    addHeader(new HttpHeader("Connection", "Keep-Alive"));
    addHeader(new HttpHeader("Keep-Alive", "timeout=15, max=30"));
    
    for(HttpCookie cookie: getCookies()){
      socket.write(cookie.getHeaderLine() + "\r\n");
    }

    for(HttpHeader header: getHeaders()){
      socket.write(header.getHeaderLine() + "\r\n");
    }

    socket.write("\r\n");
    
    while(!cBuffer.isClosed()){
      String line = cBuffer.popLine();
      int length = line.length();
      String lengthStr = Integer.toString(length, 16);
      
      socket.write(lengthStr + "\r\n");
      socket.write(line + "\r\n");
    }
    
    socket.write("0\r\n");
  }
}

package org.kaspernj.jbusa.response;

public class ContentBuffer{
  private StringBuffer strBuffer;
  private boolean closed;

  public ContentBuffer(){
    strBuffer = new StringBuffer();
    closed = false;
  }
  
  public void close(){
    closed = true;
  }
  
  public boolean isClosed(){
    return closed;
  }

  public synchronized void write(String str){
    strBuffer.append(str);
  }
  
  public void puts(String str){
    write(str + "\r\n");
  }
  
  // Reads and deletes a line from the beginning of the buffer.
  public synchronized String popLine(){
    StringBuffer lineBuffer = new StringBuffer();
    
    int index = 0;
    while(true){
      char chr = strBuffer.charAt(index);
      lineBuffer.append(chr);
      
      if (chr == 10)
        break;
    }
    
    strBuffer.delete(0, index);
    return lineBuffer.toString();
  }
}

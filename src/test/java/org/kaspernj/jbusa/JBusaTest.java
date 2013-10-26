package org.kaspernj.jbusa;

import org.junit.Test;

public class JBusaTest{
  @Test
  public void test(){
    JBusa busa = new JBusa();
    busa.setPort(4000);
    busa.start();
  }
}

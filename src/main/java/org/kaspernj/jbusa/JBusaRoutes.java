package org.kaspernj.jbusa;

import org.kaspernj.jbusa.routes.Container;

public class JBusaRoutes extends Container{
	private Object base;

	public void setBase(Object object){
		base = object;
	}
  
  public Object getBase(){
    return base;
  }
}

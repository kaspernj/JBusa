package org.kaspernj.jbusa.request;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.kaspernj.jbusa.JBusaController;
import org.kaspernj.jbusa.JBusaRoutes;
import org.reflections.Reflections;

import easy.http.HttpRequest;

public class RequestResolver {
	private JBusaRoutes routes;
	private HttpRequest request;
	private String[] pathParts;
  private Map<String, Class<? extends JBusaController>> controllersByName = new HashMap<String, Class<? extends JBusaController>>();

	public RequestResolver(JBusaRoutes routesIn, HttpRequest requestIn){
		routes = routesIn;
		request = requestIn;
		pathParts = request.path.split("/");
		
		if (pathParts.length == 0) throw new RuntimeException("Invalid path: " + request.path);
    
    generateControllers();
	}
  
  private void generateControllers(){
    Reflections ref = new Reflections(routes.getBase().getClass().getPackage());
    Set<Class<? extends JBusaController>> classes = ref.getSubTypesOf(JBusaController.class);
    for(Class<? extends JBusaController> clazz: classes){
      controllersByName.put(clazz.getSimpleName(), clazz);
    }
    
    if (controllersByName.isEmpty())
      throw new RuntimeException("No controllers was found?");
  }
}

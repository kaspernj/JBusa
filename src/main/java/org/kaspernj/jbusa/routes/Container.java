package org.kaspernj.jbusa.routes;

import java.util.ArrayList;

public class Container implements Runnable {
	private ArrayList<Resource> resources = new ArrayList<Resource>();
	private ArrayList<Namespace> namespaces = new ArrayList<Namespace>();
	private ArrayList<Get> gets = new ArrayList<Get>();
	private ArrayList<Post> posts = new ArrayList<Post>();
	
	public void resource(Resource res){
		res.run();
		resources.add(res);
	}
	
	public void namespace(Namespace namespace){
		namespace.run();
		namespaces.add(namespace);
	}
	
	public void get(String name){
		Get get = new Get(name);
		gets.add(get);
	}
	
	public void post(String name){
		Post post = new Post(name);
		posts.add(post);
	}

	public void run() {
		// Nothing unless overwritten.
	}
}

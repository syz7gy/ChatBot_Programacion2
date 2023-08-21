package co.edu.unbosque.controller;

import co.edu.unbosque.model.Server;

public class Controller {
	
	private Server server;
	
	public Controller() {
		server = new Server(8080);
		server.start();
	}

}

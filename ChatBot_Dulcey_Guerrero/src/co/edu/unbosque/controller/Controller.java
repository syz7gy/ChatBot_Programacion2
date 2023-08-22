package co.edu.unbosque.controller;

import co.edu.unbosque.model.Client;
import co.edu.unbosque.model.Server;

public class Controller {
	
	private Server server;
	private Client client;
	
	public Controller() {
		
		server = new Server(8080);
		server.start();
		
		client = new Client("127.0.9.09", 8080);
		client.start();
	}
}

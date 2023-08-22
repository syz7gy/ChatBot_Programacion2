package co.edu.unbosque.model;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import co.edu.unbosque.model.persistance.FileHandler;

public class Server extends Thread {

	protected Socket s;
	protected Socket replyS;
	protected ServerSocket ss;
	protected ObjectOutputStream out;
	protected ObjectInputStream in;
	private int port;
	private String clientAddress;
	private ArrayList<String> answers;
	

	public Server(int port) {
		this.s = null;
		this.replyS = null;
		this.ss = null;
		this.out = null;
		this.in = null;
		this.port = port;
		this.clientAddress = clientAddress;

		answers = loadFromFile("Answers.txt");
		
	}
	
	private ArrayList<String> loadFromFile(String file){
		ArrayList<String> fromFile = new ArrayList<>();
		String content = FileHandler.abrirArchivoDeTexto(file);
		String[] lines = content.split("\n");
		for (String line : lines) {
			fromFile.add(line);
		}
		return fromFile;
	}

	@Override
	public void run() {

		try {
			this.ss = new ServerSocket(this.port);
			System.out.println("Server started");
			System.out.println("Waiting for a client to connect...");
			this.s = ss.accept();
			System.out.println("Client accepted. " + "\nWelcome to Paulita's and Juanchito's musical chatbot");
			
			System.out.println("\nWrite an artist down so the ChatBot can recommend you a song: ");
			this.in = new ObjectInputStream(new BufferedInputStream(s.getInputStream()));

			in.readObject();

			this.replyS = new Socket(this.s.getInetAddress(), this.port + 1);

			this.out = new ObjectOutputStream(replyS.getOutputStream());
			this.out.writeUTF("Goodbye");
			this.out.close();
			this.replyS.close();
			this.in.close();
			this.ss.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		if (this.isInterrupted()) {
			System.out.println("Closing connection");

			try {
				s.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}

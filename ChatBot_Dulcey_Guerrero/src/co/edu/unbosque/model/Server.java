package co.edu.unbosque.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

//import co.edu.unbosque.model.persistance.FileHandler;

public class Server extends Thread {

	protected Socket s;
	protected Socket replyS;
	protected ServerSocket ss;
	protected DataOutputStream out;
	protected DataInputStream in;
	private int port;
	private String clientAddress;
	private static Scanner sc;
	private static File file;
	private ArrayList<String> answers;
	

	public Server(int port) {
		this.port = port;
		this.clientAddress = clientAddress;
		file = new File("src/co/edu/unbosque/model/persistance/Answers.txt");
		answers = new ArrayList<>();
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("Error: File 'Answers.txt' was not found.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error: Check permissions for file 'Answers.txt'.");
			e.printStackTrace();
		}
		while(sc.hasNext()) {
			answers.add(sc.nextLine());
		}
		
	}


	@Override
	public void run() {

		try {
			
			out = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
			in = new DataInputStream(new BufferedInputStream(s.getInputStream()));
			
			ss = new ServerSocket(this.port);
			System.out.println("Server started");
			System.out.println("Waiting for a client to connect...");
			this.s = ss.accept();
			System.out.println("Client accepted. " + "\nWelcome to Paulita's and Juanchito's chatbot");
			
			System.out.println("\nWould you like to start? ");
			
			

			String rta = in.readUTF();
			
			out.writeUTF(menu().toString());
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}


	}
	
	public String menu() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n1) Quien es mejor?");
		sb.append("\n1) Quien es mejor?");
		sb.append("\n1) Quien es mejor?");
		return sb.toString();
	}
	
//	public String 
}

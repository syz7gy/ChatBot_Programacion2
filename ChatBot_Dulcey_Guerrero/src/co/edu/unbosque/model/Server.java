package co.edu.unbosque.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server extends Thread{
	
	protected Socket s;
	protected Socket replyS;
	protected ServerSocket ss;
	protected ObjectOutputStream out;
	protected ObjectInputStream in;
	private int port;
	private String clientAddress;
	private static File file;
	private static Scanner sc;
	private ArrayList<String> answers;
	
	public Server(int port) {
		this.s = null;
		this.replyS = null;
		this.ss = null;
		this.out = null;
		this.in = null;
		this.port = port;
		this.clientAddress = clientAddress;
		file = new File("src/co/edu/unbosque/model/persistance/Answers.txt");
		answers = new ArrayList<>();
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("Error: The file 'Answers.txt' was not found.");
			e.printStackTrace();
		} catch(IOException e) {
			System.out.println(
					"Error: The file 'Answers.txt' couldn't be opened. Check permissions.");
			e.printStackTrace();
		}
		while(sc.hasNext()) {
			answers.add(sc.nextLine());
		}
		
	}
	
	@Override
	public void run() {
		
		System.out.println(answers.toString());
		
	}
	
	
	

}

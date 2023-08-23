package co.edu.unbosque.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread {

	protected Socket s;
	private Scanner sc;
	protected ObjectOutputStream out;
	protected ObjectInputStream in;
	private String address;
	private int port;

	public Client(String address, int port) {
		this.sc = new Scanner(System.in);
		this.address = address;
		this.port = port;
	}

	@Override
	public void run() {

		String line;

		try {
			s = new Socket(this.address, this.port);
			System.out.println("Connected");

			out = new ObjectOutputStream(new BufferedOutputStream(s.getOutputStream()));
			in = new ObjectInputStream(new BufferedInputStream(s.getInputStream()));

			line = sc.nextLine();

			out.writeUTF(line);
			
			System.out.println(in.readUTF().toString());

			

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
				out.close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
package co.edu.unbosque.model;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread {

	protected Socket s;
	protected ServerSocket ss;
	private Scanner sc;
	protected ObjectOutputStream out;
	protected ObjectInputStream in;
	private String address;
	private int port;

	public Client(String address, int port) {
		this.s = null;
		this.ss = null;
		this.sc = new Scanner(System.in);
		this.out = null;
		this.address = address;
		this.port = port;
	}

	@Override
	public void run() {

		String line;

		try {
			this.s = new Socket(this.address, this.port);
			System.out.println("Connected");

			this.out = new ObjectOutputStream(s.getOutputStream());

			line = sc.nextLine();

			this.out.writeUTF(line);

			this.out.close();
			this.s.close();
			this.ss = new ServerSocket(this.port + 1);
			System.out.println("Recieved message: ");
			this.in = new ObjectInputStream(new BufferedInputStream(s.getInputStream()));
			System.out.println(in.readUTF());
			this.in.close();
			this.ss.close();

		} catch (IOException e) {
			System.out.println(e);
		}

		try {
			this.out.close();
			s.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
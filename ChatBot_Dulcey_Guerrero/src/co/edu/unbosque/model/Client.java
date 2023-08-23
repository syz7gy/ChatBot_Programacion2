package co.edu.unbosque.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread {

	protected Socket s;
	private Scanner sc;
	protected DataOutputStream out;
	protected DataInputStream in;
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

			out = new DataOutputStream(s.getOutputStream());
			in = new DataInputStream(new BufferedInputStream(s.getInputStream()));
			System.out.println(in.readUTF());
			line = sc.nextLine();
			while(true) {
				out.writeUTF(line);
			}
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
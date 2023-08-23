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
		while (sc.hasNext()) {
			answers.add(sc.nextLine());
		}

	}

	@Override
	public void run() {
		while(true) {
			try {
				ss = new ServerSocket(port);
				System.out.println("Server started");
				System.out.println("Waiting for a client to connect...");
				s = ss.accept();
				
				in = new DataInputStream(new BufferedInputStream(s.getInputStream()));
				System.out.println(
						"Client accepted. " + "\nWelcome to Paulita's and Juanchito's chatbot." + "\nSelenct an option:");
				System.out.println(menu().toString());
				
				String mensaje = in.readUTF();
				int valor = Integer.parseInt(mensaje);
				while(valor!=21) {
					replyS = new Socket(clientAddress, port+1);
					out = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
					out.writeUTF(botAnswers(mensaje) + "\n");
					System.out.println();
					System.out.println(menu().toString());
					mensaje = in.readUTF();
				}
				if(valor==21) {
					System.out.println("Closing server...");
					in.close();
					out.close();
					ss.close();
					s.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public StringBuilder menu() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n1) Who's your favorite artist?");
		sb.append("\n2) What's the best alt rock band?");
		sb.append("\n3) Who's the musician you have on repeat?");
		sb.append("\n4) Modern Warfare 2019 or Modern Warfare 2?");
		sb.append("\n5) Taylor Swift or Kanye West?");
		sb.append("\n6) Have you ever commited murder?");
		sb.append("\n7) Favourite sex position?");
		sb.append("\n8) Who made a revolution in musical industry?");
		sb.append("\n9) What's the best historical period?");
		sb.append("\n10) Yo Mr. White?");
		sb.append("\n11) ?");
		sb.append("\n12) Who was Stalin?");
		sb.append("\n13) Are you gay?");
		sb.append("\n14) Will someone love me?");
		sb.append("\n15) Whats the best Sci-Fi series?");
		sb.append("\n16) Who's the best teacher?");
		sb.append("\n17) Who's the worst teacher?");
		sb.append("\n18) Do you wanna build a snowman?");
		sb.append("\n19) What if the earth ends tomorrow?");
		sb.append("\n20) I might be wrong?");
		sb.append("\n21) Close chat.");
		return sb;
	}

	public String botAnswers(String rta) {
		int resp = Integer.parseInt(rta);
		String r = "";
		switch (resp) {
		case 1:
			r = answers.get(0);
		case 2:
			r = answers.get(1);
		case 3:
			r = answers.get(2);
		case 4:
			r = answers.get(3);
		case 5:
			r = answers.get(4);
		case 6:
			r = answers.get(5);
		case 7:
			r = answers.get(6);
		case 8:
			r = answers.get(7);
		case 9:
			r = answers.get(8);
		case 10:
			r = answers.get(9);
		case 11:
			r = answers.get(10);
		case 12:
			r = answers.get(11);
		case 13:
			r = answers.get(12);
		case 14:
			r = answers.get(13);
		case 15:
			r = answers.get(14);
		case 16:
			r = answers.get(15);
		case 17:
			r = answers.get(16);
		case 18:
			r = answers.get(17);
		case 19:
			r = answers.get(18);
		case 20:
			r = answers.get(19);
		}
		return r;
		
	}
}
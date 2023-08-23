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

		try {
			ss = new ServerSocket(port);
			System.out.println("Server started");
			System.out.println("Waiting for a client to connect...");
			s = ss.accept();
			out = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
			in = new DataInputStream(new BufferedInputStream(s.getInputStream()));
			System.out.println(
					"Client accepted. " + "\nWelcome to Paulita's and Juanchito's chatbot." + "\nSelenct an option:");
			System.out.println(menu().toString());

		} catch (Exception e) {
			e.printStackTrace();
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

	private String botAnswers(String rta) {
		int resp = Integer.parseInt(rta);
		switch (resp) {
		case 1:
			return null;
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
		case 11:
		case 12:
		case 13:
		case 14:
		case 15:
		case 16:
		case 17:
		case 18:
		case 19:
		case 20:

		}
		return rta;
	}
}
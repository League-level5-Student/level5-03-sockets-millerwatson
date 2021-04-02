package _02_Chat_Application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class ChatClient {
	private String ip;
	private int port;
	
	Socket connection;
	
	DataOutputStream dos;
	DataInputStream dis;
	
	public ChatClient(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	
	public void start() {
		try { 
			connection = new Socket(ip, port);
			
			dos = new DataOutputStream(connection.getOutputStream());
			dis = new DataInputStream(connection.getInputStream());
			
			dos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		while (connection.isConnected()) {
			try {
				JOptionPane.showMessageDialog(null, dis.readUTF());
				System.out.println(dis.readUTF());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void sendMessage() {
		try {
			if (dos != null) {
				String message = JOptionPane.showInputDialog("Message to send?");
				dos.writeUTF(message + "\n - Client");
				dos.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

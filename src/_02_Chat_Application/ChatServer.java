package _02_Chat_Application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class ChatServer {
	private int port;

	private ServerSocket server;
	private Socket connection;

	DataOutputStream dos;
	DataInputStream dis;

	public ChatServer(int port) {
		this.port = port;
	}

	public void start(){
		try {
			server = new ServerSocket(port, 100);

			connection = server.accept();

			dos = new DataOutputStream(connection.getOutputStream());
			dis = new DataInputStream(connection.getInputStream());

			dos.flush();

			while (connection.isConnected()) {
				try {
					JOptionPane.showMessageDialog(null, dis.readUTF());
					System.out.println(dis.readUTF());
				}catch(EOFException e) {
					JOptionPane.showMessageDialog(null, "Connection Lost");
					System.exit(0);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getIPAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "ERROR!!!!!";
		}
	}

	public int getPort() {
		return port;
	}

	public void sendMessage() {
		try {
			if (dos != null) {
				String message = JOptionPane.showInputDialog("Message to send?");
				dos.writeUTF(message + "\n - Server");
				dos.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


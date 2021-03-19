package _02_Chat_Application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ChatServer {
	private int port;
	
	private ServerSocket server;
	private Socket connection1;
	private Socket connection2;
	
	public ChatServer(int portNum) {
		this.port = portNum;
	}
	
	public void start() {
		
		try {
			server = new ServerSocket(port);
			
			connection1 = server.accept();
			//connection2 = server.accept();
			
			DataInputStream dis1 = new DataInputStream(connection1.getInputStream());
			DataOutputStream dos1 = new DataOutputStream(connection1.getOutputStream());
			
			//DataInputStream dis2 = new DataInputStream(connection2.getInputStream());
			//DataOutputStream dos2 = new DataOutputStream(connection2.getOutputStream());
			
			while (connection1.isConnected()) {
				try {
					JOptionPane.showMessageDialog(null, dis1.readUTF());
				}catch(EOFException e) {
					JOptionPane.showMessageDialog(null, "Connection Lost");
					System.exit(0);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		}
	}


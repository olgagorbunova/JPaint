package se.miun.olgo1700.dt062g.jpaint.server;

import java.io.*;
import java.net.*;

/**
* Server class, stores XML files with drawings.
* Server starts on a port provided as an argument. Several Clients can connect to the Server at the same time. Communication 
* between server and clients is managed by ClientHandler class.
*
* @author  Olga Gorbunova (olgo1700)
* @version 1.0
* @since   2019-1-15
*/
public class Server{
	private int port;
	
	/**
	 * Server constructor.
	 * @param p port number
	 */
	public Server(int p) {
		this.port = p;
		TheServer();
	}
	
	/**
	 * Listens to the port provided in constructor. Accepts connection to Clients.
	 */
	public void TheServer(){
		ServerSocket ss = null;
		
		try {
			ss = new ServerSocket(port);
			System.out.println("Server started on port " + port);
		} catch (IOException e) {
			System.err.println("Couldn't listen to port " + port);
			return;
		}
		
		while(true) {
			try {
				Socket s = ss.accept();
				new ClientHandler(s).start();

			} catch (IOException e) {
				System.err.println("Connection error:\n" + e);
			}
		}
	}
	
	public static void main(String[] args) {
		int port = 10000;
		if(args.length != 0) {
			try {
				port = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				System.err.println("Invalid argument, starting on default port");
			}
		}
		new Server(port);
	}
}


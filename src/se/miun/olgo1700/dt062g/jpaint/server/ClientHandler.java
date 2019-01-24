package se.miun.olgo1700.dt062g.jpaint.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.File;
import java.io.FilenameFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.Socket;
import java.util.LinkedList;

/**
* Manages communication between Server and Client, facilitates possibility for several Clients to connect to the Server simultaneously.
*
* @author  Olga Gorbunova (olgo1700)
* @version 1.0
* @since   2019-1-15
*/
public class ClientHandler extends Thread {
	private Socket s;
	String clientAddress;
	BufferedReader in;
	PrintWriter out;
	
	/**
	 * Constructor.
	 * @param s Socket
	 */
	public ClientHandler(Socket s) {
		this.s = s;
		clientAddress = s.getInetAddress().getHostAddress() + ":" + s.getPort();
	}
	
	/**
	 * Creates streams for communication with Client. Receives command from Client, calls respective method to process the request.
	 * When done, closes communication streams and connection socket.
	 */
	public void run() {
		System.out.println("New client connected from " + clientAddress);
		
		try {
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new PrintWriter(s.getOutputStream(), true);
		} catch (IOException e) {
			System.err.println("Error opening streams");
		}
		
		try {
			String command = in.readLine();
			System.out.println("Command '" + command + "' received from " + clientAddress);
			
			switch (command) {
			case "save":
				saveToFile();
				break;
			case "list":
				sendFilenames();
				break;
			case "load":
				sendFile();
				break;
			default:
				System.out.println("Unknown command received from " + clientAddress);
				break;
			}
		} catch (IOException e) {
			System.err.println("Error reading message from " + clientAddress);
		}
		
		try {
			in.close();
			out.close();
			s.close();
			System.out.println("Client from " + clientAddress + " has disconnected");
		} catch (IOException e) {
			System.err.println("Couldn't close connection with " + clientAddress);
		}
	}
	
	/**
	 * Sends names of all XML files stored in Server's 'xml' folder.
	 */
	private void sendFilenames() {
		try {
			//File folder = new File("C:\\Users\\olgag\\Documents\\Java\\JPaint\\xml");
			File folder = new File("xml/");
			File[] listOfFiles = folder.listFiles();

			int nrOfXml = 0;
			LinkedList<String> xmlFileList = new LinkedList<>();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile() && isXml(listOfFiles[i].getName())) {
					nrOfXml++;
					xmlFileList.add(listOfFiles[i].getName());
				 }
			}
			
			out.println(nrOfXml);
			for(String file: xmlFileList)
				out.println(file);
			
			System.out.println("File list sent to " + clientAddress);
		} catch (Exception e) {
			System.err.println("Error sendng file list to " + clientAddress);
		}
	}
	
	/**
	 * Verifies if a file name ends with '.xml'.
	 * @param fileName name of file
	 * @return true if file is an XML file, otherwise false
	 * 
	 */
	private boolean isXml(String fileName) {
		if(fileName.length() > 4) {
			String nameEnd = fileName.substring(fileName.length() - 4);
			
			if(nameEnd.equals(".xml")) {
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}
	
	/**
	 * Sends a file from Server to Client.
	 */
	private void sendFile() {
		try {
			String fileName = in.readLine();
			
			try {
				FileReader in = new FileReader("xml/" + fileName);
				BufferedReader fileIn = new BufferedReader(in);
				
				System.out.println("Sending '" + fileName + "' to " + clientAddress);
				
				String line = fileIn.readLine();
				while(line != null) {
					out.println(line);
					line = fileIn.readLine();
				}
				System.out.println("'" + fileName + "' sent to " + clientAddress);
				try {
					fileIn.close();
				} catch (IOException e) {
					System.err.println("Error closing file (" + clientAddress + " request)");
				}	
				
			} catch (FileNotFoundException e) {
				System.err.println("Invalid file name from " + clientAddress);
			}
			
		} catch (IOException e) {
			System.err.println("Error sending file to " + clientAddress);
		}
	}
	
	/**
	 * Saves a file received from Client in 'xml' folder on Server.
	 */
	private void saveToFile() {
		try {
			String fileName = in.readLine();
			if(fileName != null) {
				String saveToPath = "xml/" + fileName;
				
				System.out.println("Receiving '" + fileName + "' from " + clientAddress);
				
				BufferedWriter fileOut = new BufferedWriter(new FileWriter(saveToPath));
				
				String line = in.readLine();
				if(line != null) {
					
					while(line != null) {
						fileOut.write(line);
						line = in.readLine();
						if(line != null)
							fileOut.newLine();
					}
					System.out.println("File received from " + clientAddress);
				}
				fileOut.close();
			}			
			
		} catch (IOException e) {
			System.err.println("Error reading message from " + clientAddress);
		}
	}
}


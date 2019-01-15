package se.miun.olgo1700.dt062g.jpaint.client;

import java.net.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;

/**
* Client class. Connects to Server, sends requests to save files to Server, or retrieve files from Server.
* 
* @author  Olga Gorbunova (olgo1700)
* @version 1.0
* @since   2019-1-15
*/
public class Client{
	
	public static final String DEFAULT_ADDRESS = "localhost";
	public static final int DEFAULT_PORT = 10000;
	
	private String serverAddress;
	private int serverPort;
	
	Socket s = null;
	BufferedReader in = null;
	PrintWriter out = null;
	boolean connected = false;
	
	/**
	 * Default constructor. Connects to Server on default address and port.
	 */
	public Client() {
		serverPort = DEFAULT_PORT;
		serverAddress = DEFAULT_ADDRESS;
	}
	
	/**
	 * Constructor.
	 * @param address Server address
	 * @param port Server port
	 */
	public Client(String address, int port) {
		serverPort = port;
		serverAddress = address;
	}
	
	/**
	 * Establishes connection to Server if not connected: creates socket and communication streams.
	 * @return true if connection successful, false if unsuccessful
	 */
	private boolean connect() {
		if(!connected) {
			try {
				s = new Socket(serverAddress, serverPort);
				in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				out = new PrintWriter(s.getOutputStream(), true);
				connected = true;
				return true;
			} catch (UnknownHostException e) {
				System.err.println("Can't connect to host");
				return false;
			} catch (IOException e) {
				System.err.println("Can't establish connection");
				return false;
			}
		}
		else
			return false;
	}
	
	/**
	 * Disconnects from Server, if connected: closes socket and communication streams.
	 */
	private void disconnect() {
		if(connected) {
			try {
				in.close();
				out.close();
				s.close();
				in = null;
				out = null;
				s = null;
				connected = false;
			} catch (IOException e) {
				System.err.println("Error disconnecting");
			}
		}
	}
	
	/**
	 * Requests and receives list of names of all XML files on Server.
	 * @return String array of file names, or null if error occurs
	 */
	public String[] getFilenamesFromServer() {
		boolean connectionSuccesfull = connect();
		String[] filenames = null;
		if(connectionSuccesfull) {
			out.println("list");
			try {
				int listLength = Integer.parseInt(in.readLine());
				filenames = new String[listLength];
				
				String fileName = in.readLine();
				int nr = 0;
				while(fileName != null && nr < listLength) {
					filenames[nr] = fileName;
					nr++;
					fileName = in.readLine();
				}

			} catch (Exception e) {
				//System.err.println("Error receiving file list from server");
				e.printStackTrace();
				disconnect();
				return null;
			}
		}
		else {
			disconnect();
			return null;
		}	
		
		disconnect();
		return filenames;
	}
	
	/**
	 * Sends a request to save a file from Server to a local folder.
	 * @param fileName name of file on Server
	 * @return full path to file at Client (String), or null if error occurred
	 */
	public String getFileFromServer(String fileName) {
		boolean connectionSuccesfull = connect();
		if(connectionSuccesfull) {
			try {
				out.println("load");
				out.println(fileName);
				
				File myDir = new File("clientXml/");
				if(!myDir.exists())
					myDir.mkdir();
					
				String saveToPath = "clientXml/" + fileName;
				
				String line = in.readLine();
				
				if(line != null) {
					BufferedWriter fileOut = new BufferedWriter(new FileWriter(saveToPath));
					
					while(line != null) {
						fileOut.write(line);
						line = in.readLine();
						if(line != null)
							fileOut.newLine();
					}
					
					try {
						fileOut.close();
					} catch (IOException e) {
						System.err.println("Error closing file");
					}
					
					File myFile = new File("localXml/"+fileName);
					String filePath = myFile.getAbsolutePath();
					
					disconnect();
					return filePath;
				}
				
				else {
					disconnect();
					return "";
				}
				
			} catch (Exception e) {
				System.err.println("Error reading message from server");
				disconnect();
				return null;
			}
		}
		else {
			disconnect();
			return null;
		}
	}
	
	/**
	 * Sends a request to save a local file to Server, and file content.
	 * @param fileNameLocal name of file on local disk, including file extension
	 * @param fileNameSaved name that file has to be saved under on Server, including file extension
	 */
	public boolean saveAsFileToServer(String fileNameLocal, String fileNameSaved) {
		boolean connectionSuccesfull = connect();
		
		if(connectionSuccesfull) {
			out.println("save");
			
			try {
				String fileNameLocalFolder = "clientXml/" + fileNameLocal;
				FileReader in = new FileReader(fileNameLocalFolder);
				BufferedReader fileIn = new BufferedReader(in);
				
				out.println(fileNameSaved);
				
				try {
					String line = fileIn.readLine();
					while(line != null) {
						out.println(line);
						line = fileIn.readLine();
					}
				} catch (IOException e) {
					System.err.println("Error reading from file");
				}
				
				try {
					fileIn.close();
				} catch (IOException e) {
					System.err.println("Error closing file");
				}
				
			} catch (FileNotFoundException e) {
				System.err.println("Invalid file name");
				disconnect();
				return false;
			}
			disconnect();
			return true;
		}
		else {
			disconnect();
			return false;
		}
	}
	
	/**
	 * Calls method saveAsFileToServer() to save a local file to Server.
	 * @param fileName name of file on local disk, and name under which file will be saved on Server, including file extension
	 */
	public boolean saveFileToServer(String fileName) {
		boolean saveSuccessfull = saveAsFileToServer(fileName, fileName);
		return saveSuccessfull;
	}
	
}

package networking.Udp; 
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import projekt.Alarm;
import projekt.Packet;
import projekt.TimeHistory;
import projekt.Tools;

public class UDPClient {
	public static void main(String[] args) {
		String device = "Karol";
		String description = "student";
		long date = 1000; 
		int channelNr = 1;
		String unit = "st";
		double Resolution = 0.01;
		double sens = 0.1;
		Double[] ffff = new Double[2];
		TimeHistory<Double> packet = new TimeHistory<Double>(device, description, date, channelNr, unit, Resolution,ffff,sens);
		System.out.println(packet);
		byte[] data = Tools.serialize(packet);
		DatagramSocket aSocket = null;
		Scanner scanner = null;
		
		try {

			
			byte[] buffer = new byte[1024];
			
			InetAddress aHost = InetAddress.getByName("localhost");
			int serverPort = 9876;
			aSocket = new DatagramSocket();
			scanner = new Scanner(System.in);
//			while (true) {
//				System.out.println("Enter your request (+,-,?,!)|nick|...|");
//				if (scanner.hasNextLine())
//					line = scanner.nextLine();
				DatagramPacket request = new DatagramPacket(data, data.length, aHost, serverPort);
				aSocket.send(request);
				DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
				aSocket.receive(reply);
				Packet p = (Packet) Tools.deserialize(reply.getData());
				System.out.println("Reply: "+p);
//			}
		} catch (SocketException ex) {
			Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
		} catch (UnknownHostException ex) {
			Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			aSocket.close();
			scanner.close();
		}
	}
}
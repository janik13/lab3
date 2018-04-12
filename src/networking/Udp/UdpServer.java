package networking.Udp;
 
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
//import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

//import projekt.Packet;
import projekt.Tools;

public class UdpServer {
    public static void main(String[] args) {
    	DatagramSocket aSocket = null;
      try {
        // args contain message content and server hostname
        aSocket = new DatagramSocket(9876);
        byte[] buffer = new byte[1024];
        while(true) {
          DatagramPacket request = new DatagramPacket(buffer, buffer.length);
          System.out.println("Waiting for request...");
          aSocket.receive(request);
//          Packet p = (Packet) Tools.deserialize(request.getData());
          Request p = (Request) Tools.deserialize(request.getData());
          switch(p.getType())
          {
          case "write":
          {
        	  File name = new File(p.getDevice()+"_"+p.getDescription()+".txt");
        	  Files.write(name.toPath(), request.getData());
        	  buffer = request.getData();
        	  System.out.println("Saved to file");
          } break;
          case "single":
          {
        	  Path path = FileSystems.getDefault().getPath(p.getValue() + ".txt");
        	  buffer = Files.readAllBytes(path);
        	  Request p2 = (Request) Tools.deserialize(buffer);
        	  System.out.println("Read from file: " + p2.toString());
          } break;
          default:
          {
        	  buffer = null;
        	  System.out.println("Wrong request");
          } break;
          }
          DatagramPacket reply = new DatagramPacket(buffer, buffer.length, 
          		request.getAddress(), request.getPort());
          aSocket.send(reply); 
        }
      } catch (SocketException ex) {
        Logger.getLogger(UdpServer.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IOException ex) {
        Logger.getLogger(UdpServer.class.getName()).log(Level.SEVERE, null, ex);
      } catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
				aSocket.close();
			}
      
    }
}


package application.Multiplayer;

import java.util.LinkedList;

import application.Models.Vector2D;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;


public class ServerProgram extends Listener {

	//Server object
	static Server server;
	//Ports to listen on
	static int udpPort = 27960, tcpPort = 27960;

	PacketMessage packetMessage = new PacketMessage();
	
	public static void main(String[] args) throws Exception {
		server = new Server();
		server.getKryo().register(java.util.concurrent.atomic.AtomicLong.class);
		server.getKryo().register(java.util.Random.class);
		server.getKryo().register(Vector2D.class);
		server.getKryo().register(LinkedList.class);
		server.getKryo().register(PacketMessage.class);
		server.bind(tcpPort, udpPort);
		server.start();
		server.addListener(new ServerProgram());
		System.out.println("Server is operational!");
	}

	public void connected(Connection c){
		System.out.println("Received a connection from "+c.getRemoteAddressTCP().getHostString());
		packetMessage.messages.add(new Vector2D());
	}

	public void received(Connection c, Object p){
		if(p instanceof PacketMessage){
			PacketMessage packet = (PacketMessage) p;
			System.out.println(packet.messages.size());
			c.sendTCP(packetMessage);
		}
	}

	public void disconnected(Connection c){
		System.out.println("A client disconnected!");
	}
}

package application.Multiplayer;

import application.Models.Vector2D;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import java.util.LinkedList;


public class ClientProgram extends Listener {

	static Client client;
	static String ip = "localhost";
	static int tcpPort = 27960, udpPort = 27960;
	static boolean messageReceived = false;

	static long timer = System.currentTimeMillis();
	
	public static void main(String[] args) throws Exception {
		PacketMessage thetest = new PacketMessage();
		System.out.println("Connecting to the server...");
		client = new Client();
		client.getKryo().register(java.util.concurrent.atomic.AtomicLong.class);
		client.getKryo().register(java.util.Random.class);
		client.getKryo().register(Vector2D.class);
		client.getKryo().register(LinkedList.class);
		client.getKryo().register(PacketMessage.class);
		client.start();
		client.connect(5000, ip, tcpPort, udpPort);
		client.addListener(new ClientProgram());
		client.sendTCP(thetest);
		System.out.println("Connected! The client program is now waiting for a packet...\n");
		while(true){
			if(timer + 50< System.currentTimeMillis()){
				client.sendTCP(thetest);
				timer = System.currentTimeMillis();
			}
		}
	}

	public void received(Connection c, Object p){
		if(p instanceof PacketMessage){
			PacketMessage packet = (PacketMessage) p;
			System.out.println(packet.messages.size());
			messageReceived = true;
		}
	}
}

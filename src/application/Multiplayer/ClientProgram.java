package application.Multiplayer;

import application.Models.Vector2D;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;
import java.util.LinkedList;



public class ClientProgram extends Listener {


	//private String ip = "localhost";
	private int tcpPort = 27960, udpPort = 27960;
	private Client client;
	private static PacketMessage pm ;

	String ip = "192.168.1.144";


	public  PacketMessage getPm() {
		return pm;
	}

	public void setPm(PacketMessage pm) {
		ClientProgram.pm = pm;
		client.sendTCP(pm);
	}

	public void start() throws IOException {
		pm = new PacketMessage();
		client = new Client();
		client.getKryo().register(Boolean.class);
		client.getKryo().register(java.util.concurrent.atomic.AtomicLong.class);
		client.getKryo().register(java.util.Random.class);
		client.getKryo().register(Vector2D.class);
		client.getKryo().register(BulletPositions.class);
		client.getKryo().register(LinkedList.class);
		client.getKryo().register(PacketMessage.class);
		client.start();
		client.connect(5000, ip, tcpPort, udpPort);
		client.addListener(new ClientProgram());
	}

	public void received(Connection c, Object p){
		if(p instanceof PacketMessage){
			PacketMessage packet = (PacketMessage) p;
			this.pm = packet;
		}
	}
}

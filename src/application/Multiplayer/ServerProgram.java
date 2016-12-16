package application.Multiplayer;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import application.Models.Vector2D;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;


public class ServerProgram extends Listener {

	private Server server;
	private int udpPort = 27960, tcpPort = 27960;
	private List<Connection> myConnections = new LinkedList<>();
	static PacketMessage packetMessage = new PacketMessage();
	static PacketMessage packetMessage2 = new PacketMessage();

	
	public static void main(String[] args) throws Exception {
		ServerProgram serverProgram = new ServerProgram();
		serverProgram.start();
		packetMessage.setId(1);
		packetMessage2.setId(2);
	}

	public void start() throws IOException {
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
		myConnections.add(c);
		System.out.println("Received a connection from "+c.getRemoteAddressTCP().getHostString());
	}

	private void sendMsg(){
		if(myConnections.size() == 2){
			myConnections.get(0).sendTCP(packetMessage);
			myConnections.get(1).sendTCP(packetMessage2);
		}
	}

	public void received(Connection c, Object p){
		if(p instanceof PacketMessage){
			PacketMessage packet = (PacketMessage) p;
			if(myConnections.size() == 2){
				if(c == myConnections.get(0)){
					this.packetMessage.setFirstCharacter(packet.getFirstCharacter());
					this.packetMessage2.setFirstCharacter(packet.getFirstCharacter());
				}
				else{
					this.packetMessage.setSecondCharacter(packet.getFirstCharacter());
					this.packetMessage2.setSecondCharacter(packet.getFirstCharacter());
				}
			sendMsg();
		}
	}}

	public void disconnected(Connection c){
		myConnections.remove(c);
		System.out.println("A client disconnected!");
	}
}

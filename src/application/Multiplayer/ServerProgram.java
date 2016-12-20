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
	private List<Vector2D> enemieLocations;

	
	public static void main(String[] args) throws Exception {
		ServerProgram serverProgram = new ServerProgram();
		serverProgram.start();
		serverProgram.addLocs();
		packetMessage.setId(1);
		packetMessage2.setId(2);
	}

	public void addLocs(){
		this.enemieLocations = new LinkedList<>();
		for (int i = 0; i < 5; i++) {
			this.enemieLocations.add(new Vector2D());
		}
		this.packetMessage.setEnemies(this.enemieLocations);
		this.packetMessage2.setEnemies(this.enemieLocations);
	}

	public void start() throws IOException {
		server = new Server();
		server.getKryo().register(Boolean.class);
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
			System.out.println(this.packetMessage.getEnemies().size());
			myConnections.get(0).sendTCP(this.packetMessage);
			myConnections.get(1).sendTCP(this.packetMessage2);
		}
	}

	public void received(Connection c, Object p){
		if(p instanceof PacketMessage){
			PacketMessage packet = (PacketMessage) p;
			if(myConnections.size() == 2){
				if(c == myConnections.get(0)){
					this.packetMessage.setFirstCharacter(packet.getFirstCharacter());
					this.packetMessage2.setFirstCharacter(packet.getFirstCharacter());
					System.out.println(packet.getEnemies().size());
					packetMessage.setSpawnFirstClient(packet.getSpawnFirstClient());
					packetMessage2.setSpawnFirstClient(packet.getSpawnFirstClient());
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

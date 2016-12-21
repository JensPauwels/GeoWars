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
	private static PacketMessage packetMessage = new PacketMessage();
	private static PacketMessage packetMessage2 = new PacketMessage();
	private List<Vector2D> enemieLocations;
	private List<Integer> deadenemies = new LinkedList<>();
	private int wave = 1;

	
	public static void main(String[] args) throws Exception {
		ServerProgram serverProgram = new ServerProgram();
		serverProgram.start();
		serverProgram.addLocs();
		packetMessage.setId(1);
		packetMessage2.setId(2);

	}

	public void addLocs(){
		this.deadenemies = new LinkedList<>();
		this.enemieLocations = new LinkedList<>();
		for (int i = 0; i < (5*this.wave); i++) {
			this.enemieLocations.add(new Vector2D());
		}
		packetMessage.setEnemies(this.enemieLocations);
		packetMessage2.setEnemies(this.enemieLocations);
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
		if(myConnections.size() == 2 ){
			sendMsg();
		}
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
					packetMessage.setFirstCharacter(packet.getFirstCharacter());
					packetMessage2.setFirstCharacter(packet.getFirstCharacter());
					packetMessage.setSpawnFirstClient(packet.getSpawnFirstClient());
				}
				else{
					packetMessage.setSecondCharacter(packet.getFirstCharacter());
					packetMessage2.setSecondCharacter(packet.getFirstCharacter());
					packetMessage2.setSpawnSecondClient(packet.getSpawnSecondClient());
				}
				checkOnDeadEnemies(packet.getDeadEnemies());
			sendMsg();
		}
	}}

	public void checkOnDeadEnemies(List<Integer> ids){
		for (int i = 0; i < ids.size(); i++) {
			if(!deadenemies.contains(ids.get(i))){
				deadenemies.add(ids.get(i));
			}
		}
		if(deadenemies.size() == wave*5){
			deadenemies.removeAll(deadenemies);
			System.out.println("deadenemies size " +deadenemies.size());
			this.wave = this.wave +1;
			System.out.println(wave*5);
			addLocs();
			System.out.println(enemieLocations.size());
			packetMessage.setSpawnFirstClient(true);
			packetMessage2.setSpawnSecondClient(true);
		}
		packetMessage.setDeadEnemies(deadenemies);
		packetMessage2.setDeadEnemies(deadenemies);

	}

	public void disconnected(Connection c){
		myConnections.remove(c);
		packetMessage = new PacketMessage();
		packetMessage2 = new PacketMessage();
		addLocs();
		packetMessage.setId(1);
		packetMessage2.setId(2);
		System.out.println("A client disconnected!");
	}
}

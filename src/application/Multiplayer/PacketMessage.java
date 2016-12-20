package application.Multiplayer;

import application.Models.Vector2D;

import java.util.LinkedList;
import java.util.List;

public class PacketMessage {



	private List<Vector2D> bullets = new LinkedList<>();
	private Vector2D firstCharacter;
	private Vector2D secondCharacter;
	public Boolean spawnFirstClient = true;
	public Boolean spawnSecondClient = true;

	public Boolean getSpawnSecondClient() {
		return spawnSecondClient;
	}

	public void setSpawnSecondClient(Boolean spawnSecondClient) {
		this.spawnSecondClient = spawnSecondClient;
	}

	public Boolean getSpawnFirstClient() {
		return spawnFirstClient;
	}

	public void setSpawnFirstClient(Boolean spawnFirstClient) {
		this.spawnFirstClient = spawnFirstClient;
	}

	public List<Vector2D> enemies = new LinkedList<>();

	public int id=0;

	public List<Vector2D> getEnemies() {
		return enemies;
	}

	public void setEnemies(List<Vector2D> enemies) {
		this.enemies = enemies;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PacketMessage(){
		this.firstCharacter = new Vector2D(500,400);
		this.secondCharacter = new Vector2D(300,400);


	}


	public List<Vector2D> getBullets() {
		return bullets;
	}

	public void setBullets(List<Vector2D> bullets) {
		this.bullets = bullets;
	}

	public Vector2D getFirstCharacter() {
		return firstCharacter;
	}

	public void setFirstCharacter(Vector2D firstCharacter) {
		this.firstCharacter = firstCharacter;
	}

	public Vector2D getSecondCharacter() {
		return secondCharacter;
	}

	public void setSecondCharacter(Vector2D secondCharacter) {
		this.secondCharacter = secondCharacter;
	}
}

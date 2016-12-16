package application.Multiplayer;

import application.Models.EnemyType.Enemy;
import application.Models.Vector2D;

import java.util.LinkedList;
import java.util.List;

public class PacketMessage {



	private List<Vector2D> enemys,bullets = new LinkedList<>();
	private Vector2D firstCharacter;
	private Vector2D secondCharacter;
	public String message = "testing";

	public int id=0;


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

	public List<Vector2D> getEnemys() {
		return enemys;
	}

	public void setEnemys(List<Vector2D> enemys) {
		this.enemys = enemys;
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

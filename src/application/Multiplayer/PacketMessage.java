package application.Multiplayer;

import application.Models.Vector2D;

import java.util.LinkedList;
import java.util.List;

public class PacketMessage {

	public String message;

	public List<Vector2D> messages = new LinkedList<>();

	public PacketMessage(){
		messages.add(new Vector2D());
		messages.add(new Vector2D());
		messages.add(new Vector2D());
	}

	public void setList(List<Vector2D> list){
		messages = list;
	}
}

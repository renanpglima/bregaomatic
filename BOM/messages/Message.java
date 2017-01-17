package messages;

import java.io.Serializable;

public class Message implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int senderId;
	
	public Message() {
		senderId = 0;
	}

	public Message(int senderId) {
		this.senderId = senderId;
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}	
}

package messages;

import business.Tile;

public class UIMessage extends Message {
	private static final long serialVersionUID = 0L;
	private EUIMessageType messageType;
	private Tile tile;	
	
	public UIMessage() {
		super(0);
	}
	
	public UIMessage(int senderId, EUIMessageType type, Tile tile) {
		super(senderId);
		this.messageType = type;
		this.tile = tile;
	}

	public EUIMessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(EUIMessageType messageType) {
		this.messageType = messageType;
	}

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}
}

package business;

import java.io.Serializable;

public class Tile implements Serializable{
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private int value;
	
	public Tile()
	{
	}
	
	public Tile(int x, int y)
	{
		this.x = x;
		this.y = y;		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}

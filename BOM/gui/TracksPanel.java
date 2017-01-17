package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import messages.EUIMessageType;
import messages.Message;
import messages.UIMessage;
import business.CoreManager;
import business.Tile;

@SuppressWarnings("serial")
public class TracksPanel extends JPanel {

	//Esse atributo tem que ser setado pelo FMain.
	public int userId;
	public FMain parentFrame = null;
	private JLabel backgroundLbl = null;
	private Timer timer = new Timer();
	
	/**
	 * This is the default constructor
	 */
	public TracksPanel() {
		super();
		initialize();
		timer.schedule(new TimerTaskPanel(this),0,500);
		//Message message = new UIMessage(userId, EUIMessageType.LOAD, null);
		//CommunicationHandler.getInstance().getCommunicationManager().send(message);
	}
	
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setBounds(new Rectangle(0, 0, 800, 211));
		backgroundLbl = new JLabel();
		backgroundLbl.setText("");
		backgroundLbl.setIcon(new ImageIcon(getClass().getResource(Constants.TRACK_PANE)));
		this.add(backgroundLbl, null);
		this.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				TracksPanel.this.handleMouseClick(e);
			}
		});
	}

	protected void handleMouseClick(MouseEvent e) {
		//TODO: Verificar se o BUTTON1 é realmente o botão esquerdo do mouse
		if (e.getButton() == MouseEvent.BUTTON1) {
			//1. Obter o tile no qual o mouse foi clicado.
			Tile clickedTile = this.getTileFromPixel(e.getX(), e.getY());
			if(clickedTile != null) {
				//JOptionPane.showMessageDialog(null, "x = " + clickedTile.getX() + "\ny = " + clickedTile.getY());
				//2. Mandar a mensagem pro communicationManager
				Message message = new UIMessage(userId, EUIMessageType.TILE_CLICKED, clickedTile);
				try {
					CommunicationHandler.getInstance().getCommunicationManager().send(message);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			this.parentFrame.repaint();
		}
	}
	
	//@Override
	public void paint(Graphics graphic) {		
		Tile[][] tiles = CoreManager.getInstance().getTiles();
		Tile t = null;
		int tileValue = (-1);
		
		if (tiles != null) {			
			for (int i = 0; i < tiles.length; i++) {
				for (int j = 0; j < tiles[i].length; j++) {
					t = tiles[i][j];
					tileValue = t.getValue();
					
					if (tileValue >= 0) {
						this.paintValuedTile(i, j, tileValue, graphic);
					} else if (tileValue == CoreManager.BEFORE_SAMPLE) {
						this.paintTile(i,j, graphic);
					}
				}
			}
		}
		paintCursor(graphic);
	}
	
	private void paintCursor(Graphics graphic) {
		int col = CoreManager.getInstance().getCurrentColumn() - 1;
		if (col < 0)
			col = 0;
		
		if (graphic != null){
			graphic.setColor(Color.BLUE);
			graphic.drawRect(getPixelX(col), 0, 21, 211);
		}
	}

	private int getPixelX(int i) {
		return ((21 * i) + 65);
	}
	
	private int getPixelY(int j) {
		int pixelY = (-1);
		
		switch (j) {
			case 0:
				pixelY = 21;			
				break;
			case 1:
				pixelY = 50;
				break;
			case 2:
				pixelY = 81;
				break;
			case 3:
				pixelY = 111;
				break;
			case 4:
				pixelY = 141;
				break;
			case 5:
				pixelY = 171;
				break;
			default:
				break;
		}
		
		return pixelY;
	}

	private void paintTile(int i, int j, Graphics graphic) {
		graphic.setColor(Constants.instrumentsColors[j]);
		graphic.fillOval(this.getPixelX(i) + 4, this.getPixelY(j) + 5, 13, 13);
	}
	private void paintValuedTile(int i, int j, int value, Graphics graphic) {
		graphic.setColor(Constants.instrumentsColors[j]);
		graphic.fillOval(this.getPixelX(i) + 2, this.getPixelY(j) + 3, 17, 17);
		
		Font font = new Font("raavi", Font.PLAIN | Font.BOLD, 14);
		graphic.setFont(font);
		graphic.setColor(Color.WHITE);
		String valueString = "" + value;
		FontMetrics metrics = graphic.getFontMetrics(font);
		int stringWidth = metrics.stringWidth(valueString);
		int stringHeight = metrics.getHeight();
		int centroX = this.getPixelX(i) + 10;
		int centroY = this.getPixelY(j) + 28;
		int x = centroX - stringWidth/2;
		int y = centroY - stringHeight/2;
		graphic.drawString(valueString, x, y);
	}

	private Tile getTileFromPixel(int x, int y) {
		Tile tile = null;
		
		if (x >= 65 && x <= 695) {
			int tX = (x - 65)/21;
			tile = new Tile();
			tile.setX(tX);
			
			int tY = -1;
			
			if (y >= 21 && y <= 42)
				tY = 0;
			else if(y >= 50 && y <= 71)
				tY = 1;
			else if(y >= 81 && y <= 102)
				tY = 2;
			else if(y >= 111 && y <= 132)
				tY = 3;
			else if(y >= 141 && y <= 162)
				tY = 4;
			else if(y >= 171 && y <= 192)
				tY = 5;
			
			//Se nao for um Y valido ele anula o tile.
			if (tY == -1) {
				tile = null;
			} else {
				tile.setY(tY);
			}
			
		}
		
		return tile;
	}
	
}  //  @jve:decl-index=0:visual-constraint="10,10"

class TimerTaskPanel extends TimerTask {

	TracksPanel panel;
	
	public TimerTaskPanel(TracksPanel panel) {
		this.panel = panel;
	}
	@Override
	public void run() {
		if(panel.parentFrame != null && panel.parentFrame.isInitialized)
			panel.parentFrame.paint(panel.parentFrame.getGraphics());
	}
}

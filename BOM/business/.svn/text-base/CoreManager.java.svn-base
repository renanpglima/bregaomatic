package business;

import java.io.File;
import java.io.IOException;
import java.util.Timer;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.sun.corba.se.impl.orbutil.closure.Constant;
import com.sun.org.apache.bcel.internal.Constants;

import messages.EUIMessageType;
import messages.Message;
import messages.UIMessage;
import data.TrackCollection;

public class CoreManager {
	
	public static final int NUMBER_COLUMN = 30;
	public static final int NUMBER_OF_INSTRUMETS = 6;
	public static final int WITHOUT_SAMPLE = -2;
	public static final int BEFORE_SAMPLE = -1;
	public static final long TILE_TIMESTAMP = 3002;	
	
	private int currentColumn;
	private Tile[][] tiles;
	private static CoreManager instance;
	private Timer timer;
	private TimerTile timerTile;
			
	
	protected CoreManager() {
		this.currentColumn = 0;
		this.tiles = new Tile[NUMBER_COLUMN][NUMBER_OF_INSTRUMETS];
		this.loadTiles();
	}
	
	public static CoreManager getInstance() {
		if (instance == null)
			instance = new CoreManager();
		
		return instance;
	}
	
	public void addTile(Tile tile){
		Tile t = this.tiles[tile.getX()][tile.getY()];
		TrackCollection track = TrackCollection.getInstance();
		int size = track.numberSample(tile.getY());
		if(t.getValue() == WITHOUT_SAMPLE){
			t.setValue(0);
		} else if(t.getValue() == size-1){
			t.setValue(WITHOUT_SAMPLE);
			int next = BEFORE_SAMPLE;
			for(int i =1;next==BEFORE_SAMPLE;i++ ){
				if(tile.getX()+i<NUMBER_COLUMN){
					next = this.tiles[tile.getX()+i][tile.getY()].getValue();
					if(next==BEFORE_SAMPLE){
						this.tiles[tile.getX()+i][tile.getY()].setValue(WITHOUT_SAMPLE);
					}
				}else{
					break;
				}
			}
			int i =-1;
			int x = tile.getX() +i;
			while(x>=0 && this.tiles[x][tile.getY()].getValue()==BEFORE_SAMPLE){
				i--;
				x = tile.getX() +i;
			}
			if(x>=0 && this.tiles[x][tile.getY()].getValue()!=WITHOUT_SAMPLE){
				Tile tileTemp = this.tiles[x][tile.getY()];
				int fillTile = track.get(tile.getY()).elementAt(this.tiles[x][tile.getY()].getValue()).getTileFill();
				for(int j = 1; j<fillTile && j+tileTemp.getX()< NUMBER_COLUMN; j++ ){
					if(this.tiles[j+tileTemp.getX()][tileTemp.getY()].getValue() != WITHOUT_SAMPLE && 
							this.tiles[j+tileTemp.getX()][tileTemp.getY()].getValue() != BEFORE_SAMPLE)
						break;
					else
						this.tiles[j+tileTemp.getX()][tileTemp.getY()].setValue(BEFORE_SAMPLE);
				}
			}
		}else{
			t.setValue((t.getValue()+1)%size);
		}
		if(t.getValue() != WITHOUT_SAMPLE && t.getValue()!= BEFORE_SAMPLE){
			int fillTile = track.get(tile.getY()).elementAt(t.getValue()).getTileFill();
			int aux =1;
			for(int i = 1; i<fillTile && i+tile.getX()< NUMBER_COLUMN; i++ ){
				if(this.tiles[i+tile.getX()][tile.getY()].getValue() != WITHOUT_SAMPLE && 
						this.tiles[i+tile.getX()][tile.getY()].getValue() != BEFORE_SAMPLE)
					break;
				else
					this.tiles[i+tile.getX()][tile.getY()].setValue(BEFORE_SAMPLE);
				aux = i;
			}
			aux++;
			while(aux + tile.getX()<NUMBER_COLUMN && this.tiles[tile.getX()+aux][tile.getY()].getValue() == BEFORE_SAMPLE){
				this.tiles[aux + tile.getX()][tile.getY()].setValue(WITHOUT_SAMPLE);
				aux++;
			}
			
		}
		
	}
	
	public void play(){
		this.timer = new Timer();
		this.timerTile = new TimerTile(this.timer);
		this.timerTile.play();
		this.timer.schedule(this.timerTile ,0, TILE_TIMESTAMP);
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}
	
	public void changeTile() throws InvalidMidiDataException{
		Tile[] t= this.tiles[this.currentColumn];
		TrackCollection track = TrackCollection.getInstance();
		for(int i =0 ; i< t.length; i++){
			if(t[i].getValue() != WITHOUT_SAMPLE && t[i].getValue() != BEFORE_SAMPLE){
				track.get(i).starElementAt(t[i].getValue());
			}
		}
		this.currentColumn = (this.currentColumn +1)%NUMBER_COLUMN;
		//System.out.println("CurrentColumn: "+ this.currentColumn);
	}
	
	public void loadTiles(){
		for(int i = 0; i < NUMBER_COLUMN; i++){
			for(int j = 0; j < NUMBER_OF_INSTRUMETS; j++){
				this.tiles[i][j] = new Tile(i,j);
				this.tiles[i][j].setValue(WITHOUT_SAMPLE);
			}
		}
	}
	
	public void receive(Message message) {
		if (message != null) {
			
			if (message instanceof UIMessage) {
				UIMessage msg = (UIMessage)message;
				
				if (msg.getMessageType() == EUIMessageType.PLAY) {
					this.play();
				} else if (msg.getMessageType() == EUIMessageType.STOP) {
					this.stop();
				} else if (msg.getMessageType() == EUIMessageType.TILE_CLICKED) {
					Tile clickedTile = msg.getTile();
					
					if (clickedTile != null) {
						this.addTile(clickedTile);
					}
				}else if (msg.getMessageType() == EUIMessageType.LOAD) {
					TrackCollection.getInstance();
				}
			}
			else {
				//TODO: Colocar o tratamento pra outros tipos de mensagens
			}
		}		
	}
	
	public void stop(){
		this.currentColumn = 0;
		this.timerTile.stop();
		this.timer.cancel();
		this.timerTile.cancel();
		this.timer= null;
		this.timerTile = null;
		TrackCollection.getInstance().stop();
	}
	
	public int sizeTile(Tile t){
		return TrackCollection.getInstance().get(t.getY()).elementAt(t.getValue()).getTileFill();
	}

	public int getCurrentColumn() {
		return currentColumn;
	}

	public void setCurrentColumn(int currentColumn) {
		this.currentColumn = currentColumn;
	}
	
	public void playBacksound() {
	      File arquivo;
	      AudioInputStream entrada = null;
	      AudioFormat formatoDaEntrada;
	      DataLine.Info descricaoDaEntrada;
	      Clip clip;
	      try {
	        arquivo = new File(data.Constants.BACKSOUND);
	        entrada = AudioSystem.getAudioInputStream(arquivo);
	        formatoDaEntrada = entrada.getFormat();
	        descricaoDaEntrada = new DataLine.Info(Clip.class, formatoDaEntrada);
			if (!AudioSystem.isLineSupported(descricaoDaEntrada)) {
	         System.out.println("Não é possível reproduzir arquivos deste tipo");
	        }
	        clip = (Clip) AudioSystem.getLine(descricaoDaEntrada);
	        clip.open(entrada);
	        clip.start();
	        clip.drain();
	        } catch (IOException excecao) {
	            excecao.printStackTrace();
	        } catch (UnsupportedAudioFileException excecao) {
	            excecao.printStackTrace();
	        } catch (LineUnavailableException excecao) {
	            excecao.printStackTrace();
	        } finally {
	            if (entrada != null) {
	                try {
	                    entrada.close();
	                } catch (IOException excecao) {
	                    excecao.printStackTrace();
	                }
	            } 
	        }
	    }
		
}

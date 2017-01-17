package business;

import java.util.Timer;
import java.util.TimerTask;

import javax.sound.midi.InvalidMidiDataException;

public class TimerTile extends TimerTask{
	
	private Timer timer;
	private boolean stop;
	
	
	public TimerTile(Timer timer) {
		super();
		this.timer = timer;
	}
	
	public void run(){
		if(!stop){
			// Executar Midi
			try {
				CoreManager.getInstance().changeTile();
			} catch (InvalidMidiDataException e) {
				e.printStackTrace();
			}
			
		}else{
			timer.cancel();
		}		
	}

	public void stop(){
		this.stop = true;
	}
	
	public void play(){
		this.stop = false;
	}
}

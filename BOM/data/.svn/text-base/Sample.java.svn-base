package data;

import java.io.File;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;

import business.CoreManager;

public class Sample {
	
	private Sequence sequence;
	
	private int tileFill;
	private boolean isSolo;
	
	public Sample(File midi){
		try {
			this.sequence = MidiSystem.getSequence (midi);
			this.calcularTileFill();
						
		} catch (InvalidMidiDataException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Sequence getSequence() {
		return sequence;
	}

	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}

	public int getTileFill() {
		return tileFill;
	}

	public void setTileFill(int tileFill) {
		this.tileFill = tileFill;
	}
	
	public void calcularTileFill(){
		double temp = this.sequence.getMicrosecondLength()/1000;
		temp = Math.ceil(temp/CoreManager.TILE_TIMESTAMP);
		if(temp <1){
			this.tileFill = 1;
		}else {
			this.tileFill = (int)temp;
		}
		
	}
	
	public boolean isSolo() {
		return isSolo;
	}

	public void setSolo(boolean isSolo) {
		this.isSolo = isSolo;
	}
}

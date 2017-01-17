package data;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Transmitter;

public class SampleCollection {
	
	private EInstrument instrument;
	private List<Sample> samples;
	private Sequencer sequencer;
	
	public SampleCollection(EInstrument instrument, Synthesizer sint) throws MidiUnavailableException{
		this.instrument = instrument;
		this.samples = new ArrayList<Sample>();
		this.sequencer = MidiSystem.getSequencer();
		this.sequencer.open();
		Transmitter seqTrans = sequencer.getTransmitter ();
		Receiver sintRcvr = sint.getReceiver();
		seqTrans.setReceiver (sintRcvr);
	}
	
	
	public void add(Sample s){
		this.samples.add(s);
	}
	
	public void remove(int i){
		this.samples.remove(i);
	}
	
	public Sample elementAt(int i){
		return this.samples.get(i);
	}
	
	public void starElementAt(int i) throws InvalidMidiDataException{
		this.sequencer.stop();
		this.sequencer.setTickPosition(0);
		Sample s = this.samples.get(i);
		this.sequencer.setSequence(s.getSequence());
		this.sequencer.start();
	}
	
	public void stop(){
		this.sequencer.stop();
		this.sequencer.setTickPosition(0);		
	}
	
	public int sizeList(){
		return this.samples.size();		
	}
	
	public EInstrument getInstrument() {
		return instrument;
	}


	public void setInstrument(EInstrument instrument) {
		this.instrument = instrument;
	}
	
	  
	
	

}

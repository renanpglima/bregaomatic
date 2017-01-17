package data;

import java.io.File;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.org.apache.xerces.internal.dom.DeferredElementImpl;

public class TrackCollection {
	
	private SampleCollection[] musicInstruments;
	private Synthesizer synthesizer;
		
	private Document xmlDocument;
	
	private static TrackCollection instance;
	
	public static TrackCollection getInstance(){
		if (instance == null){
			instance = new TrackCollection();
		}
		return instance;
	}
	
	private TrackCollection(){
		
		this.musicInstruments = new SampleCollection[6];
		
		try {
			this.synthesizer = MidiSystem.getSynthesizer();
			this.synthesizer.open();
					
		} catch (MidiUnavailableException e) {
			
		}
		
		xmlDocument = parseXmlFile(Constants.SAMPLES, false);
		NodeList xmlInstruments = xmlDocument.getElementsByTagName("track");
		
		//pegando cada instrumento midi
		for (int i = 0 ; i <  xmlInstruments.getLength(); i++){
			Node xmlInstrument = xmlInstruments.item(i);
			NamedNodeMap instrumentAttributes = xmlInstrument.getAttributes();
			String instrumentName = instrumentAttributes.item(0).getNodeValue();
			NodeList xmlMidis = xmlInstrument.getChildNodes();
			
			EInstrument instrument = null;
			try {
				instrument = EInstrument.toInstrument(instrumentName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//criando a coleção
			SampleCollection sampleCollection = null;
			try {
				sampleCollection = new SampleCollection(instrument, synthesizer);
			} catch (MidiUnavailableException e) {
				e.printStackTrace();
			}
			
			String classpath = System.getProperty("java.class.path");
			
			//carregando os atributos do sample
			for (int j = 0; j < xmlMidis.getLength(); j++){
				Node xmlMidi = xmlMidis.item(j);
				if (xmlMidi instanceof DeferredElementImpl)
				{
					NamedNodeMap midAttributes = xmlMidi.getAttributes();
					
					//atributos
					String fileName = midAttributes.getNamedItem("fileName").getNodeValue();
					boolean isSolo = Boolean.parseBoolean(midAttributes.getNamedItem("solo").getNodeValue());
					//int tiles = Integer.parseInt(midAttributes.getNamedItem("tiles").getNodeValue());
					
					//preencher a estrutura...
					File file = new File(classpath + fileName);					
					Sample sample = new Sample(file);
					sample.setSolo(isSolo);
					
					//insere na coleção
					sampleCollection.add(sample);
				}
			}
			
			try {
				this.musicInstruments[EInstrument.toInteger(instrument)] = sampleCollection;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	//Parses an XML file and returns a DOM document.
	//If validating is true, the contents is validated against the DTD
	//specified in the file.
	private Document parseXmlFile(String filename, boolean validating) {
		try {
			// Create a builder factory
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(validating);
			
			// Create the builder and parse the file
			DocumentBuilder docB = factory.newDocumentBuilder();
			File xml = new File(filename);
			Document doc = docB.parse(xml);
			return doc;
		} catch (Exception e) {
			//TODO: deu erro mo vei
			e.printStackTrace();
		}
		return null;
	}
	
	public void loadMusicInstruments(){
		
	}
	
	public SampleCollection get(int i){
		return this.musicInstruments[i];
	}
	
	public void stop(){
		for(int i= 0; i < this.musicInstruments.length; i++){
			this.musicInstruments[i].stop();
		}
	}
	
	public int numberSample(int i){
		return this.musicInstruments[i].sizeList();
	}
}

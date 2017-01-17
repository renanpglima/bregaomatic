package gui;

import communication.ICommunicationManager;
import communication.LocalCommunicationManager;

public class CommunicationHandler {
	
	private ICommunicationManager communicationManager;
	
	private static CommunicationHandler instance;
	
	private CommunicationHandler() {
		communicationManager = new LocalCommunicationManager();
	}
	
	public static CommunicationHandler getInstance() {
		if(instance == null)
			instance = new CommunicationHandler();
		return instance;
	}
	
	public ICommunicationManager getCommunicationManager() {
		return communicationManager;
	}
	
	public void setCommunicationManager(ICommunicationManager cm) {
		this.communicationManager = cm;
	}
}

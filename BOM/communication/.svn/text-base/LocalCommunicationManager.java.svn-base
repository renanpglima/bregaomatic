package communication;

import java.rmi.RemoteException;

import messages.Message;
import business.CoreManager;

public class LocalCommunicationManager implements ICommunicationManager{

	//Conecta o cliente no servidor e retorna o Id do cliente.
	public int connect() throws RemoteException {
		return 0;
	}
	
	public void send(Message message) throws RemoteException {
		CoreManager.getInstance().receive(message);
	}
}

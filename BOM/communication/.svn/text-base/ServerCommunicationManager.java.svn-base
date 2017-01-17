package communication;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import messages.Message;
import business.CoreManager;

public class ServerCommunicationManager extends UnicastRemoteObject implements ICommunicationManager, IServer {
	private static final long serialVersionUID = 1L;
	
	private Vector<IClient> clients = new Vector<IClient>();
	
	public ServerCommunicationManager() throws RemoteException {
		super();
		
		try {
			Naming.rebind("bregaomaticServer", this);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		System.out.println("foi!");
	}

	/* (non-Javadoc)
	 * @see communication.IServer#connect()
	 */
	public int connect() throws RemoteException {
		return 0;
	}
	
	/* (non-Javadoc)
	 * @see communication.IServer#connect(java.lang.String)
	 */
	public int connect(String clientRMIName) throws RemoteException {
		int id = -1;
		
		IClient client;
		try {
			client = (IClient) Naming.lookup("//localhost/bregaomaticClient");
			clients.add(client);
			id = clients.size() + 1;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		return id;
	}

	/* (non-Javadoc)
	 * @see communication.IServer#send(messages.Message)
	 */
	public void send(Message message) throws RemoteException {
		this.receive(message);		
	}

	/* (non-Javadoc)
	 * @see communication.IServer#receive(messages.Message)
	 */
	public void receive(Message message) throws RemoteException {
		CoreManager.getInstance().receive(message);
		
		for(IClient client : clients) {
			client.receive(message);
		}
	}

}

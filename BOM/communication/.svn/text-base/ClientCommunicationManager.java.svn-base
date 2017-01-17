package communication;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import messages.Message;
import business.CoreManager;

public class ClientCommunicationManager extends UnicastRemoteObject 
implements ICommunicationManager, IClient, Serializable{
	private static final long serialVersionUID = 1L;
	
	public IServer server;
	
	public ClientCommunicationManager(String serverHost) throws RemoteException {
		super();
		
		try {
			Naming.rebind("bregaomaticClient", this);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		try {
			server = (IServer) Naming.lookup("//" + serverHost + "/bregaomaticServer");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see communication.IClient#connect()
	 */
	public int connect() throws RemoteException {
		String hostname = null;
		
		try {
	        InetAddress addr = InetAddress.getLocalHost();
	        // Get hostname
	        hostname = addr.getHostAddress();
	    } catch (UnknownHostException e) {
	    }
		
		int id = server.connect("//" + hostname + "/bregaomaticClient");
		return id;
	}

	/* (non-Javadoc)
	 * @see communication.IClient#send(messages.Message)
	 */
	public void send(Message message) throws RemoteException {
		server.receive(message);
	}

	/* (non-Javadoc)
	 * @see communication.IClient#receive(messages.Message)
	 */
	public void receive(Message message) throws RemoteException {
		CoreManager.getInstance().receive(message);
	}
	

}

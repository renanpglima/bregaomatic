package communication;

import java.rmi.Remote;
import java.rmi.RemoteException;

import messages.Message;

public interface IClient extends Remote {

	public abstract int connect() throws RemoteException;

	public abstract void send(Message message) throws RemoteException;

	public abstract void receive(Message message) throws RemoteException;

}
package communication;

import java.rmi.Remote;
import java.rmi.RemoteException;

import messages.Message;

public interface IServer extends Remote{

	public abstract int connect() throws RemoteException;

	public abstract int connect(String clientRMI) throws RemoteException;

	public abstract void send(Message message) throws RemoteException;

	public abstract void receive(Message message) throws RemoteException;

}
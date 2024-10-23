import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Process_Interface extends Remote {
    void requestCriticalSection() throws RemoteException;
    void releaseCriticalSection() throws RemoteException;
    VectorClock getClock() throws RemoteException;
}
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MutexManager_Interface extends Remote {
    void requestEntry(int processId, VectorClock_Interface clock) throws RemoteException;
    void releaseEntry(int processId) throws RemoteException;
}

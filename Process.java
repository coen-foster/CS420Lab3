import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Process extends UnicastRemoteObject implements Process_Interface {
    private static final long serialVersionUID = 1L;
	private VectorClock clock;
    private MutexManager_Interface mutexManager;
    private int processId;

    public Process(int processId, MutexManager_Interface mutexManager) throws RemoteException {
        this.processId = processId;
        this.mutexManager = mutexManager;
        this.clock = new VectorClock(processId);
    }

    @Override
    public void requestCriticalSection() throws RemoteException {
        clock.increment();
        System.out.println("Process " + processId + " requesting critical section with clock: " + clock);
        mutexManager.requestEntry(processId, clock);
    }

    @Override
    public void releaseCriticalSection() throws RemoteException {
        System.out.println("Process " + processId + " releasing critical section");
        mutexManager.releaseEntry(processId);
    }

    @Override
    public VectorClock getClock() throws RemoteException {
        return clock;
    }
}

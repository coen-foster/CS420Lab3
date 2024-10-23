import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MutexManager extends UnicastRemoteObject implements MutexManager_Interface {
    private static final long serialVersionUID = 1L;
	private PriorityQueue<Request> requestQueue;
    private boolean isCriticalSectionOccupied;

    public MutexManager() throws RemoteException {
        this.requestQueue = new PriorityQueue<>(new RequestComparator());
        this.isCriticalSectionOccupied = false;
    }

    @Override
    public synchronized void requestEntry(int processId, VectorClock_Interface clock) throws RemoteException {
        requestQueue.add(new Request(processId, clock.getClock()));
        processRequests();
    }

    @Override
    public synchronized void releaseEntry(int processId) throws RemoteException {
        isCriticalSectionOccupied = false;
        processRequests();
    }

    private void processRequests() throws RemoteException {
        if (!isCriticalSectionOccupied && !requestQueue.isEmpty()) {
            Request nextRequest = requestQueue.poll();
            isCriticalSectionOccupied = true;
            System.out.println("Process " + nextRequest.getProcessId() + " enters critical section");
        }
    }

    // Request class to encapsulate processId and vector clock
    class Request {
        private int processId;
        private int[] clock;

        public Request(int processId, int[] clock) {
            this.processId = processId;
            this.clock = clock;
        }

        public int getProcessId() {
            return processId;
        }

        public int[] getClock() {
            return clock;
        }
    }

    // Comparator for queue based on Lamport's logical clock rules
    class RequestComparator implements Comparator<Request> {
        @Override
        public int compare(Request r1, Request r2) {
            for (int i = 0; i < r1.clock.length; i++) {
                if (r1.clock[i] < r2.clock[i]) {
                    return -1;
                } else if (r1.clock[i] > r2.clock[i]) {
                    return 1;
                }
            }
            return Integer.compare(r1.getProcessId(), r2.getProcessId());
        }
    }
}

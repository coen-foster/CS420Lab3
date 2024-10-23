import java.rmi.Naming;

public class Client {
    public static void main(String[] args) {
        try {
            MutexManager_Interface mutexManager = (MutexManager_Interface) Naming.lookup("rmi://localhost/MutexManager");
            Process_Interface process1 = new Process(0, mutexManager);
            Process_Interface process2 = new Process(1, mutexManager);
            Process_Interface process3 = new Process(2, mutexManager);
            
            process1.requestCriticalSection();
            process2.requestCriticalSection();
            Thread.sleep(2000);
            process1.releaseCriticalSection();
            Thread.sleep(2000);
            process2.releaseCriticalSection();
            process3.requestCriticalSection();
            Thread.sleep(2000);
            process3.releaseCriticalSection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
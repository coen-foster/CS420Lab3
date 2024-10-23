import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099); // Start the RMI registry on port 1099
            MutexManager mutexManager = new MutexManager(); // 3 processes
            Naming.rebind("rmi://localhost/MutexManager", mutexManager);
            System.out.println("MutexManager bound in registry");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


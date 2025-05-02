import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class HotelServer {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099); // Start RMI registry
            HotelImpl hotel = new HotelImpl(5); // 5 rooms total
            Naming.rebind("HotelService", hotel);
            System.out.println("Hotel Server is ready...");
        } catch (Exception e) {
            System.out.println("Server Error: " + e);
        }
    }
}

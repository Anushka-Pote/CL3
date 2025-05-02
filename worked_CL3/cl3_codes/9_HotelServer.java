import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class HotelServer extends UnicastRemoteObject implements HotelBooking {

    private HashMap<String, Boolean> bookings;

    public HotelServer() throws RemoteException {
        super();
        bookings = new HashMap<>();
        System.out.println("Hotel Server started...");
    }

    public String bookRoom(String guestName) throws RemoteException {
        if (bookings.containsKey(guestName)) {
            return "Room is already booked for " + guestName;
        } else {
            bookings.put(guestName, true);
            return "Room booked successfully for " + guestName;
        }
    }

    public String cancelBooking(String guestName) throws RemoteException {
        if (bookings.containsKey(guestName)) {
            bookings.remove(guestName);
            return "Booking cancelled for " + guestName;
        } else {
            return "No booking found for " + guestName;
        }
    }

    public static void main(String[] args) {
        try {
            HotelServer server = new HotelServer();
            Naming.rebind("HotelService", server);
            System.out.println("HotelService is ready.");
        } catch (Exception e) {
            System.out.println("Server Exception: " + e);
        }
    }
}

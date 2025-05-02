import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.*;

public class HotelImpl extends UnicastRemoteObject implements Hotel {
    private Map<String, Integer> bookings;
    private int availableRooms;

    public HotelImpl(int totalRooms) throws RemoteException {
        super();
        bookings = new HashMap<>();
        availableRooms = totalRooms;
    }

    public synchronized String bookRoom(String guestName) throws RemoteException {
        if (bookings.containsKey(guestName)) {
            return "Guest already has a booking.";
        }
        if (availableRooms > 0) {
            bookings.put(guestName, bookings.size() + 1);
            availableRooms--;
            return "Room booked for " + guestName;
        }
        return "No rooms available.";
    }

    public synchronized String cancelBooking(String guestName) throws RemoteException {
        if (bookings.containsKey(guestName)) {
            bookings.remove(guestName);
            availableRooms++;
            return "Booking cancelled for " + guestName;
        }
        return "No booking found for " + guestName;
    }
}

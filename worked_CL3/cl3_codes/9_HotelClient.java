import java.rmi.Naming;
import java.util.Scanner;

public class HotelClient {
    public static void main(String[] args) {
        try {
            HotelBooking hotel = (HotelBooking) Naming.lookup("rmi://localhost/HotelService");
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("\n--- Hotel Booking System ---");
                System.out.println("1. Book Room");
                System.out.println("2. Cancel Booking");
                System.out.println("3. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();
                sc.nextLine();  // Clear buffer

                if (choice == 1) {
                    System.out.print("Enter guest name: ");
                    String name = sc.nextLine();
                    System.out.println(hotel.bookRoom(name));
                } else if (choice == 2) {
                    System.out.print("Enter guest name: ");
                    String name = sc.nextLine();
                    System.out.println(hotel.cancelBooking(name));
                } else if (choice == 3) {
                    System.out.println("Thank you for using Hotel Booking System.");
                    break;
                } else {
                    System.out.println("Invalid choice. Try again.");
                }
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Client Exception: " + e);
        }
    }
}

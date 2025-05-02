import java.rmi.Naming;
import java.util.Scanner;

public class HotelClient {
    public static void main(String[] args) {
        try {
            Hotel hotel = (Hotel) Naming.lookup("rmi://localhost/HotelService");
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("\n1. Book Room\n2. Cancel Booking\n3. Exit");
                System.out.print("Choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); // Consume newline

                if (choice == 1) {
                    System.out.print("Enter Guest Name: ");
                    String name = sc.nextLine();
                    System.out.println(hotel.bookRoom(name));
                } else if (choice == 2) {
                    System.out.print("Enter Guest Name to Cancel: ");
                    String name = sc.nextLine();
                    System.out.println(hotel.cancelBooking(name));
                } else {
                    break;
                }
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Client Error: " + e);
        }
    }
}

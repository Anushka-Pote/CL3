import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // Change if needed
        int port = 5000; // Must match server port

        try (Socket socket = new Socket(serverAddress, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            // Take input from user
            System.out.print("Enter first string: ");
            String str1 = userInput.readLine();

            System.out.print("Enter second string: ");
            String str2 = userInput.readLine();

            // Send strings to server
            out.println(str1);
            out.println(str2);

            // Receive and print the concatenated result
            String result = in.readLine();
            System.out.println("Concatenated Result: " + result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

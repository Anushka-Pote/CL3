import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CharacterCount {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java CharacterCount <input_file>");
            return;
        }

        String filePath = args[0];
        Map<Character, Integer> charCountMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.replaceAll("\\s+", ""); // Remove spaces
                for (char c : line.toCharArray()) {
                    charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        // Print the character count
        for (Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

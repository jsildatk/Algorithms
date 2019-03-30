import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Helper {
    public static HashMap<Character, Integer> countCharacters(String input) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        char[] stringsArray = input.toCharArray();
        for (char c : stringsArray) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1); // if map contains key increment by 1
            } else {
                map.put(c, 1); // else set key's value to 1
            }
        }
        return map;
    }

    public static String readFile(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)), "UTF-8");
    }
}

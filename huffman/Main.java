import java.io.IOException;
import java.util.*;

public class Main {
    private static void print(Node root, String s, HashMap<Character, Integer> map, HashMap<String, Integer> values) {
        if (root.getLeft() == null && root.getLeft() == null) {
            System.out.println(root.getKey() + " | " + map.get(root.getKey()) + " | " + s);
            values.put(s, map.get(root.getKey()));
            return;
        }
        print(root.getLeft(), s + "0", map, values);
        print(root.getRight(), s + "1", map, values);
    }

    private static int codedLength(HashMap<String, Integer> map) {
        int result = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            result += (entry.getKey().length() * entry.getValue());
        }
        return result;
    }

    public static void main(String[] args) {
        try {
            String word = Helper.readFile("test"); // word from file
            HashMap<Character, Integer> map = Helper.countCharacters(word); // map with (character, occurrences)
            PriorityQueue<Node> queue = new PriorityQueue<Node>(word.length(), new HuffmanComparator());
            HashMap<String, Integer> values = new HashMap<>(); // map with (huffman code, occurrences)
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                Node node = new Node(entry.getKey(), entry.getValue(), null, null);
                queue.add(node);
            }
            Node root = null;
            while (queue.size() > 1) {
                Node x = queue.peek(); // first min extract
                queue.poll();

                Node y = queue.peek(); // second min extract
                queue.poll();

                Node n = new Node('-', x.getValue() + y.getValue(), x, y); // node that is sum of two extracted nodes
                root = n;
                queue.add(n); // add new node to queue
            }
            print(root, "", map, values);
            System.out.println("\nDlugosc slowa: " + word.length());
            System.out.println("Dlugosc zakodowanego slowa: " + codedLength(values));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

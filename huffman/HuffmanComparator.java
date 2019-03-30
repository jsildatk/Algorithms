import java.util.Comparator;

public class HuffmanComparator implements Comparator<Node> {
    @Override
    public int compare(Node o1, Node o2) {
        return o1.getValue() - o2.getValue();
    }
}

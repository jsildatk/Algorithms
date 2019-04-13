public class Main {
    public static void main(String[] args) {
        Tree tree = new Tree(3);
        tree.insert(10);
        tree.insert(20);
        tree.insert(5);
        tree.insert(6);
        tree.insert(12);
        tree.insert(30);
        tree.insert(7);
        tree.insert(17);
        tree.insert(9);
        tree.insert(90);
        tree.insert(21);
        tree.insert(11);
        tree.print();
        System.out.println("\n" + tree.search(17));
        System.out.println(tree.search(27));
        System.out.println(tree.search(10));
    }
}

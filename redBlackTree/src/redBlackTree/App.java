package redBlackTree;

public class App {
	public static void main(String[] args) {
		Tree rbTree = new Tree();
		rbTree.insert(rbTree.new Node(38));
		rbTree.insert(rbTree.new Node(31));
		rbTree.insert(rbTree.new Node(22));
		rbTree.insert(rbTree.new Node(8));
		rbTree.insert(rbTree.new Node(20));
		rbTree.print(rbTree.root);
	}
}

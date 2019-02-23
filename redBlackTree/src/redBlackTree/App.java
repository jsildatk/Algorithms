package redBlackTree;

public class App {
	public static void main(String[] args) {
		Tree rbTree = new Tree();
		rbTree.insert(rbTree.new Node(38));
		rbTree.insert(rbTree.new Node(31));
		rbTree.insert(rbTree.new Node(22));
		rbTree.insert(rbTree.new Node(8));
		rbTree.insert(rbTree.new Node(20));
		rbTree.insert(rbTree.new Node(1));
		rbTree.insert(rbTree.new Node(5));
		System.out.println("Drzewo (inorder): ");
		rbTree.print(rbTree.root);
		System.out.println("Ilosc czerwonych wezlow: " + rbTree.countRedNodes(rbTree.root));
		System.out.println("Max glebokosc: " + rbTree.maxDepth(rbTree.root));
		System.out.println("Min glebokosc: " + rbTree.minDepth(rbTree.root));
	}
}

package redBlackTree;

public class Main {
	public static void main(String[] args) {
		Tree rbTree = new Tree();
		rbTree.insert(new Node(38));
		rbTree.insert(new Node(31));
		rbTree.insert(new Node(22));
		rbTree.insert(new Node(8));
		rbTree.insert(new Node(20));
		rbTree.insert(new Node(5));
		rbTree.insert(new Node(10));
		rbTree.insert(new Node(9));
		rbTree.insert(new Node(21));
		rbTree.insert(new Node(27));
		rbTree.insert(new Node(29));
		rbTree.insert(new Node(25));
		rbTree.insert(new Node(28));
		System.out.println("Drzewo (inorder): ");
		rbTree.print(rbTree.getRoot());
		System.out.println("Ilosc czerwonych wezlow: " + rbTree.countRedNodes(rbTree.getRoot()));
		System.out.println("Max glebokosc: " + rbTree.maxDepth(rbTree.getRoot()));
		System.out.println("Min glebokosc: " + rbTree.minDepth(rbTree.getRoot()));
	}
}

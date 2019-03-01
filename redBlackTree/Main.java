public class Main {
	public static void main(String[] args) {
		Tree rbTree = new Tree(new Node(38));
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
		System.out.println("-------------------------------");
		rbTree.print(rbTree.getRoot());
		rbTree.countRedNodes(rbTree.getRoot());
		System.out.println("\nIlosc czerwonych wezlow: " + rbTree.getRedNodes());
		System.out.println("Max glebokosc: " + rbTree.maxDepth(rbTree.getRoot()));
		System.out.println("Min glebokosc: " + rbTree.minDepth(rbTree.getRoot()));
		System.out.println("-------------------------------");
		rbTree.delete(new Node(5));
		rbTree.delete(new Node(38));
		rbTree.delete(new Node(8));
		rbTree.delete(new Node(10));
		rbTree.delete(new Node(22));
		rbTree.delete(new Node(20));
		rbTree.delete(new Node(29));
		rbTree.delete(new Node(31));
		rbTree.print(rbTree.getRoot());
		rbTree.setRedNodes(0);
		rbTree.countRedNodes(rbTree.getRoot());
		System.out.println("\nIlosc czerwonych wezlow: " + rbTree.getRedNodes());
		System.out.println("Max glebokosc: " + rbTree.maxDepth(rbTree.getRoot()));
		System.out.println("Min glebokosc: " + rbTree.minDepth(rbTree.getRoot()));
		System.out.println("-------------------------------");
	}
}

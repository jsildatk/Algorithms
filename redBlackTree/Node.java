public class Node {
	int key = -1;
	int color = Tree.getBlack();
	Node left = Tree.getNil();
	Node right = Tree.getNil();
	Node parent = Tree.getNil();
	
	public Node(int key) {
		this.key = key;
	}
}

package redBlackTree;

public class Tree {
	private int redNodes = 0;
	private static final int RED = 0;
	private static final int BLACK = 1;
	private final static Node nil = new Node(-1); // null node
	private Node root = nil;
	
	public Node getRoot() {
		return this.root;
	}
	
	public static int getBlack() {
		return BLACK;
	}
	
	public static Node getNil() {
		return nil;
	}
	
	public void print(Node node) { // inorder
		String temp;
		if (node == nil) {
			return;
		}
		print(node.left);
		if (node.color == RED) {
			temp = "R";
		} else {
			temp = "B";
		}
		System.out.println(node.key + " " + temp);
		print(node.right);
	}
	
	public int countRedNodes(Node node) { // go through tree and count red nodes
		if (node != nil) {
			if (node.color == RED) {
				this.redNodes++;
			}
			countRedNodes(node.left);
			countRedNodes(node.right);
		}
		return this.redNodes;
	}
	
	public int maxDepth(Node node) { // count depth on left and right and return max
		if (node == nil) {
			return 0;
		}
		int leftDepth = maxDepth(node.left);
		int rightDepth = maxDepth(node.right);
		return Math.max(leftDepth, rightDepth)+1;
	}
	
	public int minDepth(Node node) { // return min
		if (node == nil) {
			return 0;
		}
		int leftDepth = minDepth(node.left);
		int rightDepth = minDepth(node.right);
		return Math.min(leftDepth, rightDepth)+1;
	}
	
	public void insert(Node node) {
		Node y = root;
		if (root == nil) { // if tree is empty node becomes root
			root = node;
			node.color = BLACK;
			node.parent = nil;
		} else {
			node.color = RED; // nodes to be inserted are red at start
			while (true) {
				if (node.key < y.key) { // go to the left subtree
					if (y.left == nil) { // if null leaf, attach node
						y.left = node;
						node.parent = y;
						break;
					} else {
						y = y.left; // forward to the left subtree
					}
				} else { // else go to the right subtree
					if (y.right == nil) {
						y.right = node;
						node.parent = y;
						break;
					} else {
						y = y.right;
					}
				}
			}
			fixViolation(node); // fix tree after insertion
		}
	}
	
	private void fixViolation(Node node) {
		while (node.parent.color == RED) {
			Node uncle = nil;
			if (node.parent == node.parent.parent.left) {
				uncle = node.parent.parent.right; // parent is on the left - uncle is on the right
				if (uncle != nil && uncle.color == RED) { // case1: if uncle is red flip colors of nodes
					node.parent.color = BLACK;
					uncle.color = BLACK;
					node.parent.parent.color = RED;
					node = node.parent.parent; // set node to grandparent
					continue;
				}
				if (node == node.parent.right) { // case2: right child - rotate by parent
					node = node.parent;
					rotateLeft(node);
				}
				node.parent.color = BLACK; // case3: left child - rotate by grandparent and flip colors
				node.parent.parent.color = RED;
				rotateRight(node.parent.parent); 
			} else {
				uncle = node.parent.parent.left;
				if (uncle != nil && uncle.color == RED) { // case1.2
					node.parent.color = BLACK;
					uncle.color = BLACK;
					node.parent.parent.color = RED;
					node = node.parent.parent;
					continue;
				}
				if (node == node.parent.left) { // case 2.2: left child - rotate by parent
					node = node.parent;
					rotateRight(node);
				}
				node.parent.color = BLACK; // case 3.2: right child - rotate by grandparent and flip colors
				node.parent.parent.color = RED;
				rotateLeft(node.parent.parent);
			}
		}
		root.color = BLACK; // root has to be black at the end
	}
	
	private void rotateLeft(Node node) {
		if (node.parent != nil) { // if its not root
			if (node == node.parent.left) {
				node.parent.left = node.right;
			} else {
				node.parent.right = node.right;
			}
			node.right.parent = node.parent;
			node.parent = node.right;
			if (node.right.left != nil) {
				node.right.left.parent = node;
			}
			node.right = node.right.left;
			node.parent.left = node;
		} else { // Rotate root
			Node temp = root.right;
			root.right = temp.left;
			temp.left.parent = root;
			root.parent = temp;
			temp.left = root;
			temp.parent = nil;
			root = temp;
		}
	}
	
	private void rotateRight(Node node) {
		if (node.parent != nil) { // if its not root
			if (node == node.parent.left) {
				node.parent.left = node.left;
			} else {
				node.parent.right = node.left;
			}
			node.left.parent = node.parent;
			node.parent = node.left;
			if (node.left.right != nil) {
				node.left.right.parent = node;
			}
			node.left = node.left.right;
			node.parent.right = node;
		} else { // Rotate root
			Node temp = root.left;
			root.left = root.left.right;
			temp.right.parent = root;
			root.parent = temp;
			temp.right = root;
			temp.parent = nil;
			root = temp;
		}
	}
}

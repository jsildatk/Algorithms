package redBlackTree;

public class Tree {
	public class Node {
		int key = -1;
		int color = BLACK;
		Node left = nil;
		Node right = nil;
		Node parent = nil;
		
		public Node(int key) {
			this.key = key;
		}
	}
	private static int redNodes = 0;
	private static int leftDepth = 0;
	private static int rightDepth = 0;
	private static final int RED = 0;
	private static final int BLACK = 1;
	private final Node nil = new Node(-1);
	public Node root = nil;
	
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
	
	public int countRedNodes(Node node) {
		if (node != nil) {
			if (node.color == RED) {
				redNodes++;
			}
			countRedNodes(node.left);
			countRedNodes(node.right);
		}
		return redNodes;
	}
	
	public int maxDepth(Node node) {
		if (node == nil) {
			return 0;
		}
		leftDepth = maxDepth(node.left);
		rightDepth = maxDepth(node.right);
		if (leftDepth > rightDepth) {
			return leftDepth+1;
		} else {
			return rightDepth+1;
		}
	}
	
	public int minDepth(Node node) {
		if (node == nil) {
			return 0;
		}
		leftDepth = minDepth(node.left);
		rightDepth = minDepth(node.right);
		if (leftDepth < rightDepth) {
			return leftDepth+1;
		} else {
			return rightDepth+1;
		}
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
					if (y.left == nil) { // if phantom leaf attach node
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
			fixViolation(node); // fix tree
		}
	}
	
	private void fixViolation(Node node) {
		while (node.parent.color == RED) {
			Node uncle = nil;
			if (node.parent == node.parent.parent.left) {
				uncle = node.parent.parent.right;
				if (uncle != nil && uncle.color == RED) { // case1: if uncle is red flip colors of nodes
					node.parent.color = BLACK;
					uncle.color = BLACK;
					node.parent.parent.color = RED;
					node = node.parent.parent;
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
				uncle = node.parent.parent.right;
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
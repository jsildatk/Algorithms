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
	
	public int getRedNodes() {
		return this.redNodes;
	}
	
	public void setRedNodes(int redNodes) {
		this.redNodes = redNodes;
	}
	
	public Tree() {}
	
	public Tree(Node root) {
		this.root = root;
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
		System.out.print(node.key + temp + " ");
		print(node.right);
	}
	
	public void countRedNodes(Node node) { // go through tree and count red nodes
		if (node != nil) {
			if (node.color == RED) {
				this.redNodes++;
			}
			countRedNodes(node.left);
			countRedNodes(node.right);
		}
	}
	
	public int maxDepth(Node node) { // count depth on left and right and return max
		if (node == nil) {
			return 0;
		}
		int leftDepth = maxDepth(node.left);
		int rightDepth = maxDepth(node.right);
		return Math.max(leftDepth, rightDepth)+1;
	}
	
	public int minDepth(Node node) { // return minimum
		if (node == nil) {
			return 0;
		}
		int leftDepth = minDepth(node.left);
		int rightDepth = minDepth(node.right);
		return Math.min(leftDepth, rightDepth)+1;
	}
	
	public void insert(Node node) {
		Node y = root;
		if (root == nil) { // if tree is empty => node becomes root
			root = node;
			node.color = BLACK;
			node.parent = nil;
		} else {
			node.color = RED; // nodes to be inserted are red at start
			while (true) {
				if (node.key < y.key) { // go to the left subtree
					if (y.left == nil) { // if null leaf => attach node
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
			fixInsert(node); // fix tree after insertion
		}
	}
	
	public boolean delete(Node node) {
		node = findNode(node, this.root);
		if (node == null) {
			return false; // node was not found
		}
	    Node x;
	    Node y = node; // temporary reference y
	    int originalColor = y.color;
	    if (node.left == nil) { // if node has no left child => swap with right child
	    	x = node.right;
	        swap(node, node.right);  
	    } else if (node.right == nil) { // symmetrical
	        x = node.left;
	        swap(node, node.left); 
	    } else { // if node has both children => find minimum node in right subtree and swap
	        y = treeMinimum(node.right);
	        originalColor = y.color;
	        x = y.right;
	        if (y.parent == node) {
	        	x.parent = y;
	        } else {
	            swap(y, y.right);
	            y.right = node.right;
	            y.right.parent = y;
	        }
	        swap(node, y);
	        y.left = node.left;
	        y.left.parent = y;
	        y.color = node.color; 
	    }
	    if(originalColor == BLACK) { // if color is black we need to fix the tree
	        fixDelete(x);  
	    }
	    return true; // node was deleted
	}
	
	private void fixDelete(Node node) {
		Node sibling = nil;
	    while (node != root && node.color == BLACK) { // while entire tree is not fixed
	    	if (node == node.parent.left) { // if node is left child
	    		sibling = node.parent.right; // sibling is right child
	            if (sibling.color == RED) { // case 1: sibling is red
	            	node.color = BLACK;
	                node.parent.color = RED;
	                rotateLeft(node.parent);
	                sibling = node.parent.right;
	            }
	            if (sibling.left.color == BLACK && sibling.right.color == BLACK) { // case 2: both sibling's children are black
	            	sibling.color = RED;
	                node = node.parent;
	                continue; // go to the next
	            } else if (sibling.right.color == BLACK) { // case 3: right sibling's child is black
	            	sibling.left.color = BLACK;
	                sibling.color = RED;
	                rotateRight(sibling);
	                sibling = node.parent.right;
	            }
	            if (sibling.right.color == RED) { // case 4: right sibling's child is red
	                sibling.color = node.parent.color;
	                node.parent.color = BLACK;
	                sibling.right.color = BLACK;
	                rotateLeft(node.parent);
	                node = root;
	            }
	    	} else { // if node is right child
	    		sibling = node.parent.left; // sibling is left child
	            if (sibling.color == RED) { // case 1.2
	                sibling.color = BLACK;
	                node.parent.color = RED;
	                rotateRight(node.parent);
	                sibling = node.parent.left;
	            }
	            if (sibling.right.color == BLACK && sibling.left.color == BLACK) { // case 2.2
	                sibling.color = RED;
	                node = node.parent;
	                continue;
	            } else if (sibling.left.color == BLACK) { // case 3.2
	                sibling.right.color = BLACK;
	                sibling.color = RED;
	                rotateLeft(sibling);
	                sibling = node.parent.left;
	            }
	            if (sibling.left.color == RED) { // case 4.2
	                sibling.color = node.parent.color;
	                node.parent.color = BLACK;
	                sibling.left.color = BLACK;
	                rotateRight(node.parent);
	                node = root;
	            }
	        }
	    }
	    node.color = BLACK; // node has to be black
	}

	private void fixInsert(Node node) {
		Node uncle = nil;
		while (node.parent.color == RED) {
			if (node.parent == node.parent.parent.left) {
				uncle = node.parent.parent.right; // parent is on the left - uncle is on the right
				if (uncle != nil && uncle.color == RED) { // case1: if uncle is red flip colors of nodes
					node.parent.color = BLACK;
					uncle.color = BLACK;
					node.parent.parent.color = RED;
					node = node.parent.parent; // set node to grandparent
					continue; // go to the next
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
  
	private void swap(Node x, Node y) {
		if (x.parent == nil) { // if x has no parent => y becomes root
			root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.parent = x.parent; // link nodes
	}
  
	private Node treeMinimum(Node node) { // find minimum value in tree
		while (node.left != nil) {
			node = node.left;
		}
		return node;
	}
  
	private Node findNode(Node x, Node node) { // find node x in tree and return it
		if (root == nil) { // if tree is empty 
			return null;
		}
		if (x.key < node.key) {
			if (node.left != nil) {
				return findNode(x, node.left);
			}
		} else if (x.key > node.key) {
			if (node.right != nil) {
				return findNode(x, node.right);
			}
		} else if (x.key == node.key) {
			return node;
		}
		return null; // node was not found
	}
}

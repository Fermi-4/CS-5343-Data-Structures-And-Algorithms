import java.util.LinkedList;
import java.util.Queue;

/*
 *  We have discussed Binary Search Trees.(BST)
	Write a program to implement a delete operation from BST.
	You will have to write the program to insert nodes in the BST also (we already did the algorithm in detail in the class for insert).
	Insert the following nodes in the order mentioned here.
	40, 60, 20, 80, 50, 10, 30, 15, 5, 35, 25, 45, 55, 70, 90, 32, 33, 48, 46
	Do an inorder traversal.  
	make screen shot
	Now delete 40 (you decide predecessor or successor).
	Do inorder traversal again.
	Make screen shot
	Now delete 20
	Do inroder traversal
	make screen shot.
	Submit the code.
	Submit the screen shots.
 */
public class Main {

	static int[] numbers = { 40, 60, 20, 80, 50, 10, 30, 15, 5, 35, 25, 45, 55, 70, 90, 32, 33, 48, 46 };

	public static void main(String[] args) {
		BST bst = new BST();

		for (int n : numbers) {
			bst.insert(n);
		}
		System.out.print("Inorder Traversal: ");
		printInOrderTraversal(bst.getRoot());
		System.out.println("");
//		printBFS(bst.getRoot());
		String msg = bst.delete(40, "successor") ? "Successfully deleted..." : "Error deleting...";
		System.out.println(msg);
		printInOrderTraversal(bst.getRoot());
	}

	static void printInOrderTraversal(Node node) {
		if (node == null)
			return;
		printInOrderTraversal(node.getLeft());
		System.out.print(node.toString() + " ");
		printInOrderTraversal(node.getRight());
	}

	static void printBFS(Node root) {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		Node current;
		int count = 1;
		double level = Math.floor(Math.log10(count) / Math.log10(2));
		double prevLevel = -1;
		while (!queue.isEmpty()) {
			level = Math.floor(Math.log10(count) / Math.log10(2));
			if (prevLevel != level) {
				System.out.print("\ndepth " + (int) level + ":");
				prevLevel = level;
			}
			current = queue.remove();
			System.out.print(" " + current.getData());

			if (current.getLeft() != null) {
				queue.add(current.getLeft());
			}
			if (current.getRight() != null) {
				queue.add(current.getRight());
			}
			count++;
		}
	}
}

class BST {

	Node root;

	boolean insert(int v) {
		// check root
		if (root == null) {
			this.root = new Node(v);
			return true;
		}
		return insert(root, v);
	}

	private boolean insert(Node n, int v) {

		if (n.getData() < v) {
			if (n.getRight() == null) {
				n.setRight(new Node(v, n));
				return true;
			} else {
				return insert(n.getRight(), v);
			}
		}

		if (n.getData() > v) {
			if (n.getLeft() == null) {
				n.setLeft(new Node(v, n));
				return true;
			} else {
				return insert(n.getLeft(), v);
			}
		}

		return false;
	}

	boolean delete(int v, String mode) {
		Node nd = getNode(root, v);
		if (nd == null)
			return false;

		if (mode == "successor") {
			Node successor = getSuccessor(nd);
			nd.getRight().setParent(successor);
			nd.getLeft().setParent(successor);
			successor.setRight(nd.getRight());
			successor.setLeft(nd.getLeft());
			successor.getParent().setLeft(successor.getRight());
			System.out.println("successor: " + successor.toString());
			if(nd == root) root = successor;
			return true;
		} else if (mode == "predecessor") {
			Node pred = getPredecessor(nd);
			System.out.println("predecessor: " + pred.toString());
			
			if(nd == root) root = pred;
			return true;
		} else {
			return false;
		}

	}

	private Node getNode(Node n, int v) {
		if (v == n.getData())
			return n;
		if (v > n.getData() && n.getRight() != null)
			return getNode(n.getRight(), v);
		if (v < n.getData() && n.getLeft() != null)
			return getNode(n.getLeft(), v);
		return null;
	}

	// smallest on the right
	private Node getSuccessor(Node root) {
		return findMin(root.getRight());
	}

	private Node findMin(Node n) {
		if (n.getLeft() != null)
			return findMin(n.getLeft());
		return n;
	}

	// largest on the left
	private Node getPredecessor(Node root) {
		return findMax(root.getLeft());
	}

	private Node findMax(Node n) {
		if (n.getRight() != null)
			return findMax(n.getRight());
		return n;
	}

	Node getRoot() {
		return root;
	}

}

/*
 * Node class def
 */
class Node {
	final int data;
	Node left, right, parent;

	public Node(int data) {
		super();
		this.data = data;
	}

	public Node(int data, Node parent) {
		super();
		this.data = data;
		this.parent = parent;
	}

	public Node(int data, Node left, Node right, Node parent) {
		super();
		this.data = data;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public int getData() {
		return data;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "" + data;
	}
}

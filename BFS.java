import java.util.*;

class BFS {
	public static class Node {
		int data;
		Node left;
		Node right;
		Node next;

		Node(int data) {
			this.data = data;
			left = right = null;
		}
	}

	/**
	 * Binary Tree Level Order Traversal
	 * 
	 * Given a binary tree, populate an array to represent its level-by-level
	 * traversal. You should populate the values of all nodes of each level from
	 * left to right in separate sub-arrays.
	 * 
	 * [[1],[2,3],[4,5,6,7]]
	 * 
	 **/

	public static List<List<Integer>> traverse(Node root) {
		List<List<Integer>> result = new ArrayList<>();
		if (root == null) {
			return result;
		}
		Queue<Node> q = new LinkedList<>();
		q.offer(root);
		q.offer(null);
		List<Integer> levelList = new ArrayList<>();
		while (!q.isEmpty()) {
			Node node = q.poll();
			if (node != null) {
				levelList.add(node.data);
				if (node.left != null) {
					q.offer(node.left);
				}
				if (node.right != null) {
					q.offer(node.right);
				}
			} else {
				result.add(levelList);
				levelList = new ArrayList<>();
				if (!q.isEmpty()) {
					q.offer(null);
				}
			}
		}
		return result;
	}

	/**
	 * Reverse Level Order Traversal
	 * 
	 * Given a binary tree, populate an array to represent its level-by-level
	 * traversal in reverse order, i.e., the lowest level comes first. You should
	 * populate the values of all nodes in each level from left to right in separate
	 * sub-arrays
	 * 
	 * 
	 * 
	 * [[4,5,6,7],[2,3],[1]]
	 * 
	 ***/

	public static List<List<Integer>> traverseReverse(Node root) {
		List<List<Integer>> result = new ArrayList<>();
		if (root == null) {
			return result;
		}
		Queue<Node> q = new LinkedList<>();
		q.offer(root);
		q.offer(null);
		Stack<Integer> s = new Stack<>();
		while (!q.isEmpty()) {
			Node node = q.poll();
			if (node != null) {
				s.add(node.data);
				if (node.left != null) {
					q.offer(node.left);
				}
				if (node.right != null) {
					q.offer(node.right);
				}
			} else {
				result.add(0, s);
				s = new Stack<>();
				if (!q.isEmpty()) {
					q.offer(null);
				}
			}
		}
		return result;
	}

	/**
	 * Zigzag Traversal
	 * 
	 * Given a binary tree, populate an array to represent its zigzag level order
	 * traversal. You should populate the values of all nodes of the first level
	 * from left to right, then right to left for the next level and keep
	 * alternating in the same manner for the following levels.
	 * 
	 * [[1],[3, 2],[4, 5, 6, 7]]
	 * 
	 **/

	public static List<List<Integer>> traverseZigzag(Node root) {
		List<List<Integer>> result = new ArrayList<>();
		if (root == null) {
			return result;
		}
		Queue<Node> q = new LinkedList<>();
		q.offer(root);
		q.offer(null);
		List<Integer> temp = new ArrayList<>();
		boolean zigzag = false;
		while (!q.isEmpty()) {
			Node node = q.poll();
			if (node != null) {
				if (zigzag) {
					temp.add(0, node.data);
				} else {
					temp.add(node.data);
				}
				if (node.left != null) {
					q.offer(node.left);
				}
				if (node.right != null) {
					q.offer(node.right);
				}
			} else {
				result.add(temp);
				temp = new ArrayList<>();
				if (!q.isEmpty()) {
					q.offer(null);
					zigzag = !zigzag;
				}
			}
		}
		return result;
	}

	/**
	 * Level Averages in a Binary Tree
	 * 
	 * Given a binary tree, populate an array to represent the averages of all of
	 * its levels.
	 * 
	 * [1, 2.5, 5.5]
	 * 
	 **/

	public static List<Double> findLevelAverages(Node root) {
		List<Double> result = new ArrayList<>();
		if (root == null) {
			return result;
		}
		Queue<Node> q = new LinkedList<>();
		q.offer(root);
		while (!q.isEmpty()) {
			int size = q.size();
			double levelSum = 0;
			for (int i = 0; i < size; i++) {
				Node node = q.poll();
				levelSum += node.data;
				if (node.left != null) {
					q.offer(node.left);
				}
				if (node.right != null) {
					q.offer(node.right);
				}
			}
			double findLevelAverage = levelSum / size;
			result.add(findLevelAverage);
		}
		return result;
	}

	/**
	 * 
	 * Minimum Depth of a Binary Tree
	 * 
	 * Find the minimum depth of a binary tree. The minimum depth is the number of
	 * nodes along the shortest path from the root node to the nearest leaf node
	 **/

	public static int findDepth(Node root) {
		if (root != null && root.left == null && root.right == null) {
			return 0;
		}
		return Math.max(1 + findDepth(root.left), 1 + findDepth(root.right));
	}

	/**
	 * Level Order Successor
	 * 
	 * Given a binary tree and a node, find the level order successor of the given
	 * node in the tree. The level order successor is the node that appears right
	 * after the given node in the level order traversal
	 * 
	 **/

	public static Node findSuccessor(Node root, int key) {
		if (root == null) {
			return root;
		}
		Queue<Node> q = new LinkedList<>();
		q.offer(root);

		while (!q.isEmpty()) {
			Node node = q.poll();
			if (node.left != null) {
				q.offer(node.left);
			}
			if (node.right != null) {
				q.offer(node.right);
			}
			if (node.val == key) {
				return q.poll();
			}
		}
		return null;
	}

	/**
	 * Connect Level Order Siblings
	 * 
	 * Given a binary tree, connect each node with its level order successor. The
	 * last node of each level should point to a null node.
	 ***/

	public static void connect(Node root) {
		if (root == null) {
			return;
		}
		Queue<Node> q = new LinkedList<>();
		q.offer(root);
		while (!q.isEmpty()) {
			int n = q.size();
			Node prvNode = null;
			for (int i = 0; i < n; i++) {
				Node node = q.poll();
				if (prvNode != null) {
					prvNode.next = node;
				}
				prvNode = node;
				if (node.left != null) {
					q.offer(node.left);
				}
				if (node.right != null) {
					q.offer(node.right);
				}
			}
		}
	}

	/**
	 * Connect All Level Order Siblings
	 * 
	 * Given a binary tree, connect each node with its level order successor. The
	 * last node of each level should point to the first node of the next level.
	 * 
	 * 
	 * Traversal using 'next' pointer: 12 7 1 9 10 5
	 **/

	public static void connectLevelOrderSiblings(Node root) {
		Queue<Node> q = new LinkedList<>();
		q.offer(root);

		List<Node> list = new ArrayList<>();
		while (!q.isEmpty()) {
			Node node = q.poll();
			list.add(node);
			if (node.left != null) {
				q.offer(node.left);
			}
			if (node.right != null) {
				q.offer(node.right);
			}
		}
		for (int i = 0; i < list.size() - 1; i++) {
			Node node = list.get(i);
			node.next = list.get(i + 1);
		}
	}

	/**
	 * Right View of a Binary Tree
	 * 
	 * Given a binary tree, return an array containing nodes in its right view. The
	 * right view of a binary tree is the set of nodes visible when the tree is seen
	 * from the right side
	 * 
	 ***/

	public static void updateList(List<Node> result, Node root) {

		if (root == null) {
			return;
		}
		System.out.println(root.val);
		if (root != null && root.next == null) {
			result.add(root);
		}
		updateList(result, root.right);
		updateList(result, root.left);

	}

	public static List<Node> traverseLeftView(Node root) {
		List<Node> result = new ArrayList<>();
		Queue<Node> q = new LinkedList<>();
		q.offer(root);
		while (!q.isEmpty()) {
			int n = q.size();
			for (int i = 0; i < n; i++) {
				Node node = q.poll();
				if (node != null) {
					node.next = q.peek();
					if (node.left != null) {
						q.offer(node.left);
					}
					if (node.right != null) {
						q.offer(node.right);
					}
				}
			}
			if (!q.isEmpty()) {
				q.offer(null);
			}
		}
		updateList(result, root);
		return result;
	}

	/**
	 * 
	 * Problem 1: Given a binary tree, return an array containing nodes in its left
	 * view. The left view of a binary tree is the set of nodes visible when the
	 * tree is seen from the left side.
	 **/

	public static void leftView(Node root) {
		if (root == null) {
			return;
		}
		Queue<Node> q = new LinkedList<>();
		q.offer(root);
		List<Integer> leftViewList = new Arraylist<>();
		while (!q.isEmpty()) {
			int n = q.size();
			for (int i = 0; i < n; i++) {
				Node node = a.poll();
				if (i == 0) {
					leftViewList.add(node.data);
				}
				if (node.left != null) {
					q.offer(node.left);
				}
				if (node.right != null) {
					q.offer(node.right);
				}
			}
		}
		for (Integer integer : leftViewList) {
			System.out.println(integer + " ");
		}
	}

	/**
	 * Tree Boundary
	 * 
	 * Given a binary tree, return an array containing all the boundary nodes of the
	 * tree in an anti-clockwise direction.
	 * 
	 * The boundary of a tree contains all nodes in the left view, all leaves, and
	 * all nodes in the right view. Please note that there should not be any
	 * duplicate nodes. For example, the root is only included in the left view;
	 * similarly, if a level has only one node we should include it in the left
	 * view.
	 ***/
	public static List<Node> findBoundary(Node root) {
		List<Node> result = new ArrayList<>();
		List<Node> leftView = new ArrayList<>();
		List<Node> rightView = new ArrayList<>();
		List<Node> leafView = new ArrayList<>();
		Queue<Node> q = new LinkedList<>();
		q.offer(root);

		while (!q.isEmpty()) {
			int n = q.size();
			for (int i = 0; i < n; i++) {
				Node node = q.poll();
				if (i > 0 && i < n - 1 && node.left == null && node.right == null) {
					leafView.add(node);
				}
				if (i == 0) {
					leftView.add(node);
				}
				if (i == n - 1 && i != 0) {
					rightView.add(node);
				}
				if (node.left != null) {
					q.offer(node.left);
				}
				if (node.right != null) {
					q.offer(node.right);
				}
			}
		}
		result.addAll(leftView);
		for (int i = leafView.size() - 1; i >= 0; i--) {
			result.add(leafView.get(i));
		}
		for (int i = rightView.size() - 1; i >= 0; i--) {
			result.add(rightView.get(i));
		}
		return result;
	}

	public static void main(String[] args) {
		Node root = new Node(12);
		root.left = new Node(7);
		root.right = new Node(1);
		root.left.left = new Node(9);
		root.left.right = new Node(2);
		root.right.left = new Node(10);
		root.right.right = new Node(5);
		// List<List<Integer>> list = traverse(root);
		// List<List<Integer>> list = traverseReverse(root);
		// List<List<Integer>> list = traverseZigzag(root);
		// List<Double> list = findLevelAverages(root);
		// for (Double double1 : list) {
		// System.out.println(double1);
		// }
		int minDepth = findDepth(root);
		System.out.println(minDepth);
		// print(list);
	}

	public static void print(List<List<Integer>> list) {
		for (List<Integer> list2 : list) {
			System.out.println(" ");
			for (Integer value : list2) {
				System.out.print(value + " ");
			}
		}
	}
}
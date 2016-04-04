package analysis;

import java.util.*;

class node {
	int value;
	node left;
	node right;

	public node(int v) {
		this.value = v;
		this.left = null;
		this.right = null;
	}
}

class tree {
	node root;

	public tree() {
		root = null;
	}

	public tree(node n) {
		root = n;
	}

	public void add(int v) {
		node newNode = new node(v);

		if (root == null) {
			root = newNode;
			return;
		}
		if (root.value >= v) {
			if (root.left == null)
				root.left = newNode;
			else
				new tree(root.left).add(v);
		} else {
			if (root.right == null)
				root.right = newNode;
			else
				new tree(root.right).add(v);
		}
	}

	public tree remove(int v) {
		if (root == null)
			return this;

		if (root.value > v && root.left != null)
			root.left = (new tree(root.left).remove(v)).root;

		else if (root.value < v && root.right != null)
			root.right = (new tree(root.right).remove(v)).root;

		else if (root.value == v) {

			if (root.left == null && root.right == null)
				return (new tree(null));

			if (root.left == null)
				return (new tree(root.right));

			if (root.right == null)
				return (new tree(root.left));

			root.value = new tree(root.right).getSuccessor();
			root.right = (new tree(root.right).remove(root.value)).root;
		}
		return this;
	}

	public int getSuccessor() {
		if (root.left == null)
			return root.value;
		return (new tree(root.left).getSuccessor());
	}

	public void printInorder() {
		if (this.root == null)
			return;
		new tree(root.left).printInorder();
		System.out.print(root.value + ", ");
		new tree(root.right).printInorder();
	}

	public void printLevel(int i, int k) {
		if (root == null)
			return;
		if (i == k) {
			System.out.print(root.value + ", ");
			return;
		}
		if (i > k)
			return;
		new tree(root.left).printLevel(i + 1, k);
		new tree(root.right).printLevel(i + 1, k);
	}

	private int max(int i, int j) {
		return ((i >= j) ? i : j);
	}

	public int getDepth() {
		if (root == null)
			return 0;

		return 1 + max((new tree(root.left).getDepth()), (new tree(root.right).getDepth()));
	}

	public temp diameter() {
		if (root == null)
			return new temp(0, 0);

		temp l = new tree(root.left).diameter();
		temp r = new tree(root.right).diameter();

		int m = l.chain + r.chain + 1;
		if (l.dia >= m || r.dia >= m) {
			if (l.dia >= r.dia)
				return new temp(1 + max(l.chain, r.chain), l.dia);
			return new temp(1 + max(l.chain, r.chain), r.dia);
		}
		return new temp(1 + max(l.chain, r.chain), m);
	}

	public void levelOrder() {
		int n = getDepth();

		int i = 0;
		for (i = 0; i < n; i++) {
			this.printLevel(0, i);
			System.out.println();
		}
	}

	public int returnMin() {
		if (root == null)
			return 0;
		if (root.left == null)
			return root.value;
		return (new tree(root.left).returnMin());
	}

	public boolean equals(tree t) {
		if (t.root == null && this.root == null)
			return true;
		if (t.root == null)
			return false;
		if (this.root == null)
			return false;
		if (t.root.value == this.root.value)
			return (new tree(root.left).equals(new tree(t.root.left))
					&& new tree(root.right).equals(new tree(t.root.right)));

		return false;
	}

	public int getLca(int x, int y) {
		if (root == null)
			return -1;
		if (x == root.value)
			return x;
		if (y == root.value)
			return y;
		if (x < root.value && y < root.value)
			return (new tree(root.left).getLca(x, y));
		if (x > root.value && y > root.value)
			return (new tree(root.right).getLca(x, y));
		return root.value;
	}

	public int getSuccessor(int v, int p) {
		if (root == null)
			return -1;
		if (root.value < v)
			return new tree(root.right).getSuccessor(v, p);
		if (root.value > v && root.left == null)
			return root.value;
		if (root.value > v)
			return new tree(root.left).getSuccessor(v, root.value);
		if (root.value == v) {
			if (root.right == null)
				return p;
			return new tree(root.right).getSuccessor(v, v);
		}
		return -1;
	}

	public static int count = 0;

	public static int smallk(tree t, int k) {
		if (t.root == null)
			return -1;

		if (t.root.left == null) {
			count++;
			if (t.root.right == null || count == k)
				return t.root.value;
			return (smallk(new tree(t.root.right), k));
		}

		int m = smallk(new tree(t.root.left), k);
		if (count == k)
			return m;
		count++;
		// System.out.print(t.root.value+"->"+count+", ");
		if (count == k)
			return t.root.value;
		return (smallk(new tree(t.root.right), k));
	}
	
	
}

class temp {
	int chain;
	int dia;

	public temp(int x, int y) {
		chain = x;
		dia = y;
	}
}

public class practice {

	public static void main(String args[]) {

		// setPractice();
		// mapPractice();
		// myqueuePractice();
		treePractice();
	}

	public static void treePractice() {
		tree t = new tree();
		tree t1 = new tree();

		t.add(50);
		t.add(30);
		t.add(35);
		t.add(67);
		t.add(54);
		t.add(38);
		t.add(27);
		t.add(29);
		t.add(20);
		t.add(25);

		System.out.println(tree.smallk(t, 10));

		t.printInorder();
		System.out.println();

		t.add(45);
		t.add(43);

		// t.levelOrder();
		// t.printLevel(0, 2);
		// System.out.println();

		t = t.remove(30);
		t = t.remove(38);
		t = t.remove(35);
		// t.printInorder();
		// System.out.println();
	}

	public static void myqueuePractice() {
		myQueue<String> q = new myQueue<String>(3);

		q.add("Yo!!");
		System.out.println(">>>" + q.element());

		q.add("dude");
		q.add("Fuck");

		System.out.println(">>>" + q.element());

		try {
			q.add("Yeah");
		} catch (Exception e) {
			// e.printStackTrace(System.out);
		}

		for (String e : q)
			System.out.println(e);

		q.remove();
		for (String e : q)
			System.out.print(e + ", ");
		System.out.println();

		q.remove();
		for (String e : q)
			System.out.print(e + ", ");
		System.out.println();
		System.out.println(">>>" + q.element());

		q.remove();
		for (String e : q)
			System.out.print(e + ", ");
		System.out.println();

		try {
			q.element();
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}

	public static void mapPractice() {
		Map<Integer, String> map = new HashMap<Integer, String>();

		map.put(1, "ONE");
		map.put(2, "TWO");
		map.put(3, "THREE");
		map.put(4, "FOUR");
		map.put(7, "SEVEN");
		map.put(50, "FIFTY");

		for (Map.Entry<Integer, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}

		map.put(3, "YEAH");
		map.put(14, "THREE");

		System.out.println(map.get(3));

		for (Integer key : map.keySet())
			System.out.println(key + " : " + map.get(key));
	}

	public static void setPractice() {
		Set<String> set1 = new LinkedHashSet<String>();
		Set<String> set2 = new HashSet<String>();

		set1.add("yo");
		set1.add("bruh");
		set1.add("lame");
		set1.add("haha");
		set1.add("dayum");
		set1.add("lame");

		for (String text : set1)
			System.out.println(text);

		set2.add("fuck");
		set2.add("yeah");
		set2.add("this");
		set2.add("haha");
		set2.add("dayum");
		set2.add("lame");
		set2.add("is");

		System.out.println(set2);

		Set<String> set3 = new HashSet<String>();

		set3 = set1;
		System.out.println(set3);

		set3.addAll(set2);
		System.out.println(set3);
	}
}

abstract class A {
	public A(int a) {
		System.out.println("yo fucked up!!");
	}

	static void fun() {
		System.out.println("A.fun()");
	}
}

class B extends A {
	public B() {
		super(15);
	}

	static void fun() {
		System.out.println("B.fun()");
	}
}

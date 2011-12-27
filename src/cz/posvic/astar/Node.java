package cz.posvic.astar;

public class Node {

	private int x, y;
	private int g, h, f;
	private Node parent;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
		this.parent = null;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getF() {
		return f;
	}

	public void setF(int f) {
		this.f = f;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public String toString() {
		return x + "x" + y;
	}

	public boolean equals(Object o) {

		if (o instanceof Node) {
			if (x == ((Node) o).x && y == ((Node) o).y) {
				return true;
			}
		}

		return false;
	}
}

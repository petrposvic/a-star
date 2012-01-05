package cz.posvic.astar;

import java.util.LinkedList;
import java.util.List;

public class AStar {

	private static final int BLOCK = 0;

	private static int heuristic(Node beg, Node end) {
		int distX = end.getX() - beg.getX();
		int distY = end.getY() - beg.getY();
		return distX * distX + distY * distY;
	}

	private static Node findBestNode(List<Node> list) {
		Node best = null;

		for (Node node : list) {
			if (best == null) {
				best = node;
			} else

			if (node.getF() < best.getF()) {
				best = node;
			}
		}

		return best;
	}

	public static List<Node> findPath(int[][] map, Node beg, Node end) {

		List<Node> opened = new LinkedList<Node>();
		List<Node> closed = new LinkedList<Node>();

		beg.setG(0);
		beg.setH(heuristic(beg, end));
		beg.setF(beg.getG() + beg.getH());

		if (map[beg.getY()][beg.getX()] != BLOCK) {
			opened.add(beg);
		}

		while (!opened.isEmpty()) {

			// Node with the lowest f
			Node actualNode = findBestNode(opened);

			// Finish, build a path from beg to end
			if (actualNode.equals(end)) {

				LinkedList<Node> ret = new LinkedList<Node>();
				Node node = actualNode.getParent();
				while (node != null) {
					ret.addFirst(node);
					node = node.getParent();
				}

				ret.add(end);
				return ret;
			}

			opened.remove(actualNode);
			closed.add(actualNode);

			// Neighbors of actual node
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {

					// Actual is neighbor
					if (i == 0 && j == 0) {
						continue;
					}

					int x = actualNode.getX() + j;
					int y = actualNode.getY() + i;
					if (x < 0 || y < 0 || x >= map[0].length || y >= map.length) {
						continue;
					}

					Node neighbor = new Node(x, y);

					// Position is blocked
					if (map[y][x] == BLOCK) {
						continue;
					}

					// Neigbor is already closed
					if (closed.contains(neighbor)) {
						continue;
					}

					int tentativeG = actualNode.getG() + heuristic(actualNode, neighbor);
					boolean tentativeIsBetter = false;

					if (!opened.contains(neighbor)) {
						opened.add(neighbor);
						tentativeIsBetter = true;
					} else

					if (tentativeG < neighbor.getG()) {
						tentativeIsBetter = true;
					}

					if (tentativeIsBetter) {
						neighbor.setParent(actualNode);
						neighbor.setG(tentativeG);
						neighbor.setH(heuristic(neighbor, end));
						neighbor.setF(neighbor.getG() + neighbor.getH());
					}
				}
			}
		}

		return new LinkedList<Node>();
	}
}

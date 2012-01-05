package cz.posvic.astar.example;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cz.posvic.astar.AStar;
import cz.posvic.astar.Node;

public class Example extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new Example();
	}

	private final int CELL = 50;
	private int[][] map;

	private Node beginNode;
	private List<Node> path;

	public Example() {

		map = new int[5][10];
		addMouseListener(this);

		JFrame frame = new JFrame("A* example");
		frame.setSize(640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(this);
		frame.setVisible(true);
	}

	public void paint(Graphics g) {
		int w = getWidth();
		int h = getHeight();

		// Background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, w, h);

		// Grid
		g.setColor(Color.YELLOW);
		for (int i = 0; i <= map[0].length; i++) {
			g.drawLine(i * CELL, 0, i * CELL, map.length * CELL);
		}
		for (int i = 0; i <= map.length; i++) {
			g.drawLine(0, i * CELL, map[0].length * CELL, i * CELL);
		}

		// Blocks
		g.setColor(Color.RED);
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] == 0) {
					g.fillRect(j * CELL + 1, i * CELL + 1, CELL - 1, CELL - 1);
				}
			}
		}

		// Begin node
		if (beginNode != null) {
			g.setColor(Color.GREEN);
			g.fillRect(beginNode.getX() * CELL + 1, beginNode.getY() * CELL + 1, CELL - 1, CELL - 1);
		}

		// Path
		if (path != null) {
			g.setColor(Color.LIGHT_GRAY);
			for (Node node : path) {
				g.fillRect(node.getX() * CELL + 1, node.getY() * CELL + 1, CELL - 1, CELL - 1);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		int x = event.getX() / CELL;
		int y = event.getY() / CELL;

		if (x < 0 || y < 0 || x >= map[0].length || y >= map.length) {
			return;
		}

		switch (event.getButton()) {

		// Left mouse button find path
		case 1:
			if (beginNode == null) {
				path = null;
				beginNode = new Node(x, y);
			} else

			{
				path = AStar.findPath(map, beginNode, new Node(x, y));
				beginNode = null;
				
				// Print path to standard output
				StringBuffer sb = new StringBuffer();
				for (Node node : path) {
					sb.append(node);
					sb.append(" -> ");
				}
				System.out.println(sb.substring(0, sb.length() - 4));
			}
			break;

		// Right mouse button edit map
		case 3:
			path = null;
			map[y][x] = (map[y][x] + 1) % 2;
			break;
		}

		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}

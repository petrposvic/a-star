package cz.posvic.astar.test;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import cz.posvic.astar.AStar;
import cz.posvic.astar.Node;

public class MainTest {

	int[][] clearMap = new int[8][8];
	int[][] blockedMap = new int[8][8];
	int[][] labyrintMap = { { 1, 0, 0, 1, 0 }, { 1, 0, 1, 0, 1 }, { 1, 0, 0, 0, 1 }, { 0, 1, 1, 1, 0 } };
	int[][] twoWaysMap = { { 1, 0, 0, 1, 0 }, { 1, 0, 1, 0, 1 }, { 1, 0, 1, 0, 1 }, { 0, 1, 1, 1, 0 } };

	@Before
	public void setMap() {
		for (int i = 0; i < clearMap.length; i++) {
			Arrays.fill(clearMap[i], 1);
		}

		for (int i = 0; i < blockedMap.length; i++) {
			Arrays.fill(blockedMap[i], 0);
		}
	}

	@Test
	public void testHorizontal() {
		List<Node> path = AStar.findPath(clearMap, 0, true, new Node(0, 0), new Node(7, 0));
		Assert.assertEquals(7, path.size());
	}

	@Test
	public void testVertical() {
		List<Node> path = AStar.findPath(clearMap, 0, true, new Node(0, 0), new Node(0, 7));
		Assert.assertEquals(7, path.size());
	}

	@Test
	public void testDiagonal() {
		List<Node> path = AStar.findPath(clearMap, 0, true, new Node(0, 0), new Node(7, 7));
		Assert.assertEquals(7, path.size());
	}

	@Test
	public void testNoPath() {
		List<Node> path = AStar.findPath(blockedMap, 0, true, new Node(0, 0), new Node(7, 7));
		Assert.assertEquals(0, path.size());
	}

	@Test
	public void testLabyrint() {
		List<Node> path1 = AStar.findPath(labyrintMap, 0, true, new Node(0, 0), new Node(2, 1));
		Assert.assertEquals(9, path1.size());

		List<Node> path2 = AStar.findPath(labyrintMap, 0, true, new Node(0, 0), new Node(2, 1));
		for (Node n : path2) {
			System.out.print(n + " ");
		}
		System.out.println();
	}

	@Test
	public void testTwoWays() {
		List<Node> path1 = AStar.findPath(twoWaysMap, 0, true, new Node(0, 0), new Node(2, 1));
		Assert.assertEquals(5, path1.size());

		List<Node> path2 = AStar.findPath(twoWaysMap, 0, true, new Node(0, 0), new Node(2, 1));
		for (Node n : path2) {
			System.out.print(n + " ");
		}
		System.out.println();
	}

}

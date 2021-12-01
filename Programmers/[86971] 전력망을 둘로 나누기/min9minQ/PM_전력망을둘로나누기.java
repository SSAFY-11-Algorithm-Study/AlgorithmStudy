package time18;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class PM_전력망을둘로나누기 {

	static private int[][] wires = { { 1, 3 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 4, 6 }, { 4, 7 }, { 7, 8 }, { 7, 9 } };
	static int n = 9;
	static ArrayList<Integer>[] list;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) {

		list = new ArrayList[n+1];
		for (int i = 0; i <= n; i++) {
			list[i] = new ArrayList<>();
		}
		// 연결 리스트
		for (int[] wire : wires) {
			list[wire[0]].add(wire[1]);
			list[wire[1]].add(wire[0]);
		}
		//
		for (int[] wire : wires) {
			int count1 = bfs(wire[0], wire[1], n);
			int count2 = bfs(wire[1], wire[0], n);
			
			answer = Math.min(answer, Math.abs(count1 - count2));
		}
		
		System.out.println(answer);

	}

	// bfs로 count 하기
	// a -> b 연결 끊기 위해서 a와 b를 가는 경우를 조건으로 넣어주기 
	static public int bfs(int a, int b, int n) {
		Queue<Integer> que = new LinkedList<>();
		boolean[] visited = new boolean[n+1];
		int count = 0;

		que.add(a);
		visited[a] = true;

		while (!que.isEmpty()) {
			int temp = que.poll();
			count++;

			for (int node : list[temp]) {
				if (node != b && !visited[node]) {
					que.add(node);
					visited[node] = true;
				}
			}
		}

		return count;
	}
}

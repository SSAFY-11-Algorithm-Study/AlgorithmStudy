package time16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BJ1967_트리의지름 {

	public static class Node {
		int idx;
		int cost;

		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}

	static ArrayList<Node> list[];
	static int n;
	static int max = 0;
	static boolean visited[];
	static int node = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		list = new ArrayList[n + 1];

		for (int i = 0; i <= n; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < n - 1; i++) {
			String[] str = br.readLine().split(" ");
			int parent = Integer.parseInt(str[0]);
			int child = Integer.parseInt(str[1]);
			int cost = Integer.parseInt(str[2]);
			list[parent].add(new Node(child, cost));
			list[child].add(new Node(parent, cost));
		}

		visited = new boolean[n + 1];
		visited[1] = true;
		dfs(1, 0);

		visited = new boolean[n + 1];
		visited[node] = true;
		dfs(node, 0);
		System.out.println(max);

	}

	public static void dfs(int x, int weight) {
		if (weight > max) {
			max = weight;
			node = x;
		}
		visited[x] = true;

		for (int i = 0; i < list[x].size(); i++) {
			Node n = list[x].get(i);
			if (visited[n.idx] == false) {
				dfs(n.idx, n.cost + weight);
				visited[n.idx] = true;
			}
		}
	}
}

// 예제는 답이 다르게 나오고
// -> 생각해보니 예제 답은 1-4-5 나와도 상관 x 
// 제출 했을 때는 시간 초과 ㅠㅠ...
package time20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ11779_최소비용구하기2 {

	private static class Node implements Comparable<Node> {
		int i;
		int dis;

		public Node(int i, int dis) {
			this.i = i;
			this.dis = dis;
		}

		public int compareTo(Node node) {
			return this.dis - node.dis;
		}
	}

	private static int n, m, start, end;
	private static int INF = 50000000;
	private static ArrayList<Node>[] list;
	private static ArrayList<Integer> path = new ArrayList<>();
	private static int[] dist, route;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		list = new ArrayList[n + 1];
		dist = new int[n + 1];
		for (int i = 1; i < n + 1; i++) {
			list[i] = new ArrayList<>();
		}
		Arrays.fill(dist, INF);

		// 돌아다녔던 길.
		route = new int[n + 1];
		// 입력
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			list[s].add(new Node(e, d));
		}

		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		dijkstra();
	}

	private static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		dist[start] = 0;
		route[start] = 0;

		while (!pq.isEmpty()) {
			int index = pq.poll().i;

			for (Node node : list[index]) {
				if (dist[node.i] > dist[index] + node.dis) {
					dist[node.i] = dist[index] + node.dis;
					pq.add(new Node(node.i, dist[node.i]));
					System.out.println("i : "+index);
					route[node.i] = index;
				}
			}
		}

		// 루트 표시하기 위해 역추적
		int temp = end;
		while (temp != 0) {
			path.add(temp);
			temp = route[temp];
		}

		System.out.println(dist[end]);
		System.out.println(path.size());
		for (int i = path.size() - 1; i >= 0; i--) {
			System.out.print(path.get(i) + " ");
		}

	}

}

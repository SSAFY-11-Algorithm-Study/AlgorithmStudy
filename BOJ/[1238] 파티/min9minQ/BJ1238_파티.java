// 돌아갈 때는 X에서 한점에서 다익스트라를 하면 되지만
// 파티에 갈 때는 모든 점에서 X로 가는 다익스트라를 다 구해야 된다..?
// -> distance 배열에 거꾸로 저장해서 2번에서 각 점으로 가는 다익스트라 한번만 적용하면 구할 수 있음.
package time20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1238_파티 {
	
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
	
	private static int N, M, X;
	private static ArrayList<List<Node>> list, reverse;
	private static int INF = 50000000;
	private static int[] distance, rdistance;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>();
		reverse = new ArrayList<>();
		
		for(int i = 0 ; i < N+1; i++) {
			list.add(new ArrayList<>());
			reverse.add(new ArrayList<>());
		}
		
		distance = new int[N + 1];
        rdistance = new int[N + 1];
        Arrays.fill(distance, INF);
        Arrays.fill(rdistance, INF);
        
        // 정상 dis 값과 역으로 된 dis 값 입력.
        for (int i = 1; i < M+1; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());
 
            list.get(start).add(new Node(end, dis));
            reverse.get(end).add(new Node(start, dis));
        }
        
        dijkstra(list, distance, X);
        dijkstra(reverse, rdistance, X);
        
        int ans = -1;
        for (int i = 1; i < N+1; i++) {
            ans = Math.max(ans, distance[i] + rdistance[i]);
        }
        System.out.println(ans);
        
	}

	private static void dijkstra(ArrayList<List<Node>> list, int[] distance, int X) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(X, 0));
 
        distance[X] = 0;
 
        while (!pq.isEmpty()) {
            int idx = pq.poll().i;
 
            for (Node node : list.get(idx)) {
                if (distance[node.i] > distance[idx] + node.dis) {
                    distance[node.i] = distance[idx] + node.dis;
                    pq.add(new Node(node.i, distance[node.i]));
                }
            }
        }
	}

}

package time19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ4396_별자리만들기 {
	
	private static class Node implements Comparable<Node>{
		int s;
	    int e;
	    double weight;
		
	    public Node(int s, int e, double weight) {
	        this.s = s;
	        this.e = e;
	        this.weight = weight;
	    }
		
	    @Override
	    public int compareTo(Node e) {
	        return Double.compare(this.weight, e.weight); //가중치 오름차순
	    }
	}
	
	private static int n;
	private static int[] parents;//크루스칼 이용
	private static double[][] stars;// 별좌표
	private static PriorityQueue<Node> pq = new PriorityQueue<>(); // sort 안해주려고 pq사용
	private static double result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		stars = new double[n][2];
		parents = new int[n];
		
		//좌표 입력
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			stars[i][0] = Double.parseDouble(st.nextToken());
			stars[i][1] = Double.parseDouble(st.nextToken());
		}
		
		//입력된 좌표를 바탕으로 거리 게산후 pq에 넣어주기 
		for (int i = 0; i < n - 1; i++) {			
            for (int j = i + 1; j < n; j++) {
                double x1 = stars[i][0];
                double y1 = stars[i][1];
                double x2 = stars[j][0];
                double y2 = stars[j][1];
				
                double weight = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
                pq.add(new Node(i, j, weight));
            }
         }
		
		make();
		//크루스칼
		while (!pq.isEmpty()) {
            Node node = pq.poll();
			
            int rootS = find(node.s);
            int rootE = find(node.e);
			
            // 부모 노드가 같지 않을 경우 union
            if (rootS != rootE) {
                union(rootS, rootE);	
                result += node.weight;
            }
        }
		
		System.out.println(String.format("%.2f", result));
	}
	
	private static void make() {
		for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
	}
	
	private static int find(int a) {
		if(a == parents[a])
			return a;

		return parents[a] = find(parents[a]);
	}
	//여기서 union boolean 말고 void로 해보기.
	private static void union(int a, int b) {
		a = find(a);
		b = find(b);

		parents[b] = a;
	}

}

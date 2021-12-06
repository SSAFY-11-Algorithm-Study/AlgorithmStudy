package week19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
 * [다익스트라 푸는 순서]
 * 1. 노드 클래스를 설정 + compareTo 설정
 * 2. 인접리스트와 거리배열 선언
 * 3. 거리배열은 INF로 해두고 출발점만 0
 * 4. list[start].add(new Node(vertex,weight)); 이렇게 인접리스트에 출발, 도착, 가중치 추가
 * 5. 다익스트라 돌리기 - 가중치 비교해서 갱신
 * 6. 다익스트라 돌린후 거리배열을 확인.
 */
public class BOJ1753_최단경로 {
	private static class Node implements Comparable<Node> { //우선순위큐로 성능개선(안하면 시간초과뜸)
        int vertex;
        int weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        //Arrays.fill 정렬 기준을 무게로 미리 설정
        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
        }
    }
	
	static ArrayList<Node>[] list;
	static int V,E;
	static int K;
	static int[] distance; //거리배열 필요(시작점 기준)
    static int INF = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//출발지 고정이고 V가 커서 플로이드워셜 말고 다익스트라 사용
		V=sc.nextInt();
		E=sc.nextInt();
		K=sc.nextInt();
		
		
		list = new ArrayList[V + 1]; //정점 인접리스트
        distance = new int[V + 1]; //시작점과 다른 정점간의 최단경로
        for (int i = 1; i <= V; i++) {
            list[i] = new ArrayList<>();
        }
        
        //초기화
        Arrays.fill(distance, INF);
        distance[K] = 0;
        
		for(int i = 0 ; i < E ; i ++) {
			int start = sc.nextInt();
			int vertex = sc.nextInt();
			int weight = sc.nextInt();
			
			//노드정보는 인접리스트에 담아두기(배열은 값이 너무커서 터짐)
			list[start].add(new Node(vertex,weight));
		}
		
		dijkstra();
		
        for (int i = 1; i <= V; i++) {
            if (distance[i] == INF) {
                System.out.println("INF");
            } else {
                System.out.println(distance[i]);
            }
        }

	}
	private static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(K,0)); // 시작점
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			
			int vertex = node.vertex;
			int weight = node.weight;
			
			//현재 가중치가 거리배열에 있는 값보다 크면 갱신 x
			if(distance[vertex] < weight)
				continue;
			
			//현재 정점에서 연결된것들을 인접리스트에서 탐색
			for(int i = 0 ; i < list[vertex].size(); i ++) {
				int vertex2 = list[vertex].get(i).vertex;
				int weight2 = list[vertex].get(i).weight + weight;
				
				if(distance[vertex2] > weight2) {
					distance[vertex2] = weight2;
					pq.add(new Node(vertex2, weight2));
				}
			}
		}
		
	}

}

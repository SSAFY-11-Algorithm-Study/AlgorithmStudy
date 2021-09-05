package week6;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ1916_최소비용구하기 {
	static int [][] map ;
	static boolean [] visited;
	static int [] distance;
	static int N,M;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N+1][N+1];
		visited = new boolean[N+1];
		distance = new int[N+1];
		
		//인접배열에 각 버스비용 담기
		for(int i = 0 ; i < M; i++) {
			int sNode = sc.nextInt();
			int eNode = sc.nextInt();
			int value = sc.nextInt();
			
			//아직 값이 없으면 일단 넣음
			if(map[sNode][eNode] == 0) {
				map[sNode][eNode] = value;
			}
			//같은 곳으로 가는 버스의 비용은 기존보다 작은 경우에만 받음
			if(map[sNode][eNode] > value && map[sNode][eNode] != 0) {
				map[sNode][eNode] = value;
			}
		}
		
		//출발점 도착점
		int start = sc.nextInt();
		int end = sc.nextInt();
		
		//거리배열은 일단 최대값으로
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		//시작점은 거리 0
		distance[start] = 0;
		
		int min=0, current=0;
		for(int i=1; i<=N; ++i){	
			
			min = Integer.MAX_VALUE;  // 최소값을 받기 위해 일단 정수 최대값으로 시작
			for(int j=1; j<=N; ++j){   //모든 정점 갯수만큼 체크
				//아직 방문하지 않았고 , j번째 정점까지의 거리가 min 보다 작으면
				if(!visited[j] && distance[j] < min){
					min = distance[j];   //최소값을 최신화
					current = j;   //현재 이동할 정점
				}
			}
			visited[current] = true; //현재 이동한 정점을 방문체크
			if(current == end){  //목표지점까지 왔다면 끝
				break;
			}			
			
			for(int j=1; j<= N; ++j){
				// 현재 이동한 정점을 기준으로 연결되지 않은 타 정점과의 간선 비용을 최소로 업데이트
				if(!visited[j] && map[current][j] != 0 // 아직 연결안되어 있고, 연결가능한곳
						&&  distance[j] > min+map[current][j]){ // 다른 정점에서 가는게 더 짧다면
					distance[j] = min+map[current][j];// 거리 배열 을 업데이트
				}
			}
		}
		

		System.out.println(distance[end]);
	}
}

// 최소비용 알고리즘  : 프림, 크루스칼, 다익스트라
// 시작에서 끝까지 모든 간선을 연결하는 프림 크루스칼과 달리
// 시작에서 각 노드까지의 최소 비용을 구해주는 다익스트라 선택.

// 예제는 맞는데 도저히 채점결과가 틀렸다고 나옵니다.. ㅠㅠ결국 못 찾았네요...
package time6;

import java.util.Arrays;
import java.util.Scanner;

public class BJ1916_mincost {
	
	static int N,M;
	static int[][] map;
	static boolean visited[];
	static int[] distance;
	static int start, end;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		map = new int[N+1][N+1];
		visited = new boolean[N+1];
		distance = new int[N+1];
		
		for(int i = 0; i < M; i++){
			int num1 = sc.nextInt();
			int num2 = sc.nextInt();
			int cost = sc.nextInt();
			/* 혹시나 해서 추가 같은 경로 가중치가 다시들어올 때
			if(map[num1][num2] == 0) 
				map[num1][num2] = cost;
			else if(map[num1][num2] > cost) 
				map[num1][num2] = cost;
			*/
		}

		start= sc.nextInt();
		end = sc.nextInt();
		
		Arrays.fill(distance, Integer.MAX_VALUE); // distance 배열 모두 INFINITY 상수 할당.
		distance[start] = 0; //첫번째에서 첫번쨰로 가는 가중치 0 설정.
		
		for(int i=0; i<N; i++){ // 모든 정점 개수만큼 체크
			int current = 0;
			int min = Integer.MAX_VALUE; //min을 INFINITY로 설정(Integer 중 가장 큰 값.) 
			for(int j=1; j<N+1; j++){ // V만큼 반복. (현재까지 방문한 값들 중 최솟값 선택)
				if(!visited[j] && distance[j] < min){ //아직 방문 안했고, 출발점 ~j 까지 오는 최소 거리 < min
					min = distance[j]; //최소 거리
					current = j; //정점 번호
				}
			}
			visited[current] = true;  // visited배열에 방문했다고 설정.
					
			// 선택된 정점(current)을 경유지로 삼아 갈 수 있는 다른 방문하지 않은 정점들 거리 업데이트
			for(int c=1; c<N+1; c++){ //V번 반복.
				if(!visited[c] && map[current][c] != 0 // 방문하지 않았고, current노드에서 c 노드로의 가중치가 0이 아니며, 
						&&  distance[c] > min+map[current][c]){ // 저장된 가중치보다 이전까지의 최소가중치+현재의 가중치가 작으면
					distance[c] = min+map[current][c]; // 저장된 가중치를 더 작은 값으로 업데이트
				}
			}
		}
		System.out.println(distance[end]);		// 최소값 출력.
	}

}

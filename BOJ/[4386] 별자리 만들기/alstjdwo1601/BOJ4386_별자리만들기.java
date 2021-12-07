package week19;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 모든 정점을 포함하는 최소가중치는 최소스패닝트리
 * 크루스칼은 간선정보 , 프림은 정점정보기준
 */
public class BOJ4386_별자리만들기 {
	static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		//별 좌표 담기
		double [][] coordi = new double[N][2];
		for(int i = 0 ; i < N; i ++) {
			double x = sc.nextDouble();
			double y = sc.nextDouble();

			coordi[i][0] = x;
			coordi[i][1] = y;
		}

		//prim 알고리즘에서 쓸 인접배열 생성
		double [][] dist = new double[N][N];
		for(int i = 0 ; i < N; i ++) {
			for(int j = 0; j < N ; j ++) {
				//별 좌표에서 거리계산 후 인접배열에 넣기
				dist[i][j] = Math.sqrt(Math.pow(Math.abs(coordi[i][0] - coordi[j][0]), 2) 
						+ Math.pow(Math.abs(coordi[i][1] - coordi[j][1]), 2));
			}
		}

		/*  인접배열 출력
		for(int i = 0 ; i < N; i ++) {
			System.out.println();
			for(int j = 0; j < N ; j ++) {
				System.out.print(String.format("%.2f", dist[i][j]) + " ");
			}
		}
		 */

		// 해당 정점이 방문되었었는지 체크하는 배열 , mst에 포함여부 체크
		boolean [] visited = new boolean[N];

		// 각 정점 별 다른 정점과의 연결 간선중 최소비용 
		// ex) minEdge[3] = 5; 3번 정점과 최소간선비용은 누군진 몰라도 5임
		double [] minEdge = new double [N];

		Arrays.fill(minEdge, Double.MAX_VALUE); // 미리 최대값으로 초기화
		minEdge[0] = 0;

		double min, result = 0.0;
		int minVertex = -1;
		for (int i = 0; i < N; i++) {// 모든 정점 수만큼 반복

			//최소간선비용 선택할거라서. 초기값으로 정수의 최대치를 주고 시작
			min = Double.MAX_VALUE;//최소 간선 비용값 
			minVertex = 0;// 최소 비용의 정점 번호
			
			//MST에 연결되지 않은 정점중 minEdge 비용이 최소인 정점 찾기
			for(int j = 0 ; j < N; j++) {
				if(!visited[j] && min > minEdge[j]) {
					min = minEdge[j];
					minVertex = j;
				}
			}
			
			//minVertex 를 MST에 포함
			result += min;
			visited[minVertex] = true;
			
			//연결안되어있고, minVertex기준으로 연결된 값이 적으면 갱신한다
			for (int j = 0; j < N; j++) { 
                if (!visited[j] && dist[minVertex][j] != 0 &&  minEdge[j] > dist[minVertex][j]  ) {
                	minEdge[j] = dist[minVertex][j];
                }
            }
		}
		System.out.print(String.format("%.2f", result));
	}	
}

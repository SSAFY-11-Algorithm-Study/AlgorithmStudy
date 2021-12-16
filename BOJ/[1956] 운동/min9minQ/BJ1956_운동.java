package time20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1956_운동 {

	private static int V, E;
	private static int INF = 50000000;
	private static int[][] distance;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		distance = new int[V + 1][V + 1];

		for (int i = 1; i < V + 1; i++) {
			for (int j = 1; j < V + 1; j++) {
				distance[i][j] = INF;
			}
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int dis = Integer.parseInt(st.nextToken());

			distance[a][b] = dis;
		}
		
		//플로이드 와샬
		for (int k = 1; k < V + 1; k++) {
			for (int i = 1; i < V + 1; i++) {
				for (int j = 1; j < V + 1; j++) {
					if (distance[i][j] > distance[i][k] + distance[k][j]) {
						distance[i][j] = distance[i][k] + distance[k][j];
					}
				}
			}
		}
		// INF가 아닌 수로 되어있으면 사이클
		int answer = INF;
        for (int i = 1; i < V+1; i++) {
            for (int j = 1; j < V+1; j++) {
                if (distance[i][j] != INF && distance[j][i] != INF) {
                    answer = Math.min(answer, distance[i][j] + distance[j][i]);
                }
            }
        }
        
        if(answer == INF) {
        	System.out.println(-1);
        } else{
        	System.out.println(answer);
        }
	}

}

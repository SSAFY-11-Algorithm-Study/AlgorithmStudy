package time19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ11404_플로이드 {
	
	private static int n; // n개의 도시
    private static int m; //m개의 버스
    private static int[][] distance; // 최소비용

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		distance = new int[n+1][n+1];
		
		for(int i = 1; i < n+1; i++) {
			for(int j = 1; j < n+1; j++) {
				if(i == j) 
					distance[i][j] = 0;
				else
					distance[i][j] = 50000000;
			}
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			distance[a][b] = Math.min(distance[a][b], c);
		}
		
		// 플로이드 와샬
		for (int k = 1; k < n+1; k++) { 
            for (int i = 1; i < n+1; i++) { 
                for (int j = 1; j < n+1; j++) { 
                    distance[i][j] = Math.min(distance[i][k] + distance[k][j], distance[i][j]); //최단경로 초기화
                }
            }
        }
		
		StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (distance[i][j] >= 50000000) {
                    sb.append("0 ");
                } else {
                    sb.append(distance[i][j] + " ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
		
	}

}

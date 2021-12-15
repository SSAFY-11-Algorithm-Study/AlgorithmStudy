package week20;

import java.util.ArrayList;
import java.util.Scanner;

//https://steady-coding.tistory.com/101
/*
 * 사이클에 대한 개념을 위에서 참고함
 */
public class BOJ1956_운동 {
	
	static int V,E;
	static int [][] map;
	static int INF = 987654321;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		V = sc.nextInt();
		E = sc.nextInt();
		
		//인접배열 초기화
		map = new int[V+1][V+1];
		for(int i = 1 ; i <= V ; i ++) {
			for(int j = 1 ; j <=V ; j++) {
				map[i][j] = INF;
				
				if(i==j)
					map[i][j] = 0;
			}
		}
		
		//간선정보 받아서 배열에 저장
		for(int i = 0 ; i < E ; i ++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			int weight = sc.nextInt();
			
			map[start][end] = weight;
		}
		
		
		//플로이드와샬
		for(int k = 1 ; k <= V ; k++) {
			for(int i = 1 ; i <= V ; i++) {
				for(int j = 1 ; j <= V ; j++) {
					if(i==j)
						continue;
					
					if(map[i][j] > map[i][k] + map[k][j])
						map[i][j] = map[i][k] + map[k][j];
				}
			}
		}
		
		int answer = INF;
		
		for(int i = 1 ; i <= V ; i++) {
			for(int j = 1 ; j <= V ; j++) {
				//자신을 제외하고
				if(i==j)
					continue;
				
				
				// 두 정점이 서로 가는 경로가 있으면 사이클이 존재
				// a->b , b->a 의 경로가  존재하는가(INF가 아닌가)
				if(map[i][j] != INF && map[j][i] !=INF)
					answer = Math.min(answer, map[i][j] + map[j][i]);
			}
		}
		
		if(answer ==INF)
			System.out.println(-1);
		else System.out.println(answer);
		
	}
}

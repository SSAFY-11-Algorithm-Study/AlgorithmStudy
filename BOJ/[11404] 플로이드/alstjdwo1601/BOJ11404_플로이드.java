package week19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/*
 * 모든 도시의 쌍에 대해 최솟값을 다 구해야하고, n이 별로 안커서 플로이드
 */
public class BOJ11404_플로이드 {
	
	static int N,M;
	static int INF = 10000001; //왠진 모르겠는데 Integer.MAX_VALUE 하니까 값 이상함
	static int [][] map ;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		//맵 정보 초기화(전부 INF , But i==j 이면  0)
		map = new int[N+1][N+1];
		for(int i = 1 ; i <= N ; i++) {
			for(int j = 1 ; j <= N ; j++) {
				map[i][j] = INF;
				
				if(i==j) 
					map[i][j] = 0;	
			}
		}
		
		//간선정보 받기
		for(int i = 0 ; i < M ; i ++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			int weight = sc.nextInt();
			
			//중복으로 1 4 1 , 1 4 2 가 주어지면 1 4 1 을 선택해야됨
			map[start][end] = Math.min(weight, map[start][end]);
		}
		
		//플로이도 와샬 돌리기
		for(int k = 1 ; k <= N ; k++) {
			for(int i = 1 ; i <= N; i ++) {
				for(int j = 1; j <=N ; j++) {
					if(map[i][j] > map[i][k] + map[k][j])
						map[i][j] = map[i][k] + map[k][j];
				}
			}
		}
		
		for(int i = 1 ; i <= N; i ++) {
			for(int j = 1; j <=N ; j++) {
				if(map[i][j] == INF)
					map[i][j] = 0;
				
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

}

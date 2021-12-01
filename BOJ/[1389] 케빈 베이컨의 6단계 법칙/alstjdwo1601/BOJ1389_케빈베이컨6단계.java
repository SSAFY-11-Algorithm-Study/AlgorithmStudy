package week18;

import java.util.Scanner;

public class BOJ1389_케빈베이컨6단계 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int network [][] = new int[N+1][N+1];
		for(int i = 0 ; i < M ; i ++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			
			network[start][end] = 1;
			network[end][start] = 1;
		}
		
		
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N +1; j++) {
            	//본인이 본인한테 가는건 없다고 생각
                if (i ==j) 
                	network[i][j] = 0;
                //나머지 칸은 최대값으로 초기화
                else 
                	network[i][j] = Integer.MAX_VALUE;
            }
        }
        
        //플로이드 워셜
        for (int k = 1; k < N+1; k++) { //거쳐가는 중간 지점 노드
            for (int i = 1; i < N+1; i++) { //시작 노드
                for (int j = 1; j < N+1; j++) { //도착 노드
                    network[i][j] = Math.min(network[i][k] + network[k][j], network[i][j]); //최단경로 초기화
                }
            }
        }
        
        int[] answer = new int[N + 1];
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            int sum = 0;
            for (int j = 1; j <= N; j++) {
                sum += network[i][j];
            }
            answer[i] = sum;
            if (sum < min) {
                min = sum;
            }
        }
        for (int i = 1; i <= N; i++) {
            if (answer[i] == min) {
                System.out.println(i);
                return;
            }
        }
	}
	


}

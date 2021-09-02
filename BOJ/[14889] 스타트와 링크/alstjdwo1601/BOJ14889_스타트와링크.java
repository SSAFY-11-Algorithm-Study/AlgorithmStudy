package week6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BOJ14889_스타트와링크 {

	static int N;
	static int [][] team;
	static int answer = Integer.MAX_VALUE;
	static boolean [] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		team = new int[N][N];

		visited = new boolean[N];   //방문체크겸 조합뽑음

		for(int i = 0 ; i <N; i ++) {
			for(int j = 0 ; j < N ; j++)
				team[i][j] = sc.nextInt();
		}

		comb(0,0);
		System.out.println(answer);
	}

	//조합으로 가능한 팀의 경우의 수 뽑아보기
	private static void comb(int depth, int start ) {
		if(depth == N/2) {
			//System.out.println(Arrays.toString(visited));
			
			getScore();
			return;
		}

		for(int i = start ; i <N ; i ++) {
			if(!visited[i]) {
				visited[i] = true;
				comb(depth+1, i+1);
				visited[i] = false;
			}
		}
	}

	private static void getScore() {
		int teamScore1 = 0;
		int teamScore2 = 0;
		
		//점수 계산
		for(int i = 0 ; i < N-1 ; i ++) {
			for(int j = i+1 ; j < N ; j++) {
				if(visited[i] && visited[j]) {
					teamScore1 += team[i][j];
					teamScore1 += team[j][i];
				}
				else if(!visited[i] && !visited[j]) {
					teamScore2 += team[i][j];
					teamScore2 += team[j][i];
				}
			}
		}
		
		//차이값
		int diff = Math.abs(teamScore1- teamScore2);
		
		//최소값 최신화
		answer = Math.min(diff, answer);
	}
}

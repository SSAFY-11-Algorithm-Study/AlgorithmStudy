package time6;

import java.util.ArrayList;
import java.util.Scanner;

public class BJ14889_startlink {
	static int N;
	static int[][] table;
	static int[] team1;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		team1 = new int[N/2];

		table = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				table[i][j] = sc.nextInt();
			}
		}
		
		comb(0,0);
		System.out.println(min);
		
	}
	static void comb(int cnt, int start) {
		if(cnt == N/2) {
			score();
			return;
		}
		for(int i = start; i < N; i ++) {
			team1[cnt] = i;
			comb(cnt+1,i+1);
		}
	}
	
	static void score() {
		ArrayList<Integer> arr = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			arr.add(i);
		}
		int sum_start = 0;
		int sum_link = 0;
		
		for(int i = 0; i < team1.length-1; i ++ ) {
			for(int j = i+1; j < team1.length; j++) {
				sum_start += table[team1[i]][team1[j]];
				sum_start += table[team1[j]][team1[i]];
			}
		}
		
		for(int i = 0; i < team1.length; i++) {
			arr.remove((Object)team1[i]);
		}
		for(int i = 0; i < arr.size()-1; i++) {
			for(int j = i+1; j < arr.size(); j++) {
				sum_link += table[arr.get(i)][arr.get(j)];
				sum_link += table[arr.get(j)][arr.get(i)];
			}
		}
		
		int result = Math.abs(sum_start - sum_link);
		
		if(min> result) min = result;
	}
}

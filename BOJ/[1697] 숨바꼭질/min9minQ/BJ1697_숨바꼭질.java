// 최단시간 BFS
package time10;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BJ1697_숨바꼭질 {

	static int N,M;
	static int[] time = new int[100001]; // 시간기록 겸 방문체크
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		//Arrays.fill(time, -1);
		bfs();
	}
	private static void bfs() {
		if(N==M) {
			System.out.println(0);
			return;
		}
		Queue<Integer> que = new LinkedList<>();
		que.offer(N);
		time[N] = 1 ;
		
		while(!que.isEmpty()) {
			int temp = que.poll();
			for(int i = 0 ; i < 3 ; i++) {
				int idx;
				if(i == 0) idx = temp - 1;
				else if(i == 1) idx = temp + 1;
				else idx = temp * 2;
				
				if(idx == M) {
					System.out.println(time[temp]);
					return;
				}
				if(idx >= 0 && idx < 100001 && time[idx] == 0) {
					que.offer(idx);
					time[idx] = time[temp] + 1;
				}
					
				
				
			}
		}
		
		
		
		
	}

}

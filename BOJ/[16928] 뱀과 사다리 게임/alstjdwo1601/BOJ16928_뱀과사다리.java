package week9;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//테케는 돌아가는데 틀렸다고 뜹니다 ㅠ

public class BOJ16928_뱀과사다리 {

	static int [] map;
	static int [] dp;
	static int [] start, end;
	static boolean [] visited;
	static int ladder,snake;
	static int answer= 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		map = new int[101];
		dp = new int[101];  //카운팅할때 쓸 메모이제이션 배열
		visited = new boolean[101];
		ladder = sc.nextInt();
		snake = sc.nextInt();

		//뱀과 사다리 지점을 저장할 배열
		start = new int[ladder+snake];
		end = new int[ladder+snake];
		for(int i = 0 ; i < ladder+snake ; i ++) {
			start[i] = sc.nextInt();
			end[i] = sc.nextInt();
		}

		//최단거리니까 일단 bfs
		bfs();
	}

	private static void bfs() {
		Queue<Integer> q = new LinkedList<>();

		//초기 설정
		q.offer(1);
		visited[1] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();

			//주사위 6개만큼
			for(int i = 1 ; i <=6 ; i++) {
				int nx = cur + i;

				if(100 < nx) continue;
				if(visited[nx]) continue;
				visited[nx] = true;
				
				boolean flag = true;
				//1) 다음위치가 뱀이나 사다리가 있는 지점
				for(int j = 0 ; j < start.length; j++) {
					if(start[j] == nx && !visited[end[j]]) {
						visited[end[j]] = true;
						q.add(end[j]);
						dp[end[j]] = dp[cur]+1;
						flag= false;
					}
				}

				//2) 다음위치가 뱀이나 사다리가 없음
				if(flag) {
					q.add(nx);
					dp[nx] = dp[cur]+1;
				}
			}

			//종료조건
			if(cur ==100) {
				System.out.println(dp[cur]);
				return;
			}

		}
	}
}



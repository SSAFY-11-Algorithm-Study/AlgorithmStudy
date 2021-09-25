//1. BFS
//2. 방문체크(다시 원래 자리로 돌아와서 시작하면 최단 경로 x)

package time9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ16928_snakeladder {
	
	static boolean[] visited = new boolean[101];
	static int[] count = new int[101];
	static int[] skill = new int[101];
	static int N,M;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int i = 0 ; i < N + M; i ++) {
			st = new StringTokenizer(br.readLine());
			skill[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());			
		}
		
		bfs();
	}
	
	static void bfs() {
		Queue<Integer> que = new LinkedList<>();
		que.offer(1);
		visited[1] = true;
		
		while(!que.isEmpty()) {
			
			int temp = que.poll();
			
			if(temp == 100) {
				System.out.println(count[100]); // 결과 출력
				return;
			}
			
			for(int i = 1; i <= 6; i++) { //주사위 방향 탐색
				int ntemp = temp + i;
				if(100 < ntemp) continue; 
				if(visited[ntemp]) continue; //이미 방문
				visited[ntemp] = true;
				
				if(skill[ntemp] != 0) {//사다리나 뱀 있으면
					if(!visited[skill[ntemp]]) { // 도착지 체크
						que.offer(skill[ntemp]);
						visited[skill[ntemp]] = true;
						count[skill[ntemp]] = count[temp] + 1; 
					}
				}
				else { // 없으면 그냥 1추가
					que.offer(ntemp);
					count[ntemp] = count[temp] + 1;
				}
			}
			
			
		}
	}

}

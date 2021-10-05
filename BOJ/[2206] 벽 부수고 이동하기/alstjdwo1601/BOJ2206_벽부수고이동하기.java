package week11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


//처음엔 순열로 일일히 하나씩 벽 부수는 식으로 했으나
//시간초과나서 Node에 부순 횟수를 추가하는 방식을 블로그에서 참고했음

public class BOJ2206_벽부수고이동하기 {
	static int [] dx = {1,0,-1,0};
	static int [] dy = {0,-1,0,1};
	static boolean [][][] visited;
	static int [][] map;
	static int N,M;
	static int answer = 0;
	static ArrayList<Integer> answerList;
	static class Node{
		int x ;
		int y ;
		int time;
		int cnt; //부순 횟수

		public Node(int x , int y , int time, int cnt ){
			this.x = x;
			this.y = y;
			this.time = time;
			this.cnt = cnt;
		}
	}


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[N][M];
		visited  = new boolean[N][M][2];
		answerList = new ArrayList<>();

		//맵 받기
		for(int i = 0 ; i<N ; i++) {
			String str = sc.next();
			for(int j = 0 ; j < M ; j++) {
				map[i][j] = Integer.parseInt(str.substring(j,j+1));
			}
		}

		//1. 벽을 하나 부숴놓기
		//breakWall(0);
		bfs();
		answerList.add(answer);

		if(N==1 && M==1)
			System.out.println(1);
		else {
			//여러 answer 중에 양수인게 있다면 그중에서 최소를 뽑고 , 없다면 그대로 -1출력
			boolean flag = false;
			for(int i = 0 ; i < answerList.size(); i++) {
				if(answerList.get(i) > 0) {
					answer = answerList.get(i);
					flag =true;
					answer = Math.min(answer , answerList.get(i));
				}
			}

			if(!flag) 
				System.out.println(-1);
			else
				System.out.println(answer);
		}
	}

	/*
	private static void breakWall(int depth) {
		//벽 하나 부순후에는 bfs로 넘어간다
		if(depth ==1) {

			for(boolean [] b : visited)
				Arrays.fill(b, false);
			//2. bfs로 최단거리 찾기
			bfs();


			return;
		}

		for(int i = 0 ; i <N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				if(map[i][j] ==1) {
					map[i][j] = 0; // 벽 부수기
					breakWall(depth + 1);
					map[i][j] = 1; // 다음 경우의 수를 위해 복구
				}
			}
		}
	}
	 */

	private static void bfs() {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0,0,1,0));  
		visited[0][0][0]= true;
		int count = 0;

		while(!q.isEmpty()) {
			Node node = q.poll();
			answer = node.time;
			count = node.cnt;

			for(int i = 0 ; i < 4; i++){
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];


				//배열안쪽이면서 빈칸이면 
				if(nx>=0 && ny>=0 && nx<N && ny<M){
					//빈칸이면 그냥 계속감
					if(map[nx][ny] == 0 && !visited[nx][ny][count]){
						visited[nx][ny][count] = true;
						q.add(new Node(nx,ny,answer+1,count));
					}
					//벽을만났는데 아직 카운트 0 이면 1로 늘리고 지나감
					else if(map[nx][ny] ==1 && count ==0) {
						visited[nx][ny][count] = true;
						q.add(new Node(nx,ny,answer+1,count+1));
					}
				}
			}
			//마지막지점 도착한경우  브레이크
			if(visited[N-1][M-1][count]){
				answer++;
				break;
			}
		}
		//while문을 break로 나오지않았다면 방문하지못하는 경우임
		if(!visited[N-1][M-1][count])
			answer = -1;
	}

}

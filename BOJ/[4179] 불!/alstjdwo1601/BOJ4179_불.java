package week13;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//테케 되는디  시간초과남.. 시간을 어케줄이지
public class BOJ4179_불 {
	static class Node{
		int x;
		int y;
		int time;

		public Node(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}

	static class FireNode{
		int x;
		int y;

		public FireNode(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N,M;
	static String [][] map;
	static boolean [][] visitedPerson;
	static boolean [][] visitedFire;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		map = new String[N][M];
		visitedPerson = new boolean[N][M];
		visitedFire = new boolean[N][M];

		for(int i = 0 ; i < N ; i++) {
			String str = sc.next();
			for(int j = 0 ; j < M ; j++) {
				map[i] = str.split("");
			}
		}

		bfs();
	}
	private static void bfs() {
		Queue<Node> q = new LinkedList<>();
		Queue<FireNode> fireq = new LinkedList<>();

		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < M ; j ++) {
				//지훈이 위치 큐에 담음
				if(map[i][j].equals("J")) {
					q.offer(new Node(i,j,0));
					visitedPerson[i][j] = true;
				}

				//불 위치 큐에 담음
				else if(map[i][j].equals("F"))
					fireq.offer(new FireNode(i,j));
			}
		}
		
		boolean flag = false;
		while(!q.isEmpty()) {
			//1. 불꽃 확산하기
			int fsize = fireq.size();

			for(int i = 0 ; i < fsize ; i++) {
				FireNode fnode = fireq.poll();

				for(int j = 0 ; j < 4 ; j++) {
					int nx = fnode.x + dx[j];
					int ny = fnode.y + dy[j];

					if(nx>=0 && ny >=0 & nx <N && ny<M) {
						if(!visitedFire[nx][ny] && map[nx][ny].equals(".")) {
							visitedFire[nx][ny] = true;
							map[nx][ny] = "F";
							fireq.add(new FireNode(nx,ny));					
						}
					}
				}
			}

			//2. 지훈이 움직이기
			int qsize = q.size();
			
			for (int a = 0; a < qsize; a++) {
				Node node = q.poll();

				for(int i = 0 ; i < 4; i ++) {
					int nx = node.x + dx[i];
					int ny = node.y + dy[i];
					int ntime = node.time+1;

					//배열 범위 안이면서
					if(nx>=0 && ny >=0 && nx <N && ny<M) {
						//방문 안했고 빈칸이면 이동
						if((!visitedPerson[nx][ny] && map[nx][ny].equals("."))) {
							//System.out.println("무야호");
							
							//배열 가장자리에 도착했다면 다음턴에 탈출가능
							if(nx ==0 || nx ==N-1 || ny==0 || ny== M-1) {
								flag = true;
								System.out.println(ntime+1);
								return;
							}
							
							//가장자리 아니면 다시 큐에 넣고 이동
							else {
								//System.out.println(nx + " " + ny);
								visitedPerson[nx][ny] = true;
								map[nx][ny] = "J";
								map[node.x][node.y] = ".";
								q.add(new Node(nx,ny,ntime));
							}
						}
					}
				}
			}//지훈
			
			/*
			for(int k = 0 ; k< N ; k ++) {
				System.out.println();
				for(int j = 0 ; j < M ; j++) {
					System.out.print(map[k][j] + " ");
				}
			}
			System.out.println();
			*/

		}
		
		
		//탈출 못한거임
		if(!flag) {
			System.out.println("IMPOSSIBLE");
			return;
		}
	}
}

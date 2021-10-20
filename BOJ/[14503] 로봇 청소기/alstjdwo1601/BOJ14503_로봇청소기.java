package week13;

import java.util.Scanner;

public class BOJ14503_로봇청소기 {

	static int N,M,cnt,nextDir;
	static int [][] map;
	static boolean [][] visited;

	static int [] dx = {-1,0,1,0};
	static int [] dy = {0,1,0,-1}; //북 동 남 서
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N= sc.nextInt();
		M=sc.nextInt();

		map= new int[N][M];
		visited= new boolean[N][M];

		//출발좌표 , 방향
		int startR = sc.nextInt();
		int startC = sc.nextInt();
		int startD = sc.nextInt();

		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		cleanRobot(startR,startC,startD);
		System.out.println(cnt);

	}
	private static void cleanRobot(int x, int y, int dir) {
		//1. 현재 위치를 청소한다.(온적 없는 경우에만)
		if(!visited[x][y]) {
			System.out.println(" 청소 : "+ x + " " + y );
			visited[x][y] = true;
			cnt++;
		}

		//2. 방향 왼쪽부터 돌려가면서 청소할곳찾기
		boolean flag = false;
		int originDir = dir;
		for(int i = 0 ; i < 4 ; i++) {
			//2-1 다음 방향을 받음
			int nextDir = (dir+3)%4;

			//2-2 해당 방향의 nx,ny 지점을 확인
			int nx = x+ dx[nextDir];
			int ny = y+ dy[nextDir];

			//2-3 청소 가능한 지점이면 재귀
			if(nx >= 0 && nx < N && ny >= 0 && ny < M && 
					map[nx][ny] == 0 && !visited[nx][ny]) {
				cleanRobot(nx,ny,nextDir);
				flag = true;
				break;
			}
			dir = (dir + 3)%4;
		}

		//3. 위의 for문에서 4방향 돌동안 flag가 false 이면 청소할곳이 없었다
		if(!flag) {
			
			int next_d = (originDir + 2) % 4; //후진방향은 180도 반대쪽
            int backX = x + dx[next_d];
            int nextY = y + dy[next_d];

            if (backX > 0 && nextY > 0 && backX < N && nextY < M) {
                if (map[backX][nextY] ==0) {
                	// 바라보는 방향 유지한 채 후진
                    cleanRobot(backX, nextY, originDir); 
                }
            }
			
		}
	}

}

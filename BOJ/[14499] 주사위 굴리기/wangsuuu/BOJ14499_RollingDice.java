import java.util.Scanner;

public class BOJ14499_RollingDice {

	static int N, M, x, y, K;
	static int[][] map;
	//상-남-동-서-북-하 순서대로, 각 면에 적혀있는 숫자
	static int[] dice_num = new int[6];
	//이동 델타. 동, 서, 북, 남
	static int[] dx = {0, 0, 0, -1, 1};
	static int[] dy = {0, 1, -1, 0, 0};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); //세로
		M = sc.nextInt(); //가로
		x = sc.nextInt(); //주사위 위치 x
		y = sc.nextInt(); //주사위 위치 y
		K = sc.nextInt(); //명령의 갯수
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		int nx = x, ny = y;
		for(int i=0; i<K; i++) { //명령의 수만큼 반복
			int cmd = sc.nextInt();
			nx = nx + dx[cmd];
			ny = ny + dy[cmd];
			
			if(nx<0 || nx>=N || ny<0 || ny>=M) { //범위를 넘는다면
				//다시 nx를 원상복귀
				nx = nx - dx[cmd];
				ny = ny - dy[cmd];
				continue;
			}
			
			//주사위 상태 조정
			if(cmd==1) { //동 -> 남북 빼고 한칸씩 시계방향 이동
				int tmp = dice_num[0];
				dice_num[0] = dice_num[3];
				dice_num[3] = dice_num[5];
				dice_num[5] = dice_num[2];
				dice_num[2] = tmp;
				
			} else if(cmd==2) { //서 -> 남북 빼고 한칸씩 시계 반대방향 이동
				int tmp = dice_num[0];
				dice_num[0] = dice_num[2];
				dice_num[2] = dice_num[5];
				dice_num[5] = dice_num[3];
				dice_num[3] = tmp;
				
			} else if(cmd==3) { //북 -> 동서 빼고 한칸씩 시계 방향 이동
				int tmp = dice_num[0];
				dice_num[0]=dice_num[1];
				dice_num[1] = dice_num[5];
				dice_num[5] = dice_num[4];
				dice_num[4] = tmp;
				
			} else { //남 -> 동서 빼고 한칸씩 시계 반대방향 이동
				int tmp = dice_num[0];
				dice_num[0] = dice_num[4];
				dice_num[4] = dice_num[5];
				dice_num[5] =dice_num[1];
				dice_num[1] = tmp;
			}
			
			if(map[nx][ny] == 0) {
				map[nx][ny] = dice_num[5];
			} else {
				dice_num[5] = map[nx][ny];
				map[nx][ny] = 0;
			}
			System.out.println(dice_num[0]);
		}
	}
}

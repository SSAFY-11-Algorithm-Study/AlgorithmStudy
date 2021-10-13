//로봇은 0칸에서 시작해서 N-1칸까지만 가고, N-1칸에서는 컨베이어 벨트에서 없앤다.

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ20055_ConveyorBelt {

	static int N, K;
	static int[][] belt;
	static boolean[] robot; //로봇이 해당 칸에 있다면 true, 없다면 false
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		belt = new int[2][N];
		robot = new boolean[N];
		
		for (int i = 0; i < 2; i++) {
			if(i==0) {
				for(int j = 0; j < N; j++) 
					belt[i][j] = sc.nextInt();
			} else { //맨 뒤 인덱스부터 차례대로 받아야 함
				for (int j = N-1; j >=0 ; j--)
					belt[i][j] = sc.nextInt();
			}
		}//입력 완료
		
		int level = 1;
		while(true) {
			// 1번
			rotateBelt();
			rotateRobot();
			
			// 2번
			moveRobot();
			
			// 3번
			loadRobot();
			
			// 4번
			int cnt=0;
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < N; j++) {
					if(belt[i][j]==0)
						cnt++;
				}
			}
			if(cnt>=K) break;
			
			level++;
		}
		System.out.println(level);
	}
	
	private static void rotateBelt() { //시계방향으로 회전
		int tmp = belt[0][N-1];
		
		//위쪽 왼->오 이동
		for (int i = N-1; i > 0; i--) {
			belt[0][i] = belt[0][i-1];
		}
		// 왼쪽 아래->위 이동
		belt[0][0] = belt[1][0];
		// 아래쪽 오->왼 이동
		for (int i = 0; i < N-1; i++) {
			belt[1][i] = belt[1][i+1];
		}
		// 오른쪽 위->아래 이동
		belt[1][N-1] = tmp;
	}
	
	private static void rotateRobot() {//한칸 회전하기
		
		for (int i = N-1; i>0 ; i--) {
			robot[i] = robot[i-1];
		}
		
		robot[N-1]=false; //내리는 위치에 로봇이 오게 된다면 즉시 내림
		robot[0] = false;
	}
	
	private static void moveRobot() { //"가장 먼저 벨트에 올라간 로봇부터" 이동 -> 가장 큰 인덱스부터 감소하는 for문!!!
		
		for (int i = N-2; i >= 0; i--) {
			if(robot[i]) {
				if(!robot[i+1] && belt[0][i+1]>=1) { //이동하려는 칸에 로봇이 없으며, 칸의 내구도가 1 이상
					robot[i]=false;
					robot[i+1]=true;
					belt[0][i+1]--; //내구도 감소
					
					if(i+1==N-1) { //이동한 위치가 내리는 위치라면 즉시 내림
						robot[i+1]=false;
					}
				}
			}
		}
	}
	
	private static void loadRobot() { //올리는 위치에 로봇 올리기
		
		if(belt[0][0]!=0) {
			robot[0]=true;
			belt[0][0]--; //내구도 감소
		}
	}
}

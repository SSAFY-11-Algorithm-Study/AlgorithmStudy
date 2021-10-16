package week12;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class BOJ20055_컨베이어벨트로봇 {
	static int N,K;
	static int [] belt;
	static boolean [] robot;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();

		
		belt = new int[2*N];
		robot = new boolean[N];
		for(int i = 0 ; i < 2*N;  i++) 
			belt[i] = sc.nextInt();
		

		int cnt = 1; //몇단계인지 세기 위한 변수
		
		while(true) {
			//1. 컨베이어 벨트 회전
			
			rotate();
			
			//2. 로봇 이동
			moveRobot();
			
			//3. 새 로봇 올리기
			upRobot();
			
			//4. 내구도 체크
			int D = chkDurability();
			
			//5. 종료조건
			if(D >=K)
				break;
			
			cnt++; //모든사이클 종료후 카운트 증가
			
		}
			
		System.out.println(cnt);	
	}
	
	private static int chkDurability() {
		int zeroCnt = 0; //내구도 0인거 세는 변수
		for(int i = 0 ; i < 2*N ; i++) {
			if(belt[i] ==0) {
				zeroCnt++;
			}
		}
		return zeroCnt;
	}

	private static void upRobot() {
		if(belt[0] >=1) { //시작점 내구도가 1이상이면
			robot[0] = true; //로봇 올리고
			belt[0]--; //내구도 내리고
		}
	}

	//로봇이 이동가능하다면 이동
	private static void moveRobot() {
	
		robot[N - 1] = false;
        for (int i = N - 1; i > 0; i--) {  
        	//현재위치는 true 이면서 다음지점은 방문 아직 x , 내구도가 1이상이면
            if (robot[i - 1] && !robot[i] && belt[i] >= 1) {
                robot[i] = true; //로봇 이동
                robot[i - 1] = false; //여긴 false
                belt[i]--; //내구도 내리기
            }
        }
	}
	private static void rotate() {
		
		//컨베이어벨트 돌리기
		int temp = belt[belt.length-1];
		for(int i = belt.length-1 ; i>0  ; i--) {
			belt[i] = belt[i-1];
		}
		belt[0] = temp;
		
		//로봇도 같이 움직임
		for(int i = robot.length-1 ; i>0 ; i--) {
			robot[i] = robot[i-1];
		}
		robot[0] = false;
		
		
	}
}




package week5;

import java.util.Scanner;


//도저히 감 안잡혀서 문제푸는 아이디어 참고해서 풀었습니다
public class BOJ2116_주사위쌓기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int num = sc.nextInt();
		

		int [][] dice = new int [num][6];
		for(int i = 0 ; i < num ; i++) {
			dice[i][0] = sc.nextInt();
			dice[i][1] = sc.nextInt();
			dice[i][2] = sc.nextInt();
			dice[i][3] = sc.nextInt();
			dice[i][4] = sc.nextInt();
			dice[i][5] = sc.nextInt();
		}
		
		int bottom = 0;
		int top = 0;
		
		int max = -1;
		//첫 주사위가 뭐를 바닥에 두는지 6가지 경우로 시작
		for(int i = 0 ; i < 6 ; i ++) {
			int result = 0;
			
			//0,5 인덱스 , 1,3인덱스  2,4인덱스가 각각 반대임
			bottom = dice[0][i];
			
			if(i ==0) top = dice[0][5];
			else if(i ==1) top = dice[0][3];
			else if(i ==2) top = dice[0][4];
			else if(i ==3) top = dice[0][1];
			else if(i ==4) top = dice[0][2];
			else if(i ==5) top = dice[0][0];
			
			if(bottom + top ==11)
				result += 4;
			else if(bottom ==6 || top ==6)
				result += 5;
			else result +=6;
			
			
			//두번째 주사위부터 위에 쌓음
			for(int j = 1; j <num ; j++) {
				//다음 주사위의 바텀은 이전 주사위의 탑
				bottom = top;
				
				int idx = 0;
				for(int k =0 ; k < 6; k ++) {
					if(dice[j][k]== bottom) {
						//바텀이 어떤 인덱스에 위치했는지 체크
						idx = k;
						
						//탑이 어떤 숫자인지 찾음
						if(k ==0) top = dice[j][5];
						else if(k ==1) top = dice[j][3];
						else if(k ==2) top = dice[j][4];
						else if(k ==3) top = dice[j][1];
						else if(k ==4) top = dice[j][2];
						else if(k ==5) top = dice[j][0];
						break;
					}
				}
				
				//점수 추가
				if(bottom + top ==11)
					result += 4;
				else if(bottom ==6 || top ==6)
					result += 5;
				else result +=6;
			}
			
			max = Math.max(max, result);
		}
		
		System.out.println(max);
	}
}

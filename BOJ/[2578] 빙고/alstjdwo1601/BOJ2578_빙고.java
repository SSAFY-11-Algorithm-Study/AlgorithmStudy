package week3;

import java.util.Scanner;

public class BOJ2578_빙고 {
	public static int [][] bingo;
	public static int [][] num;
	public static int bingoCnt;
	public static int answer;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		bingo = new int[5][5];
		num = new int [5][5]; //사회자가 부르는 번호

		//빙고판 값 채우기
		for(int i = 0 ; i < 5; i ++) {
			for(int j = 0 ; j < 5; j++) 
				bingo[i][j] = sc.nextInt();
		}

		//사회자가 번호 하나 부를때마다 함수 호출
		Outter : for(int i = 0 ; i < 5; i ++) {
			for(int j = 0 ; j < 5; j++) {
				num[i][j] = sc.nextInt();
				deleteNum(i,j);

				//빙고가 3줄이상 완성되었다면 다음 지점은 더이상 검사할 필요가없음
				//한번에 2줄에서 ->4개,5개로 생기는 경우가 생김
				//https://www.acmicpc.net/board/view/34599
				//여기서 반례찾음
				if(bingoCnt >= 3) {
					System.out.println(answer);
					break Outter;
				}
			}
		}
	}

	//사회자가 부른 번호는 0 처리하고 해당 지점에서 빙고인지 체크
	private static void deleteNum(int x, int y) {
		//빙고판에서 사회자가 부른 번호는 0으로 만든다.
		for(int i = 0 ; i < 5; i ++) {
			for(int j = 0 ; j < 5; j++) {
				if(bingo[i][j] == num[x][y]) {
					answer++;
					bingo[i][j] = 0;
					
					//정가운데 지점
					if(i ==2 && j ==2) {
						digonal(); //대각 빙고 체크
						digonal2();
						isBingo(i,j);
					}
					else {
						if(i == j) {
							digonal(); //대각 빙고 체크
							isBingo(i,j);
						}

						else if(i+j ==4) {
							digonal2(); //대각 빙고 체크
							isBingo(i,j);
						}

						else isBingo(i,j);   //상하좌우 빙고 체크
					}
				}

				//해당 지점에서 빙고체크를 한 이후 빙고카운트가 3이면 종료
				if(bingoCnt==3) {
					return;
				}
			}
		}
	}

	//(0,0) ~ (4,4) 대각이 빙고인지 체크
	private static void digonal() {
		boolean flag = true;
		for(int i = 0 ; i < 5 ; i++) {
			if(bingo[i][i] != 0) {
				flag = false;
				break;
			}
		}
		if(flag) bingoCnt++;
	}

	//(4,0) ~ (0,4) 대각이 빙고인지 체크
	private static void digonal2() {
		boolean flag  = true;
		int tempY = 4;
		for(int i = 0; i < 5 ; i++) {
			if(bingo[i][tempY] != 0) {
				flag = false;
				break;
			}
			tempY--;
		}
		if(flag) bingoCnt++;
	}

	//상하좌우  0인지 체크해서 빙고인지 아닌지 확인
	private static void isBingo(int x, int y) {
		boolean flag = true;
		//상하
		for(int i = 0 ; i < 5; i ++) {
			if(bingo[i][y] != 0 ) {
				flag = false;
				break;
			}
		}
		if(flag) bingoCnt++;


		flag = true;
		//좌우
		for(int i = 0 ; i < 5; i ++) {
			if(bingo[x][i] != 0 ) {
				flag = false;
				break;
			}
		}
		if(flag) bingoCnt++;
	}
}

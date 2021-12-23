package week21;

import java.util.Scanner;

/*
 * https://developer-mac.tistory.com/23 여기 참고했음
 * 근데 처음 함수호출시 0,n-1 에서 호출하는게 이해가 잘안됨
 * 
 */
public class BOJ2448_별찍기11 {
	static char [][] map;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		//가로는 2n-1, 세로는 n 의 크기로 생성됨
		map = new char[n][2 * n - 1];
		
		//공백으로 미리 채워둠
        for (int i = 0; i<n; i++)
            for (int j = 0; j < 2*n-1; j++)
                map[i][j] = ' ';
		
        //(0,n-1) 에서 시작  <-- 이거 생각하는게 어려운듯
        drawStar(0,n-1,n);
        
        
        //맵 출력
        for (int i = 0; i<n; i++) {
            for (int j = 0; j < 2*n-1; j++)
                System.out.print(map[i][j]);
            System.out.println();
        }
	}
	private static void drawStar(int x, int y, int n) {
		if(n==3) { //재귀 종료 조건
			map[x][y] = '*';
			
			map[x+1][y+1] = '*';
			map[x+1][y-1] = '*';
			
			map[x+2][y-2] = '*';
			map[x+2][y-1] = '*';
			map[x+2][y] = '*';
			map[x+2][y+1] = '*';
			map[x+2][y+2] = '*';
		}
		
		drawStar(x,y,n/2); // 위에 작은 삼각형
		
		//아래 두개 삼각형으로 재귀
		drawStar(x + n / 2, y - n / 2, n / 2);
        drawStar(x + n / 2, y + n / 2, n / 2);
	}

}

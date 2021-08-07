package week2;
//시간 초과 뜹니다..

import java.util.Scanner;

public class BOJ10158_Ant {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int w = sc.nextInt();
		int h = sc.nextInt();
		
		//current x, y
		int cx=sc.nextInt();
		int cy=sc.nextInt(); 
		
		int t=sc.nextInt();
		//int[][] map = new int[h+1][w+1]; -> 필요없음
		int dir = 1; //1:오른쪽 위 2:왼쪽 위 3:오른쪽 아래 4: 왼쪽 아래
		
		
		while(t!=0) {
			
			switch (dir) {
			case 1: // x++, y++
				cx++; cy++; t--;
				if(cx==w && cy!=h)
					dir=2;
				else if(cx==w && cy==h) 
					dir=4;
				else if(cx!=w && cy==h)
					dir=3;
				break;

			case 2: // x--, y++
				cx--; cy++; t--;
				if(cx==0 && cy!=h) {
					dir=1;
				} else if (cx==0 && cy==h) {
					dir=3;
				} else if(cx!=0 && cy==h) {
					dir=4;
				}
				break;
			
			case 3: //x++, y--
				cx++; cy--; t--;
				if(cx==w && cy!=0) {
					dir=4;
				}else if(cx==w && cy==0) {
					dir=2;
				}else if(cx!=w && cy==0) {
					dir=1;
				}
				break;
				
			case 4: //x--, y--
				cx--; cy--; t--;
				if(cx==0 && cy!=0) {
					dir=3;
				}else if(cx==0 && cy==0) {
					dir=1;
				}else if(cx!=0 && cy==0) {
					dir=2;
				}
				break;
			}
		}
		
		System.out.println(cx + " " + cy);
		
	}

}

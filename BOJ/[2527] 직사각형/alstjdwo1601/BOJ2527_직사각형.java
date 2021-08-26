package week5;

import java.util.Scanner;

public class BOJ2527_직사각형 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for(int a= 1; a<=4; a++) {
			int x1 = sc.nextInt();
			int y1 = sc.nextInt();
			int p1 = sc.nextInt();
			int q1 = sc.nextInt();
			
			int x2 = sc.nextInt();
			int y2 = sc.nextInt();
			int p2 = sc.nextInt();
			int q2 = sc.nextInt();
			
			//아예 밖에 있는 경우는 d
			if(p1 < x2 || p2< x1 ||q1 <y2 || q2 < y1)
				System.out.println("d");
			//한점 겹치는경우
			else if((x1==p2 && y1 ==q2) ||
					(x2==p1 && y2 ==q1) ||
					(x2==p1 && y1 ==q2) ||
					(x1==p2 && y2 ==q1))
				System.out.println("c");
			//위에서 걸러졌는데도 겹치는 숫자가 있으면 변이 겹치는것
			else if(p1 == x2 || q1==y2 ||p2 ==x1 || y1==q2) 
				System.out.println("b");
			else
				System.out.println("a");
		}
		
	}
}

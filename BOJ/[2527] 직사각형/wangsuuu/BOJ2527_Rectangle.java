import java.util.Scanner;

public class BOJ2527_Rectangle {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		for(int i=0; i<4; i++) {
			int x1 = sc.nextInt();
			int y1 = sc.nextInt();
			int p1 = sc.nextInt();
			int q1 = sc.nextInt();
			int x2 = sc.nextInt();
			int y2 = sc.nextInt();
			int p2 = sc.nextInt();
			int q2 = sc.nextInt();
			//입력 완료
			
			if(p1<x2 || y1>q2 || x1>p2 || q1<y2) { //공통부분이 없는 경우
				System.out.println("d");
			} else if((p1==x2 && y1==q2) || (p1==x2 && q1==y2) || (x1==p2 && q1==y2) || (x1==p2 && y1==q2) ) { //꼭짓점만 겹치는 경우
				System.out.println("c");
			} else if(q2==y1 || p1==x2 || q1==y2 || p2==x1) { //선분이 겹치는 경우 (c나 d의 경우는 앞에서 걸러지므로, 이것만 체크하면 됨)
				System.out.println("b");
			} else { //직사각형이 겹치는 경우
				System.out.println("a");
			}
		}
	}
}

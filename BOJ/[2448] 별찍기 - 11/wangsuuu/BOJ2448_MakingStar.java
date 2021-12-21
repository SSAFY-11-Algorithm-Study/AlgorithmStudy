import java.util.Scanner;

//https://12216715011126.tistory.com/23 참고..

public class BOJ2448_MakingStar {
	static String[] pic;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		pic = new String[N+1];
		
		pic[1] = "  *  ";
		pic[2] = " * * ";
		pic[3] = "*****";
		
		for(int i=1; 3 * Math.pow(2, i) <= N; i++) {
			makeTriangle(i);
		}
		
		for(int i=1; i<=N; i++) {
			System.out.println(pic[i]);
		}
	}

	private static void makeTriangle(int i) {
		int newEnd = (int) (3 * Math.pow(2, i));
		int newStart = newEnd/2 + 1;
		
		//밑에 새로 생기는 두 삼각형 만들기
		int idx1=1;
		for(int a=newStart; a<=newEnd; a++) {
			pic[a] = pic[idx1] + " " + pic[idx1];
			idx1++;
		}
		
		//기존에 있던 위 삼각형 하나는 공백을 주기
		int blankCnt = newEnd-newStart+1;
		String blank= "";
		for(int a=0; a<blankCnt; a++){
			blank += " ";
		}
		
		for(int a=1; a<newStart; a++) {
			pic[a] = blank + pic[a] + blank;
		}
	}
}

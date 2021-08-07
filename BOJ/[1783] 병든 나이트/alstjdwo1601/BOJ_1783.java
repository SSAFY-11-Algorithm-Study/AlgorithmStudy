package week2;
//백준 병든 나이트
import java.util.Arrays;
import java.util.Scanner;

public class BOJ_1783 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int answer =0;
		
		int N = sc.nextInt(); // 높이
		int M = sc.nextInt(); // 너비
		
		//아예 못움직임
		if(N ==1)
			answer =1;
		
		//상우우 , 하우우 만 가능한데 
		//어짜피 4번이하로밖에 못움직임 이러면
		if(N ==2)
			answer =Math.min(4, (M+1) /2);
		
		//높이가 3이상인경우 
		if( N >=3){
			//모든 방향 한번씩 사용가능함
			if(M>=7)
				answer = M-2;
			
			//이경우도 상상우, 하하우 만 가능
			else 
				answer =Math.min(4, M);
			
		}
		System.out.println(answer);
	}
}

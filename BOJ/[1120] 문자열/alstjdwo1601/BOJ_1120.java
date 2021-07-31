package week1;

import java.util.Scanner;

//백준 1120번 문자열
public class BOJ_1120 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String s1 = sc.next();
		String s2 = sc.next();
		
		int cnt = 0;
		if(s1.length() == s2.length()) {
			for(int i = 0 ; i <s1.length() ;i++) {
				if(s1.charAt(i) != s2.charAt(i))
					cnt++;
			}
		}
		//길이가 다른 경우
		else {
			int diff = s2.length() - s1.length();
			//길이가 긴 s2에서 차이만큼 잘라서 온다
			// s2 가 abcd 이고 s1이 ef 이면 front 는 ab , back은 cd
			String front = s2.substring(0,diff);
			String back = s2.substring(s2.length() - diff,s2.length());
			
			//s1에 앞뒤로 붙여서 새로 만듬
			String newS1 = front + s1;
			String newS2 = s1+ back;
			
			//System.out.println(newS1 +" , " + newS2);
			
			//새로 만든 스트링으로 비교하고 차이 적은걸로 뽑음
			int cnt1 = 0;
			int cnt2 = 0;
			for(int i = 0 ; i <s2.length() ;i++) {
				if(newS1.charAt(i) != s2.charAt(i))
					cnt1++;
			}
			
			for(int i = 0 ; i <s2.length() ;i++) {
				if(newS2.charAt(i) != s2.charAt(i))
					cnt2++;
			}
			
			cnt = Math.min(cnt1, cnt2);
		}
		
		System.out.println(cnt);
		
	}

}

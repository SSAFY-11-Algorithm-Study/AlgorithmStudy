/*
 * 1.  숫자 순열 (순열조합시 맨 앞 0 x)
 * 2.  소수인지 체크 -> 같은 카드 여러개 일 때 중복되는 소수 체크해 줘야함. 중복제거.
 */


package time8;

import java.util.ArrayList;
import java.util.Arrays;

public class PM_searchPrimeNum {
	
	static String numbers = "011";
	static boolean[] isSelected;
	static ArrayList<Integer> primenums = new ArrayList<>();
	
	public static void main(String[] args) {
		int answer = 0;
		int N = numbers.length();
		String[] papers = new String[N];
		isSelected = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			papers[i] = Character.toString(numbers.charAt(i));
		}
		System.out.println(Arrays.toString(papers));
		
		for(int i = 1; i <= N; i ++) {
			//1개~N개 까지 조합
			per(0,papers,i,"");
		}
		//중복제거
		ArrayList<Integer> result = new ArrayList<>();
		for(int num : primenums) {
			if(!result.contains(num)) {
				result.add(num);
			}
		}
		//System.out.println(primenums.size());
		//System.out.println(result.size());
		
		answer = result.size();
	}
	static void per(int cnt, String[] papers, int r, String str) {
		if(cnt == r) {
			int temp = Integer.parseInt(str);
			if(check(temp)) {
				primenums.add(temp);
			}
			return;
		}
		
		for(int i = 0; i < papers.length; i ++) {
			if(isSelected[i]) continue;
			isSelected[i] = true;
			str = str + papers[i];
			per(cnt+1,papers,r,str);
			isSelected[i] = false;
			str = str.substring(0, str.length()-1);
		}
	}
	
	static boolean check(int num) {
		if(num == 0 || num == 1) return false;
		for(int i = 2 ; i < num; i ++) {
			if(num%i == 0) return false;
		}
		System.out.println(num);
		return true;
	}

}

//답은 맞는데 메모리 초과...

package time24;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BJ2661_좋은수열 {
	
	private static int N;
	private static boolean flag = false;
	private static ArrayList<String> result = new ArrayList<>();

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		back("");
		
		Collections.sort(result);
		
		System.out.println(result.get(0)); 

	}

	private static void back(String number) {
		
		if(number.length() == N) {
			result.add(number);
			return;
		}
		
		for(int i = 1; i <= 3; i++) {
			if(isvalid(number+i)) {
				back(number+i);
			}
		}
	}

	private static boolean isvalid(String temp) {
		int length = temp.length();
		
		for(int i = 1; i <= length/2; i++) {
			String front = temp.substring(temp.length()-2*i, temp.length()-i);
			String back = temp.substring(temp.length()-i, temp.length());
			
			if(front.equals(back)) return false;
		}
		return true;
	}

}
 

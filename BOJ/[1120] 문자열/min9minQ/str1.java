package time1;

import java.util.Scanner;

public class str1 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		String str1 = sc.next();
		String str2 = sc.next();
		int result = 50;
		
		int differ = str2.length() - str1.length();
		
		for(int i = 0 ; i < differ + 1; i ++) {
			int num = 0;
			for(int j = 0 ;j<str1.length();j++) {
				if(str1.charAt(j) != str2.charAt(j+i)) {
					num++;
				}
			}
			if(num < result)
				result = num;
		}
    System.out.println(result);
	}

}

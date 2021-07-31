package time1;

import java.util.Scanner;

public class parenthesis {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		String[] str = new String[num];
		int count = 0;
		
		for(int i = 0; i < num; i++) {
			str[i] = sc.next();
		}
		for(int i = 0; i < num; i ++) {
			
			for(int j = 0 ; j < str[i].length();j++) {
				if(str[i].charAt(j)=='(') {
					count ++;
				} else if(str[i].charAt(j)==')') {
					count --;
					if (count < 0) {
						break;
					}
				}
			}
			if(count == 0) {
				System.out.println("YES");
			} else{
				System.out.println("NO");
				count = 0;
			}
		}
		
		
	}
}

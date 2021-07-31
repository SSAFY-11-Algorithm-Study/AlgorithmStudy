package time1;

import java.util.Arrays;
import java.util.Scanner;

public class big {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		int ary[][] = new int[num][2];
		
		for(int i = 0; i < num; i++) {
			ary[i][0] = sc.nextInt();
			ary[i][1] = sc.nextInt();
		}
		System.out.println(Arrays.deepToString(ary));
		
		for(int i = 0; i < num; i ++) {
			int count = 0;
			for(int j = 0; j <num; j++) {
				if(ary[i][0]<ary[j][0] && ary[i][1]<ary[j][1]) {
					count ++;
				}
			}
			System.out.print(count+1 + " ");
		}
		
		
	}

}

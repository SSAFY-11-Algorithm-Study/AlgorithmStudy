package time1;

import java.util.Arrays;
import java.util.Scanner;

public class scale {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int [] ary =new int[8];
		int [] asc = {1,2,3,4,5,6,7,8};
		int [] dsc = {8,7,6,5,4,3,2,1};
		for(int i = 0; i<ary.length ;i++) {
			ary[i] = sc.nextInt();
		}
		
		if(Arrays.equals(ary, asc) == true)
			System.out.println("ascending");
		else if (Arrays.equals(ary, dsc) == true)
			System.out.println("descending");
		else
			System.out.println("mixed");
		
	}

}

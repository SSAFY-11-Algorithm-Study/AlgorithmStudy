//시간초과 
// 찾아보니 hashset으로 하면 될듯..? (성능이 좋음)
package time28;

import java.util.ArrayList;
import java.util.Scanner;

public class BJ1269_대칭차집합 {
	
	private static ArrayList<Integer> arr = new ArrayList<>();
	private static int dup;

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int A = sc.nextInt();
		int B = sc.nextInt();
		
		for( int i = 0; i < A; i++ ) {
			arr.add( sc.nextInt());
		}
		
		for(int i = 0; i < B; i ++) {
			if(arr.contains(sc.nextInt())) dup++;
		}
		
		System.out.println(A+B-2*dup);
		

	}

}

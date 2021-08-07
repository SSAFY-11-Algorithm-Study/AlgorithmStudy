package time2;

import java.util.Scanner;
import java.util.Stack;

public class BJ10773_zero {

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		
		Scanner sc =  new Scanner(System.in);
		int num = sc.nextInt();
		
		for(int i = 0; i < num; i ++) {
			int temp = sc.nextInt();
			if(temp == 0) {
				stack.pop();
				continue;
			}
			stack.push(temp);
		}
		int sum = 0;
		for(int a : stack) {
			sum += a;
		}
		System.out.println(sum);
		
	}

}

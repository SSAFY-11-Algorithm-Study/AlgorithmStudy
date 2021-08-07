package week2;

import java.util.Scanner;
import java.util.Stack;

//백준 10773 제로
public class BOJ_10773 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		Stack<Integer> stack = new Stack<>();

		for(int i = 0 ; i < num ; i++) {
			int price = sc.nextInt();
			if(price!=0) {
				//System.out.printf("push : %d\n",price);
				stack.push(price);
			}
			else 
				stack.pop();
		}

		int answer = 0;
		while(true) {
			if(stack.size() > 0)
				answer += stack.pop();
			else
				break;
		}
		System.out.println(answer);
	}

}

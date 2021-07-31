package week1;

import java.util.Scanner;
import java.util.Stack;

//백준 9012 괄호
public class BOJ_9012 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		
		Stack<String> stack = new Stack<>();
		for(int i = 0 ; i < num ; i ++) {
			String s = sc.next();
			String [] arr = s.split("");
			
			for(int j = 0 ; j < arr.length; j++) {
				//스택이 비어있으면 일단 넣어
				if(stack.size() == 0)
					stack.push(arr[j]);

				else {
					//스택 최상단 하나 확인하고
					String str = stack.peek();
					//짝이 맞으면 팝
					if(str.equals("(") && arr[j].equals(")"))
						stack.pop();
					else
						stack.push(arr[j]);
				}
			}
			//System.out.println(stack.toString());
			
			if(stack.empty())
				System.out.println("YES");
			else
				System.out.println("NO");
			
			stack.clear();
		}
	}
}

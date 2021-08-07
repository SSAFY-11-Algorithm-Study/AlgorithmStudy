package week1;

import java.util.Scanner;
import java.util.Stack;

public class BOJ_9012_1 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		
		Stack<String> stack = new Stack<>();
		String[] answer = new String[num];
		
		for(int i=0; i<num; i++) {
			
			String s = sc.next();
			String[] arr = s.split("");
			
			for(int j=0; j<arr.length; j++) {
				if(stack.empty())//스택이 비어있으면 일단 푸시
					stack.push(arr[j]);
				else {
					if(arr[j].equals("("))
						stack.push(arr[j]);
					
					else{
						if(stack.peek().equals("(")) //짝이 맞음
							stack.pop();
						else
							stack.push(arr[j]);
					}
				}
			}
			if(stack.empty())
				answer[i]="YES";
			else
				answer[i]="NO";
			
			stack.clear();
		}
		sc.close();
		for(String s : answer)
			System.out.println(s);
	}
}

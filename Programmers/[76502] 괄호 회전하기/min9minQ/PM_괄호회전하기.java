package time13;

import java.util.Stack;

public class PM_괄호회전하기 {

	static String s = "[](){}";

	public static void main(String[] args) {
		
		int answer = 0;
		
		StringBuilder sb = new StringBuilder(s);
		
		for(int i = 0; i < s.length(); i ++) {
			if(isCorrected(sb)) {
				answer ++;
			}
			sb.append(sb.charAt(0));
			sb.deleteCharAt(0);
		}
		System.out.println(answer);
		//return answer;
	}

	private static boolean isCorrected(StringBuilder sb) {
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < sb.length(); i++) {
			char temp = sb.charAt(i);
			if (temp == '(' || temp == '[' || temp == '{') {
				stack.add(temp);
			} else if (!stack.isEmpty()) {
				if (temp == ')' && stack.peek() == '(') {
					stack.pop();
				} else if (temp == ']' && stack.peek() == '[') {
					stack.pop();
				} else if (temp == '}' && stack.peek() == '{') {
					stack.pop();
				}
			} else {
				return false;
			}
		}
		
		// 반복문을 다 빠져나오고 스택이 딱 다 비워져있으면
		if(stack.isEmpty()) return true;
		// 반복문을 다 빠져나왔는데 스택이 안비워져있어
		return false;
	}
}

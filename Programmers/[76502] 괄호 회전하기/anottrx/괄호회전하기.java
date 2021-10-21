import java.util.*;

class Solution {

	static Stack<Character> stack;

	public int solution(String s) {
		int answer = 0;
		stack = new Stack<>();

		for (int i = 0; i < s.length(); i++) { // 문자열 길이만큼 반복
			if (getResultMove(s, i)) {
				answer++;
			}
		}
		return answer;
	}

	private static boolean getResultMove(String s, int n) { // 문자열 회전
		int len = s.length();
		String movedStr = s.substring(n, len) + s.substring(0, n);
		if (isCorrect(movedStr)) { // 올바른 괄호 문자열인지 확인
			return true;
		} else {
			return false;
		}
	}

	private static boolean isCorrect(String s) { // 올바른 괄호 문자열인지 확인
		stack.clear(); // 우선 스택 비우기
		int len = s.length();
		for (int i = 0; i < len; i++) {
			if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
				stack.push(s.charAt(i));
			} else {
				if (stack.isEmpty()) { // 닫는 괄호가 나왔는데 스택은 비었다면 false
					return false;
				} else {
					if ((s.charAt(i) == ')' && stack.pop() == '(') || (s.charAt(i) == '}' && stack.pop() == '{')
							|| (s.charAt(i) == ']' && stack.pop() == '[')) {
					} else { // 위의 경우를 제외하면 false
						return false;
					}
				}
			}
		}

		if (stack.isEmpty()) { // 다 했을 때 스택이 비었다면 올바른 괄호 문자열
			return true;
		} else { // 다 했을 때 스택에 아직 문자열이 남았다면 올바리즈 않은 괄호 문자열
			return false;
		}
	}
}

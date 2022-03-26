// substring은 새로운 메모리 공간을 할당 => 효율성 문제

import java.util.Stack;

class Solution {
    public int solution(String s) {
        int answer = 1; // 1로 초기화

        Stack<Character> stack = new Stack<>();
        stack.push(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            if (stack.size() > 0 && stack.peek() == s.charAt(i)) {
                stack.pop();
            } else {
                stack.push(s.charAt(i));
            }
        }
        if (stack.size() > 0) { // 다 끝났는데 스택에 남아있다면 0
            answer = 0;
        }

        return answer;
    }
}

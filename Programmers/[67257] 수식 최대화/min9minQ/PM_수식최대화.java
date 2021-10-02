// 조합 뽑기랑 계산 과정 풀이 코딩해야됨.. ㅠㅜ

package time10;

import java.util.ArrayList;

public class PM_수식최대화 {
	
	static String expression = "100-200*300-500+20";
	
	static boolean[] visited = new boolean[3];
	static ArrayList<Long> nums = new ArrayList<>();
	static ArrayList<Character> opers = new ArrayList<>();

	public static void main(String[] args) {
		long answer = 0;
		
		for(int i = 0; i < expression.length(); i++) {
			String num = "";
			if(expression.charAt(i) == '+' || expression.charAt(i) == '-' || expression.charAt(i) == '*') {
				opers.add(expression.charAt(i));
				nums.add(Long.parseLong(num));
				num = "";
			} else {
				num += expression.charAt(i);
			}
		}
		
		// 조합으로 우선순위 뽑고 -> 아니면 어차피 최대 6가지이니까 수작업..?
		// 최대값 계산해서 리턴
		
		System.out.println(answer);
		//return answer;
	}

	static long cal(long num1, long num2, char oper) {
		if(oper == '+') return num1 + num2;
        else if(oper == '-') return num1 - num2;
        else return num1 * num2;
	}
}

package time8;

import java.util.Arrays;
import java.util.Comparator;

public class PM_BiggestNum {

	static int[] numbers = { 6, 10, 2 };
	static String answer;

	public static void main(String[] args) {
		String[] strnums = new String[numbers.length];
		for (int i = 0; i < numbers.length; i++) {
			strnums[i] = Integer.toString(numbers[i]);
		}

		Arrays.sort(strnums, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return (o2 + o1).compareTo(o1 + o2);
			}
		});

		if (strnums[0].equals("0")) { // 0 체크해줘야됨.
			answer = "0";
		} else {
			for (String num : strnums) {
				answer += num;
			}
		}
		System.out.println(answer);
		//return answer;

	}

}

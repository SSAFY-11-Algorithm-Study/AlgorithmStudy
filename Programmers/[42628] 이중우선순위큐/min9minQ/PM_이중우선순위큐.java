package time14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PM_이중우선순위큐 {

	static String[] operations = { "I 7", "I 5", "I -5", "D -1" };

	public static void main(String[] args) {
		int[] answer = {};

		ArrayList<Integer> arr = new ArrayList<>();

		for (String s : operations) {
			char oper = s.charAt(0);
			String number = s.substring(2, s.length());
			int num = Integer.parseInt(number);

			if (oper == 'I') {
				arr.add(num);
				Collections.sort(arr);
			} else {
				if (num == -1) {
					arr.remove(0);
				} else {
					arr.remove(arr.size() - 1);
				}
			}
		}

		if (arr.isEmpty()) {
			answer = new int[] { 0, 0 };
		} else {
			answer = new int[] {arr.get(arr.size()-1),arr.get(0)};
		}

		System.out.println(Arrays.toString(answer));

	}

}

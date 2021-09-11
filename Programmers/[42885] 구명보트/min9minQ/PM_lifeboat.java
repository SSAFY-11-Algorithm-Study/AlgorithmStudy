// 테스트 케이스 이것저것 돌려봤을 때, 정답은 맞는 것 같은데 시간 초과가 떴습니다..
package time7;

import java.util.ArrayList;

public class PM_lifeboat {

	static int[] people = { 100,500,500,900,950 };
	static int[] numbers;
	static int limit = 1000;
	static boolean[] isSelected;
	static int min;

	public static void main(String[] args) {

		int answer = 0;
		numbers = new int[people.length];
		isSelected = new boolean[people.length];
		min = Integer.MAX_VALUE;
		per(0,answer);
		
		System.out.println(min);
	}
	
	private static void per(int cnt,int answer) { //순열로 모든 경우 따져보기 
		if(cnt == people.length) {
			//기저 조건
			ArrayList<Integer> arr = new ArrayList<>();
			for (int i = 0; i < numbers.length; i++) {
				arr.add(numbers[i]);
			}
			while (arr.size() > 0) {
				int temp = limit - arr.get(0);
				arr.remove(0);
				for (int i = 0; i < arr.size(); i++) {
					if (temp >= arr.get(i)) {
						temp = temp - arr.get(i);
						arr.remove(i);
						i--;
					}
				}
				answer++;
			}
			min = Math.min(min, answer);
			
			return;
		}
		
		for(int i = 0; i < people.length; i++) {
			if(isSelected[i]==true) {
				continue;
			}
			
			numbers[cnt] = people[i];
			isSelected[i] = true;
			per(cnt+1,answer);
			isSelected[i] = false;
		}
	}
}


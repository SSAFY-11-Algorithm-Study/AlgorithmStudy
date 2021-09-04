// 2^31-1 이라는 숫자를 보고 
// 제한시간 초과가 날거고 생각했는데
// 제한 시간 초과가 아닌  틀렸습니다가 떴습니다.... 예제 문제 시 길이 200과 11개 라고 잘 뜨는데 ㅠㅠ


package time6;

import java.util.ArrayList;
import java.util.Scanner;

public class BJ1654_cutLAN {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int K = sc.nextInt();
		int N = sc.nextInt();
		int min = Integer.MAX_VALUE;
		int count = 0;
		ArrayList<Integer> arr = new ArrayList<>();
		for (int i = 0; i < K; i++) {
			int temp = sc.nextInt();
			arr.add(temp);
			min = Integer.min(min, temp);
		}
		while (true) {
			count = 0;
			for (int i = 0; i < arr.size(); i++) {
				int temp = arr.get(i);
				while (temp > 0) {
					temp = temp - min;
					if(temp >0)
						count++;
				}
			}
			if (count < N) {
				min--;
			} else {
				break;
			}
		}
		System.out.println(min);
	}
}

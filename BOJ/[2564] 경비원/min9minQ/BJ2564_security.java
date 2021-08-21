//테스트 케이스는 통과 되는데 백준에 올리면 인덱스 오류가...
//어디서 오타가 나거나 인덱스 범위 넘어서 접근 하는 부분이 있는거 같아 찾아보는 중입니다.

package time4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2564_security {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int minSum = 0;
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		double perimeter = 2 * (x + y); // 둘레

		int num = Integer.parseInt(br.readLine());
		int[][] arr = new int[num + 1][2]; // 동근이의 위치 까지 저장

		for (int i = 0; i <= num; i++) {// 동근이의 위치까지 저장
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		// 동근이의 집 위치에 따라 나눠줌
		if (arr[num][0] == 1) {
			for (int i = 0; i < num; i++) {
				if (arr[i][0] == 1)
					minSum += Math.abs(arr[i][1] - arr[num][1]);
				else if (arr[i][0] == 2) {
					if (arr[i][1] + arr[num][1] + y <= perimeter / 2)
						minSum += arr[i][1] + arr[num][1] + y;
					else
						minSum += perimeter - (arr[i][1] + arr[num][1] + y);
				} else if (arr[i][0] == 3)
					minSum += arr[num][1] + arr[i][1];
				else
					minSum += arr[i][1] + x - arr[num][1];
			}
		} else if (arr[num][0] == 2) {
			for (int i = 0; i < num; i++) {
				if (arr[i][0] == 1) {
					if (arr[i][1] + arr[num][1] + y <= perimeter / 2)
						minSum += arr[i][1] + arr[num][1] + y;
					else
						minSum += perimeter - (arr[i][1] + arr[num][1] + y);
				} else if (arr[i][0] == 2)
					minSum += Math.abs(arr[i][1] - arr[num][1]);
				else if (arr[i][0] == 3)
					minSum += y - arr[i][1] + arr[num][i];
				else
					minSum += x - arr[num][1] + y - arr[i][1];
			}
		} else if (arr[num][0] == 3) {
			for (int i = 0; i < num; i++) {
				if (arr[i][0] == 1)
					minSum += arr[i][1] + arr[num][1];
				else if (arr[i][0] == 2)
					minSum += y - arr[num][1] + arr[i][1];
				else if (arr[i][0] == 3)
					minSum += Math.abs(arr[i][1] - arr[num][1]);
				else {
					if (arr[num][1] + x + arr[num][1] <= perimeter / 2)
						minSum += arr[num][1] + x + arr[num][1];
					else minSum += perimeter - (arr[num][1] + x + arr[num][1]);
				}
			}
		} else if (arr[num][0] == 4) {
			for (int i = 0; i < num; i++) {
				if(arr[i][0] == 1) minSum += x-arr[i][1]+arr[num][1];
				else if(arr[i][0] == 2) minSum += x-arr[i][1]+y-arr[num][1];
				else if(arr[i][0] == 3) {
					if(arr[i][1] + x + arr[num][1] <= perimeter/2) minSum += arr[i][1] + x + arr[num][1];
					else minSum += perimeter - (arr[i][1] + x + arr[num][1]);
				} else minSum += Math.abs(arr[i][1]-arr[num][1]);
			}
		}
		
		System.out.println(minSum);

	}

}

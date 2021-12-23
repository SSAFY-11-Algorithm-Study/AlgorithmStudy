package time21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2467_용액 {

	private static int N;
	private static int[] arr;
	private static int[] result = new int[2];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		int start = 0;
		int end = N - 1;
		int max = Integer.MAX_VALUE;

		while (start < end) {
			int sum = arr[start] + arr[end];

			if (Math.abs(sum) < max) {
				result[0] = arr[start];
				result[1] = arr[end];
				max = Math.abs(sum);
			}

			if (sum > 0) {
				end--;
			} else {
				start++;
			}
		}
		System.out.println(result[0] + " " + result[1]);
	}
	

}

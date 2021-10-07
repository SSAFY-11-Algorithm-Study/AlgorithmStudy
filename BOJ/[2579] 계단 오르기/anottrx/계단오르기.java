import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int[] stair = new int[N];
		int[] cnt = new int[N];
		for (int i = 0; i < N; i++) {
			stair[i] = Integer.parseInt(br.readLine()); // 계단
			cnt[i] = stair[i]; // dp
		}

		if (N >= 2) { // 2개 이상일 때
			cnt[1] = stair[0] + stair[1];
			if (N >= 3) { // 3개 이상일 때
				cnt[2] = Math.max(stair[0], stair[1]) + stair[2];
				for (int i = 3; i < N; i++) {
					cnt[i] = Math.max(cnt[i - 3] + stair[i - 1], cnt[i - 2]) + stair[i];
				}
			}
		}
		bw.write(cnt[N - 1] + "\n");
		bw.flush();
		bw.close();
	}
}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ9095 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] coin = new int[n];
		int[] money = new int[k + 1];
		for (int i = 0; i < n; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}

		money[0] = 1;
		for (int j = 0; j < n; j++) {
			for (int i = coin[j]; i <= k; i++) {
				if (i - coin[j] >= 0) {
					money[i] = money[i] + money[i - coin[j]];
				}
			}
		}
		bw.write(money[k] + "\n");
		bw.flush();
		bw.close();
	}
}

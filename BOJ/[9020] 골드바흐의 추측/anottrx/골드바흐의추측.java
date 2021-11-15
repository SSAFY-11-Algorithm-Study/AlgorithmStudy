import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        int n = 0, a = 0, b = 0;

        boolean[] isNotPrime = new boolean[10001];
        for (int i = 2; i <= Math.sqrt(10000); i++) {
            if (!isNotPrime[i]) {
                for (int j = 2, k = i * j; k <= 10000; j++, k = i * j) {
                    isNotPrime[k] = true;
                }
            }
        }

        for (int tc = 0; tc < T; tc++) {
            n = Integer.parseInt(br.readLine());
            for (int i = 2; i <= n / 2; i++) {
                if (!isNotPrime[i] && !isNotPrime[n - i]) {
                    a = i;
                    b = n - i;
                }
            }
            bw.write(a + " " + b + "\n");
        }
        bw.flush();
        bw.close();
    }
}

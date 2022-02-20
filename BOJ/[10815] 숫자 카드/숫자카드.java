import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ10815 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nCard = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nCard[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nCard); // 정렬

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());
            int start = 0;
            int end = N - 1;
            boolean isFound = false;
            while (start <= end) {
                int mid = (start + end) / 2;
                if (nCard[mid] == num) {
                    isFound = true; // 숫자 찾음
                    break;
                } else if (nCard[mid] < num) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
            if (isFound)
                sb.append("1 ");
            else
                sb.append("0 ");
        }

        System.out.println(sb.toString());
    }
}

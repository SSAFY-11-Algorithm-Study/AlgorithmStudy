import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2805 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] wood = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            wood[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(wood); // 정렬

        int start = 0; // 1로 시작해도 문제 통과
        int end = wood[N - 1]; // 제일 큰 값
        int mid = 0;
        while (start <= end) {
            mid = (start + end) / 2;
            long sum = 0; // long
            for (int i = 0; i < N; i++) {
                sum = sum + ((wood[i] < mid) ? 0 : (wood[i] - mid)); // 나무 자른 후 길이 더하기
            }
            if (sum >= M) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        System.out.println(end); // 답은 end
    }
}

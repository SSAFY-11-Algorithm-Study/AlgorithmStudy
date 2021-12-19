import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2467 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // N은 2 이상 100,000 이하의 정수
        int[] num = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) { // 오름차순으로 입력
            num[i] = Integer.parseInt(st.nextToken()); // -1,000,000,000 이상 1,000,000,000 이하
        }
        int start = 0, end = N - 1;
        double tempSum = Integer.MAX_VALUE;
        int answerStart = 0, answerEnd = N - 1;
        while (start < end) { // 자동으로 오름차순으로 출력
            double sum = num[start] + num[end];
            if (Math.abs(sum) < Math.abs(tempSum)) { // 더 0에 가까운 값이라면 바꾸기
                answerStart = start;
                answerEnd = end;
                tempSum = sum;
            }
            if (sum == 0) { // 합이 0이라면 끝내기
                break;
            } else if (sum > 0) {
                end--;
            } else {
                start++;
            }
        }
        System.out.println(num[answerStart] + " " + num[answerEnd]);
    }
}

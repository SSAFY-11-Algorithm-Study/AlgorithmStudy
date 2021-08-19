import java.util.*;

// 맞왜틀,,,,,,
public class boj2304 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] bar = new int[N][2];

        for (int i = 0; i < N; i++) {
            bar[i][0] = sc.nextInt();
            bar[i][1] = sc.nextInt();
        }

        Arrays.sort(bar, (o1, o2) -> o1[0] - o2[0]);

        Deque<int[]> deque = new LinkedList<>();
        int answer = 0;

        for (int i = 0; i < N - 1; i++) {
            if (deque.isEmpty()) {
                deque.push(bar[i]);
                continue;
            }

            if (deque.peek()[1] <= bar[i][1]) {
                int[] high = deque.pop();
                answer += high[1] * (bar[i][0] - high[0]);
                deque.push(bar[i]);
            }
        }

        int[] last = deque.pop();

        if (last[1] > bar[N - 1][1]) {
            answer += last[1] + bar[N - 1][1] * (bar[N - 1][0] - last[0]);
        } else {
            answer += bar[N - 1][1] + last[1] * (bar[N - 1][0] - last[0]);
        }

        System.out.println(answer);
    }
}

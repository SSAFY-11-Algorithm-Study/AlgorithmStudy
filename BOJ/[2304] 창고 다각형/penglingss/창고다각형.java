package com.BOJ;

import java.util.*;

public class boj2304 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] bar = new int[N][2];
        int[] max = new int[]{0, 0};

        for (int i = 0; i < N; i++) {
            bar[i][0] = sc.nextInt();
            bar[i][1] = sc.nextInt();

            if (max[1] < bar[i][1]) { // 최대값 찾기
                max = bar[i];
            } else if (max[1] == bar[i][1]) {
                if (max[0] < bar[i][0]) {
                    max = bar[i];
                }
            }
        }

        if (N == 1) { // 안해주면 런타임(ArrayIndexOutOfBounds) 에러..
            System.out.println(bar[0][1]);
            return;
        }

        Arrays.sort(bar, (o1, o2) -> o1[0] - o2[0]);

        Deque<int[]> deque = new LinkedList<>();
        int answer = 0;

        int idx = 0;
        while (true) { // index 0 ~ max값의 막대까지 확인

            if (deque.isEmpty()) {
                deque.push(bar[idx]);
                idx++;
                continue;
            }

            if (deque.peek()[1] <= bar[idx][1]) {
                int[] high = deque.pop();
                answer += high[1] * (bar[idx][0] - high[0]);
                deque.push(bar[idx]);
            }

            if(bar[idx][0] == max[0] && bar[idx][1] == max[1]) break;

            idx++;
        }

        deque.pop(); // 마지막에 넣은 값(max 막대) 빼고

        if (idx == N - 1) { // 높이가 5 5 5 5 인 경우를 위해서..
            answer += max[1];
            System.out.println(answer);
            return;
        }

        idx = N - 1;
        while (true) { // index N - 1 ~ max값의 막대까지 확인
            if (deque.isEmpty()) {
                deque.push(bar[idx]);
                idx--;
                continue;
            }

            if (deque.peek()[1] <= bar[idx][1]) {
                int[] high = deque.pop();
                answer += high[1] * (high[0] - bar[idx][0]);
                deque.push(bar[idx]);
            }
            if(bar[idx][0] == max[0] && bar[idx][1] == max[1]) break;
            idx--;
        }

        answer += max[1]; // max값 막대 더해준적 없기때문에 마지막에 더해줌

        System.out.println(answer);
    }
}

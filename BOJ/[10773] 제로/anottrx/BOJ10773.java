// 출처: 백준저지 10773번 제로 https://www.acmicpc.net/problem/10773

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

public class BOJ10773 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Deque<Integer> num = new ArrayDeque<Integer>();
        int K = Integer.parseInt(br.readLine()); // (1 ≤ K ≤ 100,000)
        int n; // 정수. 0에서 1,000,000 사이의 값
        int sum = 0;

        for (int i = 0; i < K; i++) {
            n = Integer.parseInt(br.readLine());
            if (n == 0) { // 정수가 "0" 일 경우에는 가장 최근에 쓴 수를 지운다
                int temp = num.peek();
                num.pop();
                sum = sum - temp;
            } else {
                num.push(n);
                sum = sum + n;
            }
        }
        bw.write(sum + "\n");
        bw.flush();
        bw.close();
    }
}

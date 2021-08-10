// 출처: 백준저지 11866번 요세푸스 문제 0 https://www.acmicpc.net/problem/11866

import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ11866 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 1번부터 N번까지 N명의 사람이 원을 이루면서 앉아있다
        int K = sc.nextInt(); // 순서대로 K번째 사람을 제거한다
        Queue<Integer> seats = new LinkedList<>();
        for (int i = 0; i < N; i++) { // 자리 입력
            seats.add(i + 1);
        }

        StringBuilder sb = new StringBuilder("<");
        while (!seats.isEmpty()) { // seats가 빌 때까지 반복한다
            for (int i = 0; i < K - 1; i++) { // K번째 자리가 될 때까지
                int head = seats.poll(); // 앞에서 한 명 빼서
                seats.offer(head); // 뒤로 넣는다
            }
            sb.append(seats.poll() + ", "); // K번째는 출력하고 뺀다
        }
        sb.setLength(sb.length() - 2);
        sb.append(">");
        System.out.print(sb); // 답 출력
    }
}

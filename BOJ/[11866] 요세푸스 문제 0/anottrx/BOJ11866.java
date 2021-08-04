// 출처: 백준저지 11866번 요세푸스 문제 0 https://www.acmicpc.net/problem/11866

import java.util.Scanner;
import java.util.LinkedList;

public class BOJ11866 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 1번부터 N번까지 N명의 사람이 원을 이루면서 앉아있다
        int K = sc.nextInt(); // 순서대로 K번째 사람을 제거한다
        LinkedList<Integer> seats = new LinkedList<>();
        for (int i = 0; i < N; i++) { // 자리 입력
            seats.add(i + 1);
        }

        int find = 0; // 자리 위치 확인용. 0으로 초기화
        StringBuilder sb = new StringBuilder("<");
        while (!seats.isEmpty()) { // seats가 빌 때까지 반복한다
            find = find + K - 1; // K만큼 이동한 뒤 1을 뺀다.
            // 맨 첫번째는 0부터 시작하기 때문이고, 그 다음부터는 seats를 하나 뺐기 때문이다
            while (find >= seats.size()) { // seats 길이보다 찾고자하는 위치가 클 경우
                find = find - seats.size(); // seats 길이보다 짧아질 때까지 반복한다
            }
            sb.append(seats.get(find) + ", "); // 해당 위치의 값을 출력한다
            seats.remove(find); // 값을 출력한 뒤 해당 위치의 값을 제거한다
        }
        sb.setLength(sb.length() - 2);
        sb.append(">");
        System.out.print(sb);
    }
}

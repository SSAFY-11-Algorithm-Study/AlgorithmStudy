// 출처: 백준저지 2920번 음계 https://www.acmicpc.net/problem/2920

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String answer = ""; // 답
        int[] num = new int[8]; // 첫째 줄에 8개 숫자가 주어진다.
        for (int i = 0; i < 8; i++) { // 숫자 입력
            num[i] = sc.nextInt();
        }
        int diff = num[1] - num[0]; // 인접한 두 수의 차이. 두번째와 첫번째 수의 차이로 초기화
        for (int i = 1; i < 8; i++) {
            if (diff != (num[i] - num[i - 1])) { // 인접한 두 수의 차이가 이전과 다르다면
                answer = "mixed";
            }
        }
        if (answer == "" && diff == 1) { // 첫번째수 + 1 = 두번째수
            answer = "ascending";
        } else if (answer == "" && diff == -1) { // 두번째수 - 1 = 첫번째수
            answer = "descending";
        }
        System.out.println(answer); // 답 출력
    }

}

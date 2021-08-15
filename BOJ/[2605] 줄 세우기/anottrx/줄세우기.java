// 출처: 백준저지 2605번 줄 세우기 https://www.acmicpc.net/problem/2605

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ2605 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> students = new ArrayList<>();
        int N = sc.nextInt(); // 학생 수

        for (int i = 1; i <= N; i++) {
            students.add(sc.nextInt(), i); // 학생 순서 입력 받고 넣기
        }
        for (int i = N - 1; i >= 0; i--) { // 뒤에서부터 출력
            System.out.print(students.get(i) + " ");
        }
    }
}

// 출처: 백준저지 14696번 딱지놀이 https://www.acmicpc.net/problem/14696

import java.util.Scanner;

public class BOJ14696 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int N = sc.nextInt();
        for (int n = 0; n < N; n++) {
            int aNum = sc.nextInt();
            int[] aScore = getScore(aNum);
            int bNum = sc.nextInt();
            int[] bScore = getScore(bNum);

            char answer = getWinner(aScore, bScore);
            System.out.println(answer); // 답 출력
        }
    }

    private static int[] getScore(int num) { // 점수 계산
        int[] score = new int[4]; // 각 점수에 맞는 배열 인덱스에 저장
        for (int i = 0; i < num; i++) {
            score[sc.nextInt() - 1]++;
        }
        return score;
    }

    private static char getWinner(int[] aScore, int[] bScore) { // 점수 비교
        char winner = 'D'; // 무승부
        for (int i = 3; i >= 0; i--) {
            if (aScore[i] > bScore[i]) {
                return 'A';
            } else if (bScore[i] > aScore[i]) {
                return 'B';
            }
        }
        return winner;
    }

}

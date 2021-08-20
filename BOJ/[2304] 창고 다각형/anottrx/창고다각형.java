// 출처: 백준저지 2304번 창고 다각형 https://www.acmicpc.net/problem/2304

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ2304 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 기둥의 개수 (1000 이하)
        int[][] pillar = new int[N][2];
        int max = 0;
        for (int i = 0; i < N; i++) { // 입력 받기
            st = new StringTokenizer(br.readLine(), " ");
            pillar[i][0] = Integer.parseInt(st.nextToken());
            pillar[i][1] = Integer.parseInt(st.nextToken());
            max = Math.max(max, pillar[i][1]); // 제일 높은 기둥 높이 저장
        }
        Arrays.sort(pillar, new Comparator<int[]>() { // 정렬
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                } else {
                    return o1[0] - o2[0];
                }
            }
        });

        int answer = 0; // 면적. 답
        int a = 0, b = 0; // max의 첫번째와 마지막 위치

        int p1 = pillar[0][0]; // 첫번째 기둥 위치
        int p2 = pillar[0][1]; // 첫번째 기둥 높이
        for (int i = 0; i < N; i++) { // 앞에서부터 max 발견할 때까지 계산
            if (pillar[i][1] == max) { // max 발견하면 계산하고 끝
                answer = answer + p2 * (pillar[i][0] - p1);
                a = pillar[i][0];
                break;
            }
            if (pillar[i][1] > p2) {
                answer = answer + p2 * (pillar[i][0] - p1);
                p1 = pillar[i][0];
                p2 = pillar[i][1];
            }
        }
        p1 = pillar[N - 1][0]; // 마지막 기둥 위치
        p2 = pillar[N - 1][1]; // 마지막 기둥 높이
        for (int i = N - 1; i >= 0; i--) { // 맨 뒤에서부터 max 발견할 때까지 계산
            if (pillar[i][1] == max) { // max 발견하면 계산하고 끝
                answer = answer + p2 * (p1 - pillar[i][0]);
                b = pillar[i][0];
                break;
            }
            if (pillar[i][1] > p2) {
                answer = answer + p2 * (p1 - pillar[i][0]);
                p1 = pillar[i][0];
                p2 = pillar[i][1];
            }
        }
        answer = answer + max * (b - a + 1); // (max 높이 * 너비) 더하기

        System.out.println(answer);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] score = new int[N];
        for (int i = 0; i < N; i++) { // 입력
            score[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(score); // 정렬
        long diff = 0;
        for (int i = 0; i < N; i++) { // 차례대로 차이 구해서 합하기
            diff = diff + Math.abs(i + 1 - score[i]);
        }
        System.out.println(diff);
    }
}

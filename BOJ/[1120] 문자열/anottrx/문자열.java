// 출처: 백준저지 1120번 문자열 https://www.acmicpc.net/problem/1120

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] input = (br.readLine()).split(" "); // A와 B. 길이는 최대 50. A의 길이는 B의 길이보다 작거나 같다. 알파벳 소문자.
        char[] A = input[0].toCharArray();
        char[] B = input[1].toCharArray();
        int answer = 50; // 답. 50으로 초기화

        int aLen = A.length;
        int bLen = B.length;
        int count = 0; // 같은 알파벳 수 세기
        for (int i = 0; i < (bLen - aLen + 1); i++) {
            count = 0;
            for (int j = i; j < (aLen + i); j++) {
                if (A[j - i] != B[j]) { // 다른 알파벳이 있다면 횟수 세기
                    count++;
                }
            }
            if (count < answer) {
                answer = count;
            }
        }

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
    }
}

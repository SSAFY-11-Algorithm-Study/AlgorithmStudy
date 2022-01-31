package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1205 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 주어지는 점수 개수
        int newScore = Integer.parseInt(st.nextToken()); // 태수의 새로운 점수
        int P = Integer.parseInt(st.nextToken()); // 랭킹 리스트에 올라 갈 수 있는 점수의 개수

        int answer = 1;
        if (N != 0) { // N이 0이라면 무조건 1이 답
            st = new StringTokenizer(br.readLine());
            ArrayList<Integer> arr = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                int num = Integer.parseInt(st.nextToken());
                arr.add(num);
            }
            if (N == P && newScore <= arr.get(arr.size() - 1)) {
                answer = -1;
            } else {
                for (int i = 0; i < arr.size(); i++) {
                    if (arr.get(i) > newScore) { // 태수의 새 점수보다 높다면 등수 1씩 더하기
                        answer++;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}

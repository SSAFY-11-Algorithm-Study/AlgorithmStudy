// https://velog.io/@dydgjs2016/%EB%B0%B1%EC%A4%80-1205-%EB%93%B1%EC%88%98-%EA%B5%AC%ED%95%98%EA%B8%B0%EA%B5%AC%ED%98%84 참고했습니다

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 주어지는 점수 개수
        int newScore = Integer.parseInt(st.nextToken()); // 태수의 새로운 점수
        int P = Integer.parseInt(st.nextToken()); // 랭킹 리스트에 올라 갈 수 있는 점수의 개수

        int answer = 1;
        if (N != 0) {
            st = new StringTokenizer(br.readLine());
            ArrayList<Integer> arr = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                int num = Integer.parseInt(st.nextToken());
                arr.add(num);
            }
            if (N == P && newScore <= arr.get(arr.size() - 1)) {
                answer = -1;
            } else {
                Collections.sort(arr);
                for (int i = arr.size() - 1; i >= 0; i--) {
                    if (arr.get(i) > newScore) {
                        answer++;
                    } else {
                        // if (arr.get(i) == newScore && answer >= P) {
                        // answer = -1;
                        // }
                        break;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}

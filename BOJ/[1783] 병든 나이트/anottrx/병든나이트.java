// 출처: 백준저지 1783번 병든 나이트 https://www.acmicpc.net/problem/1783

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()); // 세로. N과 M은 2,000,000,000보다 작거나 같은 자연수
        int M = Integer.parseInt(st.nextToken()); // 가로
        int answer = 0; // 답

        // 병든 나이트의 이동 횟수가 4번보다 적지 않다면, 이동 방법을 모두 한 번씩 사용해야 한다.
        // 이동 횟수가 4번보다 적은 경우(방문한 칸이 5개 미만)에는 이동 방법에 대한 제약이 없다.
        // -> 이동 횟수가 4번일때부터는 제약이 있다

        if (N == 1 || M == 1) {
            // 세로 또는 가로가 1이라면 무조건 1칸이다
            answer = 1;
        } else if ((N == 2 && M <= 4) || (N <= 4 && M == 2)) {
            /* 추측 */
            // 가로 또는 세로가 2이고, 다른 쪽이 2, 3, 4라면 무조건 2칸이다
            answer = 2;
        } else if (N == 2 || M == 2) {
            /* 추측 */
            // 가로 또는 세로가 2이고, 다른 쪽이 5 이상이라면 무조건 3칸이다
            // 4칸부터는 4가지를 1번씩 이동한다는 제약이 있는데, 이를 위해서는 세로 4 가로 7의 제약이 있기 때문이다
            answer = 3;
        } else { // 그 외의 경우
            /*
             * 오른쪽으로만 이동한다고 가정하면, 가로 6개마다 총 4칸의 이동이 가능한 것 같다... 
             * 그래서 (가로 / 6 * 4)라고 생각해봤는데 문제의 예제에 적용이 안됨...
             */
            answer = (M / 6) * 4;
        }

        bw.write(answer + "\n"); // 답 출력
        bw.flush();
        bw.close();

    }
}

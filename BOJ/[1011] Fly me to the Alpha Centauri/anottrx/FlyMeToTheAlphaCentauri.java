// https://lee1201zxc.tistory.com/entry/%EB%B0%B1%EC%A4%80-1011-Fly-me-to-the-Alpha-CentauriC%EC%96%B8%EC%96%B4 바탕으로 풀었습니다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1011 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // x지점
            int b = Integer.parseInt(st.nextToken()); // y지점

            sb.append(getMoveCount(b - a) + "\n"); // y지점-x지점 계산
        }
        System.out.println(sb.substring(0, sb.length() - 1));
    }

    private static long getMoveCount(int diff) {
        long end = 0; // 끝값
        int n = 0; // 2에 곱할 수
        while (true) {
            if (diff <= end) {
                break;
            }
            n++; // 1부터 시작
            end = end + 2 * n;
        }

        long start = end - 2 * n; // 시작값
        long len = (end - start + 1) / 2; // 시작+len < diff <= 끝
        if (start + len < diff) {
            return (n * 2);
        } else {
            return (n * 2 - 1);
        }
    }
}

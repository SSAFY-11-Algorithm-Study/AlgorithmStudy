// 출처: 백준저지 9375번 패션왕 신해빈 https://www.acmicpc.net/problem/9375

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ9375 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<String> cloth = new ArrayList<>();
        ArrayList<Integer> cnt = new ArrayList<>();

        int T = Integer.parseInt(st.nextToken()); // 테스트 케이스 (최대 100)
        int[] answer = new int[T];
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 입력 개수
            cloth = new ArrayList<>(); // 의상의 종류
            cnt = new ArrayList<>(); // 해당 의상 종류의 개수
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                String iCloth = st.nextToken(); // 의상의 이름 (사용 안 함)
                String iCType = st.nextToken(); // 의상의 종류
                boolean isIn = false; // 우선 입력받은 의상 종류가 존재하지 않는다고 생각
                for (int j = 0; j < cloth.size(); j++) {
                    if (cloth.get(j).equals(iCType)) { // cloth에 입력받은 의상 종류가 있다면
                        int n = cnt.get(j);
                        cnt.set(j, ++n); // 해당 의상 개수에 1 더하기
                        isIn = true;
                        break;
                    }
                }
                if (isIn == false) { // cloth에 존재하지 않는다면
                    cloth.add(iCType); // 의상 종류 cloth에 넣기
                    cnt.add(1); // 1개라고 개수 정하기
                }
            }
            int total = 1;
            for (int i = 0; i < cnt.size(); i++) {
                // (의상 종류 1개당 존재하는 의상 개수 + 1)를 매번 곱하기
                total = total * (cnt.get(i) + 1);
            }
            answer[tc] = total - 1; // 옷을 모두 안 입은 경우인 1을 빼기
        }
        for (int tc = 0; tc < T; tc++) {
            System.out.println(answer[tc]); // 답 출력
        }
    }
}

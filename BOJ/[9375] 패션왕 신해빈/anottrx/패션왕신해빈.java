// 출처: 백준저지 9375번 패션왕 신해빈 https://www.acmicpc.net/problem/9375

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.HashMap;

public class BOJ9375 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken()); // 테스트 케이스 (최대 100)
        for (int tc = 0; tc < T; tc++) {
            HashMap<String, Integer> cloth = new HashMap<>();
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 해빈이가 가진 의상의 수
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                String name = st.nextToken(); // 의상 이름은 필요없다
                String type = st.nextToken(); // 의상 종료를 입력 받는다
                if (cloth.containsKey(type)) { // 의상 종류가 HashMap에 있다면 값을 받아 1을 더한다
                    cloth.put(type, cloth.get(type) + 1);
                } else { // 의상 종류가 없다면 그냥 1을 입력한다
                    cloth.put(type, 1);
                }
            }
            int answer = 1;
            for (int c : cloth.values()) { // 의상 종류의 각 개수마다 1씩 더해서 곱한다. 이때 1은 해당 의상을 전혀 안 입은 경우
                answer = answer * (c + 1);
            }

            System.out.println(answer - 1); // 의상을 모두 안 입은 경우는 제외하기 때문에 1을 뺀다
        }
    }
}

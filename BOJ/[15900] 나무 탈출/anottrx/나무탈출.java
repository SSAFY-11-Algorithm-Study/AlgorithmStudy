/*
못 풀었습니다... 아래 코드는 입출력만입니다ㅠ
제 생각에는 양방향 가능한 링크드리스트를 만들어야할 것 같습니다.
그리고 해당 링크드리스트에 자식 유무, 게임 말의 개수(자식이 없다면 0으로, 있다면 1로 초기화)를 넣어줘야 할 것 같아요.
게임을 할 때는 자식이 없는 노드마다 위로 올라가야할 것 같은데요... 뭔가 역순으로 생각해야할 것 같기도 하고요...
구현을 못하겠습니다ㅠ 죄송합니다ㅠㅠㅠ
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15900 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String answer = "Yes"; // 답
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[][] tree = new int[N - 1][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            tree[i][0] = Integer.parseInt(st.nextToken());
            tree[i][1] = Integer.parseInt(st.nextToken());
        }

        System.out.println(answer);
    }
}

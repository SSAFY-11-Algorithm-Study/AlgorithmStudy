// 출처: 백준저지 10158번 개미 https://www.acmicpc.net/problem/10158 

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class BOJ10158 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int w = Integer.parseInt(st.nextToken()); // w와 h는 자연수. (2 ≤ w,h ≤ 40,000)
        int h = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken()); // 개미의 초기 위치 p와 q도 자연수. (0 < p < w)
        int y = Integer.parseInt(st.nextToken()); // (0 < q < h)
        int t = Integer.parseInt(br.readLine()); // 계산할 시간 (1 ≤ t ≤ 200,000,000)

        StringBuilder sb = new StringBuilder(); // 답 출력을 위함
        // 한칸씩 이동하면서 확인할 경우 -> 시간 초과
        // 한번에 가다가 벽에 부딪힐 때마다 확인한 경우 -> 시간 초과
        // 단순하게 좌표만 본다면, 개미의 x는 오른쪽으로 가다가 왼쪽으로 가기만 함. 개미의 y는 위로 가다가 아래로 가기만 함.
        x = (x + t) % (w * 2); // 오른쪽으로 가다가 왼쪽으로 가는지 확인하기 위해 w * 2
        y = (y + t) % (h * 2); // 위로 가다가 아래로 가는지 확인하기 위해 h * 2
        if (x >= w) { // 나머지가 w보다 작다면 x, 아니라면 w-x
            sb.append((w * 2) - x + " "); // 시간 초과를 막기 위해 즉시 sb에 넣는다
        } else {
            sb.append(x + " ");
        }
        if (y >= h) { // 나머지가 h보다 작다면 y, 아니라면 h-y
            sb.append((h * 2) - y);
        } else {
            sb.append(y);
        }

        bw.write(sb + "\n"); // 답 출력
        bw.flush();
        bw.close();
    }
}

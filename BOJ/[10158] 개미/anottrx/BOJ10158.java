// 자바8은 통과. 자바11은 시간 초과

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(br.readLine());

        x = (x + t) % (w * 2);
        y = (y + t) % (h * 2);
        if (x >= w) {
            bw.write((w * 2) - x + " ");
        } else {
            bw.write(x + " ");
        }
        if (y >= h) {
            bw.write(((h * 2) - y) + "\n");
        } else {
            bw.write(y + "\n");
        }
        bw.flush();
        bw.close();
    }
}

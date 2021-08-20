// 출처: 백준저지 1181번 단어 정렬 https://www.acmicpc.net/problem/1181

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.io.IOException;

public class BOJ1181 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine()); // 단어의 개수 N (1 ≤ N ≤ 20,000)
        String[] words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        Arrays.sort(words, new Comparator<String>() { // 정렬
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length()) {
                    return (o1).compareTo(o2);
                } else {
                    return o1.length() - o2.length();
                }
            }
        });

        bw.write(words[0] + "\n");
        for (int i = 1; i < N; i++) {
            if (!words[i].equals(words[i - 1])) {
                bw.write(words[i] + "\n");
            }
        }
        bw.flush();
        bw.close();
    }
}

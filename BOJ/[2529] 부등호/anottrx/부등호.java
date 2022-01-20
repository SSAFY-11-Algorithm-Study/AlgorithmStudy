import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<String> arr;
    static boolean[] visited;
    static char[] charInput;
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        charInput = new char[K];
        arr = new ArrayList<>();
        visited = new boolean[10];
        for (int i = 0; i < K; i++) {
            charInput[i] = (st.nextToken()).charAt(0);
        }
        for (int i = 0; i <= 9; i++) {
            dfs(i, 0, i + "");
        }
        Collections.sort(arr);
        System.out.println(arr.get(arr.size() - 1) + "\n" + arr.get(0));
    }

    private static void dfs(int n, int m, String str) {
        visited[n] = true;
        if (str.length() == K + 1) {
            arr.add(str);
        } else {
            for (int i = 0; i <= 9; i++) {
                if (!visited[i]) {
                    if ((charInput[m] == '<' && n < i) || (charInput[m] == '>' && n > i)) {
                        visited[i] = true;
                        dfs(i, m + 1, str + i);
                    }
                }
            }
        }
        visited[n] = false;
    }
}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<ArrayList<Integer>> tree;
    static int N, cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        tree = new ArrayList<>();
        cnt = 0;
        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            tree.get(from).add(to);
            tree.get(to).add(from);
        }

        dfs(1, 0, 0);

        bw.write((cnt % 2 == 0) ? "No\n" : "Yes\n");
        bw.flush();
        bw.close();
    }

    private static void dfs(int cur, int parent, int len) {
        for (int i = 0; i < tree.get(cur).size(); i++) {
            if (tree.get(cur).get(i) != parent) {
                dfs(tree.get(cur).get(i), cur, len + 1);
            }
        }
        if (tree.get(cur).size() == 1 && cur != 1) {
            cnt += len;
        }
    }
}

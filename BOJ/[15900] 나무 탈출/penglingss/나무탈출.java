import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj15900 {
    static int rootCnt = 0;
    static boolean[] visit;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        ArrayList<Integer>[] nodeList = new ArrayList[N + 1];
        visit = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            nodeList[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            nodeList[node1].add(node2);
            nodeList[node2].add(node1);
        }

        dfs(nodeList, 1, 0);


        if(rootCnt % 2 != 0) System.out.println("Yes");
        else System.out.println("No");

    }

    private static void dfs(ArrayList<Integer>[] nodeList, int cur, int cnt) {
        for (int i = 0; i < nodeList[cur].size(); i++) {
            int idx = nodeList[cur].get(i);
            if (!visit[idx]) {
                visit[idx] = true;
                dfs(nodeList, idx, cnt + 1);
            }
        }
        if (nodeList[cur].size() == 1) {
            rootCnt += cnt;
        }
    }

}

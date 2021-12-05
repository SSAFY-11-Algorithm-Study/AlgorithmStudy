import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ10282 {

    static int n;

    static class Node implements Comparable<Node> {
        int end, time;

        public Node(int b, int s) {
            this.end = b;
            this.time = s;
        }

        public int compareTo(Node o) {
            return time - o.time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); // 컴퓨터 개수
            int d = Integer.parseInt(st.nextToken()); // 의존성 개수
            int c = Integer.parseInt(st.nextToken()); // 해킹당한 컴퓨터 번호. start

            ArrayList<Node>[] comArr = new ArrayList[n + 1];
            for (int i = 0; i < n + 1; i++) {
                comArr[i] = new ArrayList<>();
            }

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                comArr[b].add(new Node(a, s));
            }

            String answer = dijkstra(comArr, c);
            sb.append(answer);
        }
        System.out.println(sb.substring(0, sb.length() - 1));
    }

    private static String dijkstra(ArrayList<Node>[] comArr, int c) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(c, 0));
        int[] dist = new int[n + 1];
        Arrays.fill(dist, 10000001);
        dist[c] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist[cur.end] >= cur.time) {

                for (Node node : comArr[cur.end]) {
                    if (dist[node.end] > dist[cur.end] + node.time) {
                        dist[node.end] = dist[cur.end] + node.time;
                        pq.offer(new Node(node.end, dist[node.end]));
                    }
                }
            }
        }

        int cnt = 0; // 총 감염되는 컴퓨터의 수
        int lastTime = 0; // 마지막 컴퓨터가 감염되기까지 걸리는 시간
        for (int i = 1; i < n + 1; i++) {
            if (dist[i] != 10000001) {
                cnt++;
                lastTime = Math.max(lastTime, dist[i]); // 가장 오래 걸리는 시간이 답
            }
        }
        return String.valueOf(cnt) + " " + lastTime + "\n";
    }
}

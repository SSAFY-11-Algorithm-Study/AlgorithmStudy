import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ18352 {

    static int N;
    static int INF = 300002;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 도시의 개수
        int M = Integer.parseInt(st.nextToken()); // 도로의 개수
        int K = Integer.parseInt(st.nextToken()); // 거리 정보
        int X = Integer.parseInt(st.nextToken()); // 출발 도시의 번호

        ArrayList<Integer>[] cityList = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            cityList[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            cityList[from].add(to);
        }

        ArrayList<Integer> answer = dijkstra(X, K, cityList);
        if (answer.size() == 0) { // 최단 거리가 K인 도시가 하나도 존재하지 않으면 -1을 출력
            System.out.println(-1);
        } else {
            for (int i = 0; i < answer.size(); i++) {
                System.out.println(answer.get(i));
            }
        }
    }

    private static ArrayList<Integer> dijkstra(int start, int K, ArrayList<Integer>[] cityList) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int city : cityList[cur]) {
                if (dist[city] > dist[cur] + 1) {
                    dist[city] = dist[cur] + 1; // 도시와 도시 사이의 거리 차이는 항상 1
                    q.offer(city);
                }
            }
        }

        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (dist[i] == K) {
                answer.add(i); // 최단 거리가 K인 모든 도시의 번호
            }
        }
        Collections.sort(answer); // 오름차순
        return answer;
    }
}

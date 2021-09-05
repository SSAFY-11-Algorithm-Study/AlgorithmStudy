// 출처: 백준저지 1916번 최소비용 구하기 https://www.acmicpc.net/problem/1916

/* 
https://subbak2.tistory.com/55 바탕으로 풀었습니다

Priority Queue(우선순위 큐): 데이터가 들어온 순서가 아닌 우선순위가 높은 데이터부터 나간다
우선순위큐를 만들 때 Comparable 사용해서 우선순위를 정한다
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1916_2 {
    static int N, M, start, end;
    static int[] distance;
    static ArrayList<Edge>[] adjList;

    static class Edge implements Comparable<Edge> {
        int e;
        int weight;

        Edge(int e, int weight) {
            this.e = e;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) { // 우선순위큐에 넣을 때 가중치 비교
            return weight - o.weight;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<Edge>(); // 5개의 정점에 대해서 연결리스트를 우선 만든다 (이때는 모두 0개)
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjList[a].add(new Edge(b, w));
        }
        // adjList[] size: 1:4개, 2:1개, 3:2개, 4:1개, 5:0개

        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        dijkstra(start);

        bw.write(distance[end] + "\n");
        bw.flush();
        bw.close();
    }

    static void dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        distance[start] = 0;
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge now = pq.poll(); // 빼는 순서: 1 4 2 3 5

            if (now.e == end) {
                break;
            }

            if (now.weight <= distance[now.e]) { // 더 작은 가중치일 때
                int len = adjList[now.e].size(); // 해당 정점에서 출발하는 간선의 수 (1:4개, 2:1개, 3:2개, 4:1개, 5:0개)

                for (int i = 0; i < len; i++) {
                    Edge next = (Edge) adjList[now.e].get(i); // 해당 정점에서 출발하는 간선 (정점, 가중치)

                    if (distance[next.e] > now.weight + next.weight) { // 거리가 더 작을 경우
                        distance[next.e] = now.weight + next.weight;
                        pq.add(new Edge(next.e, distance[next.e])); // 더해지는 경우: 1:(2 3 4 5) 4(5) 
                    }
                }
            }

        }
    }
}

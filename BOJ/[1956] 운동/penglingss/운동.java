import java.util.*;

class Main {
    static int V, E, answer;
    static int[][] distance;
    static int INF = 10000000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        V = sc.nextInt();
        E = sc.nextInt();
        distance = new int[V + 1][V + 1];

        for (int i = 1; i <= V; i++) {
            Arrays.fill(distance[i], INF);
        }

        for (int i = 0; i < E; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int cost = sc.nextInt();
            distance[start][end] = cost;
        }

        floydWarshall();

        answer = INF;
        for (int i = 1; i <= V; i++) {
            answer = Math.min(distance[i][i], answer);
        }

        if (answer == INF) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    private static void floydWarshall() {
        for (int k = 1; k <= V; k++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    distance[i][j] = Math.min(distance[i][k] + distance[k][j], distance[i][j]);
                }
            }
        }
    }
}

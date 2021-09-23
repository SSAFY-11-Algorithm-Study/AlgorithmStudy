import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static HashMap<Integer, Integer> map;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        map = new HashMap<>();

        for (int i = 0; i < N + M; i++) {
            map.put(sc.nextInt(), sc.nextInt());
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        int cnt = 1;
        Queue<Integer> q = new LinkedList<>();
        boolean[] visit = new boolean[101];
        visit[1] = true;
        q.add(1);

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                for (int j = 1; j <= 6; j++) {
                    int next = cur + j;
                    if(next == 100) return cnt;
                    if(next > 100) break;
                    if(visit[next]) continue;
                    if (map.containsKey(next)) {
                        next = map.get(next);
                        if(visit[next]) continue;
                    }

                    q.add(next);
                    visit[next] = true;
                }
            }
            cnt++;
        }
        return cnt;
    }
}

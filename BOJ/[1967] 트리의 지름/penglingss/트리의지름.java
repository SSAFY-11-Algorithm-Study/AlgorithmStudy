import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static class Node {
        int index;
        int depth;

        public Node(int index, int depth) {
            this.index = index;
            this.depth = depth;
        }
    }
    static int n, maxIndex, max;
    static ArrayList<Node>[] nodeListArray;
    static boolean[] visit;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        nodeListArray = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            nodeListArray[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            int parent = sc.nextInt();
            int child = sc.nextInt();
            int cost = sc.nextInt();
            nodeListArray[parent].add(new Node(child, cost));
            nodeListArray[child].add(new Node(parent, cost));
        }

        visit = new boolean[n + 1];
        visit[1] = true;
        dfs(1, 0);

        visit = new boolean[n + 1];
        visit[maxIndex] = true;
        dfs(maxIndex, 0);

        System.out.println(max);
    }

    public static void dfs(int index, int count) {
        if (count > max) {
            max = count;
            maxIndex = index;
        }

        for (Node node : nodeListArray[index]) {
            if (visit[node.index]) continue;
            visit[node.index] = true;
            dfs(node.index, count + node.depth);
        }
    }

}

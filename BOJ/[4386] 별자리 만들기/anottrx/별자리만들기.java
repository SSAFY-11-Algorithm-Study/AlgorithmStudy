// https://data-make.tistory.com/534 참고하여 풀었습니다

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int n, parents[];
    static ArrayList<Edge> starEdgeList;

    static class Star {
        int index;
        double x, y;

        public Star(int index, double x, double y) {
            this.index = index;
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge> {
        int start, end;
        double length;

        public Edge(int start, int end, double length) {
            this.start = start;
            this.end = end;
            this.length = length;
        }

        @Override
        public int compareTo(Edge o) {
            // if (this.length - o.length > 0) {
            // return 1;
            // } else if (this.length == o.length) {
            // return 0;
            // } else {
            // return -1;
            // }
            return (int) (this.length - o.length);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        Star[] starList = new Star[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            double x = Float.parseFloat(st.nextToken());
            double y = Float.parseFloat(st.nextToken());
            starList[i] = new Star(i, x, y);
        }

        starEdgeList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Star cur = starList[i];
            for (int j = i + 1; j < n; j++) {
                Star next = starList[j];
                double length = Math.sqrt(Math.pow(next.x - cur.x, 2) + Math.pow(next.y - cur.y, 2));
                starEdgeList.add(new Edge(i, j, length));
            }
        }

        Collections.sort(starEdgeList);

        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        double result = 0;
        int count = 0;
        for (Edge edge : starEdgeList) {
            if (union(edge.start, edge.end)) {
                result = result + edge.length;
                count++;
                if (count == n - 1) {
                    break;
                }
            }
        }

        System.out.printf("%.2f", result);
    }

    public static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) {
            return false;
        }
        parents[bRoot] = aRoot;
        return true;
    }

    public static int find(int a) {
        if (parents[a] == a) {
            return a;
        }
        return parents[a] = find(parents[a]);
    }
}

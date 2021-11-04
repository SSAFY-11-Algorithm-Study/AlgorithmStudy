package com.BOJ;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    static class Node implements Comparable<Node> {
        int from;
        int to;
        int cost;

        public Node(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static int[] parent;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        PriorityQueue<Node> pq = new PriorityQueue<>();
        parent = new int[N + 1];
        for (int i = 0; i < M; i++) {
            pq.add(new Node(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }

        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        int answer = 0;
        int count = 0;
        while (count < N - 1) {
            Node node = pq.poll();
            if (find(node.from) != find(node.to)) {
                union(node.from, node.to);
                count++;
                if(count < N - 1) answer += node.cost;
            }
        }

        System.out.println(answer);
    }

    public static int find(int nodeNum) {
        if (parent[nodeNum] == nodeNum) return nodeNum;
        else return parent[nodeNum] = find(parent[nodeNum]);
    }

    public static void union(int from, int to) {
        parent[find(to)] = find(from);
    }
}

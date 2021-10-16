package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1991 {

    static StringBuilder sb;
    static char[][] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        tree = new char[27][2];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int node = st.nextToken().charAt(0) - 'A';
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);
            tree[node][0] = left;
            tree[node][1] = right;
        }

        sb = new StringBuilder();

        preorder('A');
        sb.append("\n");
        inorder('A');
        sb.append("\n");
        postorder('A');

        System.out.println(sb.toString());
    }

    private static void preorder(char c) {
        if (c == '.') {
            return;
        }
        sb.append(c);
        int n = c - 'A';
        preorder(tree[n][0]);
        preorder(tree[n][1]);
    }

    private static void inorder(char c) {
        if (c == '.') {
            return;
        }
        int n = c - 'A';
        inorder(tree[n][0]);
        sb.append(c);
        inorder(tree[n][1]);
    }

    private static void postorder(char c) {
        if (c == '.') {
            return;
        }
        int n = c - 'A';
        postorder(tree[n][0]);
        postorder(tree[n][1]);
        sb.append(c);
    }
}

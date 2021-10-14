import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static class Node {
        char data;
        char left;
        char right;

        public Node(char data, char left, char right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    static int N;
    static ArrayList<Node>[] tree;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        tree = new ArrayList[N];

        sc.nextLine();

        for (int i = 0; i < N; i++) {
            String[] s = sc.nextLine().split(" ");
            int idx = s[0].charAt(0) - 'A';

            tree[idx] = new ArrayList<>();
            tree[idx].add(new Node(s[0].charAt(0), s[1].charAt(0), s[2].charAt(0)));
        }

        preorder(0);
        System.out.println();
        inorder(0);
        System.out.println();
        postorder(0);

    }

    private static void preorder(int idx) {
        if(idx == -19) return;

        Node n = tree[idx].get(0);
        System.out.print(n.data);

        preorder(n.left - 'A');
        preorder(n.right - 'A');
    }

    private static void inorder(int idx) {
        if(idx == -19) return;

        Node n = tree[idx].get(0);

        inorder(n.left - 'A');
        System.out.print(n.data);
        inorder(n.right - 'A');
    }

    private static void postorder(int idx) {
        if(idx == -19) return;

        Node n = tree[idx].get(0);

        postorder(n.left - 'A');
        postorder(n.right - 'A');
        System.out.print(n.data);
    }
}

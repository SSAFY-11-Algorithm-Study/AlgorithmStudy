import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            q.add(i + 1);
        }

        System.out.print("<");
        while (q.size() > 1) {
            for (int i = 0; i < K - 1; i++) {
                q.add(q.peek());
                q.poll();
            }

            System.out.print(q.poll() + ", ");
        }
        System.out.print(q.poll());
        System.out.println(">");
    }

}

import java.util.ArrayList;
import java.util.Scanner;

public class boj2635 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        ArrayList<Integer> answer = new ArrayList<>();
        int maxCnt = 0;

        for (int i = N / 2; i <= N; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            int cur = N;
            list.add(cur);
            int next = i;
            while (true) {
                list.add(next);
                int tmp = cur - next;
                if (tmp < 0) break;
                cur = next;
                next = tmp;
            }
            if (list.size() > maxCnt) {
                maxCnt = list.size();
                answer = list;
            }
        }

        System.out.println(maxCnt);
        for (int i = 0; i < maxCnt; i++) {
            System.out.print(answer.get(i) + " ");

        }
    }
}

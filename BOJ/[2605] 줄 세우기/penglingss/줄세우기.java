import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        ArrayList<Integer> list = new ArrayList<>();
        sc.nextInt();
        list.add(1);

        for (int i = 1; i < N; i++) {
            int num = sc.nextInt();
            list.add(list.size() - num, i + 1);
        }

        for (int i = 0; i < N; i++) {
            System.out.print(list.get(i) + " ");
        }
    }
}

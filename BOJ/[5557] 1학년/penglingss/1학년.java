import java.util.*;

public class Main {
    static int N;
    static int[] num;
    static long[] count = new long[21];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        num = new int[N];
        for (int i = 0; i < N; i++) {
            num[i] = sc.nextInt();
        }

        count[num[0]] = 1;
        cal(0);
        System.out.println(count[num[N - 1]]);
    }

    public static void cal(int index) {
        if (index == N - 2)
            return;
        long[] temp = new long[21];

        for (int i = 0; i <= 20; i++) {
            if (count[i] != 0) {
                if (i - num[index + 1] >= 0)
                    temp[i - num[index + 1]] += count[i];
                if (i + num[index + 1] <= 20)
                    temp[i + num[index + 1]] += count[i];
            }
        }
        count = temp.clone();
        cal(index + 1);
    }
}

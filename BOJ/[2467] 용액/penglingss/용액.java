import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        int[] answer = new int[2];
        int max = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            int left = i + 1;
            int right = N - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                int sum = arr[i] + arr[mid];

                if (Math.abs(sum) < max) {
                    answer[0] = arr[i];
                    answer[1] = arr[mid];
                    max = Math.abs(sum);
                }

                if (sum < 0) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        System.out.println(answer[0] + " " + answer[1]);
    }
}

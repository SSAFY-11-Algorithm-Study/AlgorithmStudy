import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int sum = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();

            if (num > 0) {
                sum += num;
                stack.push(num);
            } else {
                sum -= stack.pop();
            }
        }

        System.out.print(sum);

    }
}

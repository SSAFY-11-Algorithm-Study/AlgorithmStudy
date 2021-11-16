import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int t = 0; t < T; t++) {
            int n = sc.nextInt();

            int n1 = n / 2;
            int n2 = n - n1;

            while (true) {
                if (isPrime(n1) && isPrime(n2)) {
                    break;
                } else {
                    n1++;
                    n2--;
                }
            }
            System.out.println(n2 + " " + n1);
        }
    }

    public static boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}

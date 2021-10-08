import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        if (N == 1){
            System.out.println(2);
            return;
        }

        while (true) {
            if (isPrime(N) && isPalindrom(N)) {
                System.out.println(N);
                break;
            }
            N++;
        }
    }

    static boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++){
            if (n % i == 0){
                return false;
            }
        }
        return true;
    }

    static boolean isPalindrom(int n) {
        StringBuilder str = new StringBuilder();
        str.append(n);

        return str.toString().equals(str.reverse().toString());
    }
}

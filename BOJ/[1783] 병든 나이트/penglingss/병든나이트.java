import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        if(N == 1) {
            System.out.println(1);
            return;
        }
        if(N == 2) {
            System.out.println(Math.min(4, (M + 1) / 2));
            return;
        }
        if(M < 7) {
            System.out.println(Math.min(4, M));
            return;
        }
        System.out.println(M-2);
    }
}

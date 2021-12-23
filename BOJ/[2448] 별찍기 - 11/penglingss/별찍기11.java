import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        String[] starArray = new String[n];
        starArray[0] = "  *  ";
        starArray[1] = " * * ";
        starArray[2] = "*****";

        for (int i = 1; 3 * (int) Math.pow(2, i) <= n; i++) {
            makeStar(i, starArray);
        }

        for (int i = 0; i < n; ++i) {
            System.out.println(starArray[i]);
        }
    }

    private static void makeStar(int count, String[] arr) {
        int bottom = 3 * (int) Math.pow(2, count);
        int middle = bottom / 2;

        for (int i = middle; i < bottom; ++i) {
            arr[i] = arr[i - middle] + " " + arr[i - middle];
        }

        StringBuilder space = new StringBuilder();
        while (space.length() < middle) {
            space.append(" ");
        }
        for (int i = 0; i < middle; ++i) {
            arr[i] = space + arr[i] + space;
        }
    }
}

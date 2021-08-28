package time5;

import java.util.ArrayList;
import java.util.Scanner;

public class BJ14696_play {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int round = sc.nextInt();

		point: for (int i = 0; i < round; i++) {
			ArrayList<Integer> acard = new ArrayList<>();
			ArrayList<Integer> bcard = new ArrayList<>();
			int a = sc.nextInt();
			for (int j = 0; j < a; j++) {
				acard.add(sc.nextInt());
			}
			int b = sc.nextInt();
			for (int j = 0; j < b; j++) {
				bcard.add(sc.nextInt());
			}

			for (int j = 4; j > 0; j--) {
				int acount = 0;
				int bcount = 0;
				for (int k = 0; k < acard.size(); k++) {
					if (acard.get(k) == j) {
						acount++;
					}
				}
				for (int k = 0; k < bcard.size(); k++) {
					if (bcard.get(k) == j) {
						bcount++;
					}
				}
				if (acount > bcount) {
					System.out.println("A");
					continue point;
				}
				else if (acount < bcount) {
					System.out.println("B");
					continue point;
				}

			}
			System.out.println("D");
		}
	}
}

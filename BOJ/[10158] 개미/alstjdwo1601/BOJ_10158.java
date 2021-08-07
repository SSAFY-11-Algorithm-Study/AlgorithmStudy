package week2;

import java.util.Scanner;

//백준 10158 개미
public class BOJ_10158 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int w = sc.nextInt();
		int h = sc.nextInt();

		int x = sc.nextInt();
		int y = sc.nextInt();

		int time = sc.nextInt();
		double gradient1 = h/w;
		double gradient2 = y/x;

		//기울기가 같은 경우에는 
		
		//이부분 모르겠습니다.. ㅠ
		if(gradient1 == gradient2) {
			
		}

		else {
			//x는 w의 두배가 지나면 원위치하는데 현재위치 더해서 time으로 나눈 나머지가 time 이후 위치
			x = (2*w + x) % time ;
			y = (2*h + y) % time;
		}


		System.out.printf("%d %d", x ,y);
	}

}

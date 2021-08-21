package week4;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ2564_경비원 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int width = sc.nextInt();
		int height = sc.nextInt();
		ArrayList<int[]> list = new ArrayList<>();

		int numStore = sc.nextInt();

		for(int i = 0 ; i < numStore ; i ++) {
			int position = sc.nextInt();
			int dist = sc.nextInt();

			//리스트에 동서남북정보 , 좌표를 담아둠
			if(position ==1) 
				list.add(new int[] {1,0,dist});

			else if(position ==2)
				list.add(new int[] {2,height,dist});

			else if(position ==3) 
				list.add(new int[] {3,dist,0});

			else 
				list.add(new int[] {4,dist,width});

		}

		int optimalDist = 0;
		//경비원의 좌표
		int securityPos = sc.nextInt();
		int securityDist = sc.nextInt();

		//북
		if(securityPos ==1) {
			for(int [] a : list) {
				if(a[0] ==1)
					optimalDist += Math.abs(a[2] - securityDist);
				
				else if(a[0] ==2) 
					optimalDist += height + Math.min(a[2] + securityDist, (width - a[2]) +(width - securityDist));
					
				else if(a[0] ==3)
					optimalDist += securityDist + a[1];
				
				else
					optimalDist += (width-securityDist) + a[1];	
			}
		}
		//남
		else if(securityPos ==2) {
			for(int [] a : list) {
				if(a[0] ==1) 
					optimalDist += height + Math.min(a[2] + securityDist, (width - a[2]) +(width - securityDist));
				
				else if(a[0] ==2) 
					optimalDist += Math.abs(a[2] - securityDist);
				
				else if(a[0] ==3)
					optimalDist += (height - a[1]) + securityDist;

				else
					optimalDist += (height - a[1]) + (width - securityDist);
				
			}
		}
		//서
		else if(securityPos ==3) {
			for(int [] a : list) {
				if(a[0] ==1)
					optimalDist += a[2] + securityDist;
				
				else if(a[0] ==2) 
					optimalDist += a[2] + (height - securityDist);
				
				else if(a[0] ==3)
					optimalDist += Math.abs(a[1] - securityDist);
				
				else 
					optimalDist += width + Math.min(a[1] + securityDist, (height -a[1]) + (height - securityDist));
			}
		}
		//동
		else {
			for(int [] a : list) {
				if(a[0] ==1)
					optimalDist += (width - a[2]) + securityDist;
				
				else if(a[0] ==2) 
					optimalDist += ( height - securityDist ) + (width - a[2]);
					
				else if(a[0] ==3) 
					optimalDist += width + Math.min(a[1] + securityDist, (height -a[1]) + (height - securityDist));
				
				else
					optimalDist = Math.abs(a[1] - securityDist);
			}
		}


		System.out.println(optimalDist);

	}
}

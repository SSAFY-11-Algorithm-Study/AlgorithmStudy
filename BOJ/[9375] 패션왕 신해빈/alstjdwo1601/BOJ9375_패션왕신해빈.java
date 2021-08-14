package week3;

import java.util.HashMap;
import java.util.Scanner;

public class BOJ9375_패션왕신해빈 {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		
		
		int tc = sc.nextInt();
		for(int a = 0 ; a < tc ; a++) {
			
			HashMap<String, Integer> hm = new HashMap<String, Integer>();
			int num = sc.nextInt();
			
			for(int i = 0 ; i < num ; i++) {
				String name = sc.next();
				String type = sc.next();
				
				//의상 타입이 해시맵안에 있으면 value +1 해주고 없으면 1로 넣어줌
				if(hm.containsKey(type))
					hm.replace(type, hm.get(type) +1);
				else 
					hm.put(type, 1);
			}
	        
	        int cnt = 1;
	        //hm.values를 통해 모든 타입의 value들을 받아옴
	        for(int value : hm.values())
	        	//모자가 2개면 1번모자쓰기 , 2번모자쓰기 , 안쓰기 -> 3개씩 경우 생김
	        	cnt *= (value+1);
	        
	        //아무것도 안입는 경우는 제외
	        cnt = cnt-1;
	        
	        System.out.println(cnt);
		}
	}
}

import java.util.Scanner;

public class BOJ_7568 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int num = sc.nextInt();
		int[][] people = new int[num][2];
		
		int max_w=0;
		int max_w_index=0;
		
		int max_h=0;
		int max_h_index=0;
		
		int rank=1;
		int num_unranked=num;
		int[] ranking = new int[num];
	
		
		for(int i=0; i<num; i++) {
			for (int j = 0; j < 2; j++) {
				people[i][j]=sc.nextInt();
			}
		}
		sc.close();
		//입력값 받기 완료
		
		
		//모두 등수가 정해질 때까지 반복
		while(num_unranked>0) {
			//몸무게 MAX 구하기
			for (int i = 0; i < num; i++) {
				if(people[i][0] > max_w) {
					max_w=people[i][0];
					max_w_index=i;
				}
					
			}
			//키 MAX 구하기
			for (int i = 0; i < num; i++) {
				if(people[i][1] > max_h)
					max_h=people[i][1];
					max_h_index=i;
			}
		
			
			//1등 (한명만)
			if(max_w_index == max_h_index) {
				ranking[max_w_index]=rank++;
				people[max_w_index]= new int[] {0, 0}; //다음 반복 때 영향 X 하도록
				num_unranked--;
			}
			
			//공동 1등
			//people[i][0]이 max이지만 people[i][1]이 max가 아니면, 
			//그 i 포함해서 people[i][1]보다 키가 큰 사람들이 모두 공동순위
			else { 
				ranking[max_w_index]=rank;
				num_unranked--;
				
				int n = 1;
				for(int i=0; i<num; i++) {
				if (people[max_w_index][1] < people[i][1]) {
					ranking[i]=rank;
					people[i]= new int[] {0, 0};
					num_unranked--;
					n++;
				   }
				 }
				people[max_w_index]=new int[] {0, 0};
				rank+=n;
			   }
		}
		for(int i=0; i<ranking.length; i++)
			System.out.printf("%d ", ranking[i]);
   }
}

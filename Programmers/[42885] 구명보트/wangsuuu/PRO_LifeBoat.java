import java.util.ArrayList;
import java.util.Arrays;

public class PRO_LifeBoat {
	
	static int[] people;
	static int[] ppl;
	
	public static void main(String[] args) {
		
		people= new int[] {70, 50, 80, 50};
		ppl = new int[] {70, 50, 80};
		
		System.out.println(solution(people, 100));
		System.out.println(solution(ppl, 100));
		
	} 
	
	public static int solution(int[] people, int limit) {
        
        Arrays.sort(people); //무게 순으로 정렬
        
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0; i<people.length; i++) {
        	list.add(people[i]);
        }//데이터를 옮겨 담음
        
       int cnt=0;
       
       //최대한 2명씩 태우되, limit까지 꽉꽉 채워서 넣기 -> 맨 처음과 맨 끝을 더함
       while(!list.isEmpty()) {
    	   if(list.size()>1 && list.get(0) + list.get(list.size()-1) <=limit) { //구명보트에 태울 수 있다면
    		   cnt++;
    		   list.remove(0); list.remove(list.size()-1);
    	   } else { //태울 수 없다면
    		   cnt++;
    		   list.remove(list.size()-1); //맨 끝에 것 혼자만 태워서 보냄
    	   }
    	   
       }
        return cnt;
	  }

}

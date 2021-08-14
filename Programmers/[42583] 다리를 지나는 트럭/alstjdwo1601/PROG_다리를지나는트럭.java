package week3;

import java.util.LinkedList;
import java.util.Queue;

public class PROG_다리를지나는트럭 {
	class Solution {
	    public int solution(int bridge_length, int weight, int[] truck_weights) {
	        int answer = 0;
	        Queue<Integer> beforeQ = new LinkedList<>();
	        Queue<Integer> ingQ = new LinkedList<>();
	        Queue<Integer> afterQ = new LinkedList<>();
	        
	        //대기트럭큐에 값 넣어둠
	        for(int i = 0 ; i < truck_weights.length ; i++)
	            beforeQ.add(truck_weights[i]);
	        
	        
	        //다리위에서 몇초를 보냈는지 체크하는 배열
	        int [] time = new int[truck_weights.length+1];
	        boolean [] chk = new boolean[truck_weights.length+1];
	    
	        
	        int weight_sum = 0;
	        int in_cnt = 0;
	        
	        if(truck_weights.length ==1)
	            answer = bridge_length +1;
	        else{
	            //트럭이 전부 건너면 종료
	            while(afterQ.size() < truck_weights.length){
	                //1. 다리에서 나갈수 있는 트럭있으면 나감
	                for(int i = 1 ; i <= time.length ; i++){
	                    if(time[i] == bridge_length){
	                        int temp = ingQ.poll();
	                        afterQ.add(temp);
	                        time[i] = 0;
	                        chk[i] = false;
	                        break;
	                    }
	                }
	            
	                //2. 다리 한계 무게를 넘지 않으면 대기트럭에서 하나 뽑아서 진행큐에 넣음
	                if(weight_sum <= weight){
	                    //트럭 다리에 올리기
	                    int truck = beforeQ.poll();
	                    ingQ.add(truck);
	                    weight_sum += truck;
	                
	                    //올라간 트럭은 시간 증가 , true 체크
	                    in_cnt++;
	                    chk[in_cnt] = true;
	                    for(int i = 1 ; i <= in_cnt ; i++){
	                        //현재 다리위에 있는 트럭만 1씩 시간을 추가
	                        if(chk[i]) time[i]++;
	                    }
	                }
	            
	                //3. 올라갈 트럭이 없다면(다리가 다 찼으면) 올라간애만 시간 추가
	                else{
	                    for(int i = 1 ; i <= in_cnt ; i++)
	                        if(chk[i]) time[i]++;
	                }
	            
	                //한사이클이 종료되면 시간 증가
	                answer++;
	            }
	        }
	        
	        
	        
	        return answer;
	    }
	}
}

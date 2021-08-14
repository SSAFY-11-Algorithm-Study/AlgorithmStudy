package time3;

import java.util.LinkedList;
import java.util.Queue;

public class PM_truck {

	private static int bridge_length = 2;
	private static int weight = 10;
	private static int[] truck_weights = {7,4,5,6};
	
	
	public static void main(String[] args) {
		
		int answer = 0;
		int temp = 0;
		Queue<Integer> trucks = new LinkedList<>();
		Queue<Integer> bridge = new LinkedList<>();

		for(int truck : truck_weights) {
			trucks.offer(truck);
		}
		
		while(!trucks.isEmpty()) {// trucks가 빌 때 까지
			if(bridge.isEmpty()) { // 다리에 차가 없으면 일단 들어감.
				temp += trucks.peek(); 
				bridge.offer(trucks.poll());
				answer++;
			} else if(bridge.size() == bridge_length) {//꽉 찼을 떄. 빼주는 것만 할때는 시간 더하기 x
				temp -= bridge.poll();					// 더하기랑 동시에 진행.
			} else {// 비어있진 않지만 꽉 차지 않았을때
				if(temp + trucks.peek() > weight) { //다리가 무게를 못견디면 차 못들어감.
					bridge.offer(0);// 0을 넣어 줘서 무게 영향 x 빈곳.
					answer++;
				} else {// 차 들어갈 수 있을 때
					temp += trucks.peek();
					bridge.offer(trucks.poll());
					answer++;
				}
			}
		}
		answer = answer + bridge_length; //마지막 차가 들어가고 나갈 때 까지 시간 더해줌.
		System.out.println(answer); // 
		
	}

}

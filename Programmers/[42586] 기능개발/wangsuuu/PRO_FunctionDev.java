import java.util.Arrays;
import java.util.LinkedList;

public class PRO_FunctionDev {

	public static void main(String[] args) { //test case

		int[] progresses1 = {93, 30, 55};
		int[] speeds1 = {1, 30, 5};
		int[] progresses2 = {95, 90, 99, 99, 80, 99};
		int[] speeds2 = {1, 1, 1, 1, 1, 1};
		
		int[] result1 = solution(progresses1, speeds1);
		int[] result2 = solution(progresses2, speeds2);
		
		System.out.println(Arrays.toString(result1));
		System.out.println(Arrays.toString(result2));
	}

	public static int[] solution(int[] progresses, int[] speeds) {
		
		LinkedList<Integer> p = new LinkedList<>();
		LinkedList<Integer> s = new LinkedList<>();
		LinkedList<Integer> result = new LinkedList<>(); //결과값을 담는 리스트
		
		for(int i=0; i<progresses.length; i++) {
			p.add(progresses[i]);
			s.add(speeds[i]);
		} //linkedlist에 옮겨 담음
		
		
		int cnt=0;
		while(!p.isEmpty()) {
			if(p.get(0)<100) {
				for(int i=0; i<p.size(); i++) {
					p.set(i, p.get(i)+s.get(i));
				}
			}//맨 처음 값이 100 이상이 될때까지 시간에 따라 모든 값에 작업속도 값을 더함
			else { //맨 처음값이 배포 가능하다면
				
				while(p.size()!=0 && p.get(0)>=100) { 
						p.remove(0);
						s.remove(0);
						cnt++;
					
				}//뒤에 있는 값도 이미 100이 넘었다면, 한꺼번에 배포 가능하므로.
				result.add(cnt);
				cnt=0; //초기화 필요
			}
		}
		
		int[] answer = new int[result.size()];
		for(int i=0; i<answer.length; i++)
			answer[i]=result.get(i);
		
		return answer;
		
	}

}

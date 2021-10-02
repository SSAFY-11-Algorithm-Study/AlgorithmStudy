//0이 연속 k개 이상 나오면, 더 이상 못 건넘.
//직관적으로 짰더니 시간초과 나서 이분탐색으로..
//처음부터 stone 최댓값이 2억이라는 것에서 이분탐색이라는 감 잡기!

class Solution {
	
    public int solution(int[] stones, int k) {
    	int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		int[] copy= new int[stones.length];
		
		for (int i = 0; i < stones.length; i++) {
			min = Math.min(min, stones[i]);
			max = Math.max(max, stones[i]);
		}
		
		int mid=0, cnt=0;
		while(min<=max) {
			mid = (min+max)/2;
			
			for (int i = 0; i < stones.length; i++) {
				copy[i] = stones[i]; //복사본 만들기
			}
			int zeroNum=1; //0 이하인 원소 개수 세기
			
			for (int i = 0; i < copy.length-1; i++) {
				copy[i] -= mid;
				if(copy[i]<=0 && copy[i+1]-mid<=0)
					zeroNum++;
				else
					zeroNum=1; //연속해서 0이 아니면 다시 리셋
				
				if(zeroNum>=k) //징검다리 더 이상 못건너므로
					break;
			}
			
			if(zeroNum>=k) {
				max = mid-1;
			} else {
				min = mid+1;
			}
    }
        return min;
    }
}

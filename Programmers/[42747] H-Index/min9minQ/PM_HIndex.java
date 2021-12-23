package time21;

import java.util.Arrays;

public class PM_HIndex {
	
	private static int citations[] = {3,0,6,1,5};

	public static void main(String[] args) {
		int answer = 0;
		
		Arrays.sort(citations);
		int[] arr = new int[citations.length];
		for(int i = 0; i < citations.length ; i++) {
			arr[i] = citations[citations.length-1-i];
		}
		
        int h;
        for(int i=0; i<citations.length; i++){
            //i일때 가장 큰 h
            h = citations.length-i;
            
            if(citations[i] >= h){
                answer = h;
                break;
            }
        }
		System.out.println(answer);
	}

}

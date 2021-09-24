class Solution {
    public int solution(int[][] triangle) {
        int answer = -1;
        
        //해당 위치에서의 가능한 최댓값
        int[][] opt = new int[triangle.length][];
        
        for(int i=0; i<opt.length; i++) {
        	opt[i] = new int[i+1];
        }
        
        //초기값
        opt[0][0] = triangle[0][0];
        
        for(int i=1; i<opt.length; i++) {
        	for(int j=0; j<opt[i].length; j++) {
        		
        		//양 끝에 위치했다면 -> 한쪽으로만 옴
        		if(j==0) {
        			opt[i][j] = opt[i-1][j] + triangle[i][j];
        		} else if (j==opt[i].length-1) {
        			opt[i][j] = opt[i-1][j-1] + triangle[i][j];
        		} else { //중간에 위치했다면
        			//둘 중 큰 거 고르기 
        			opt[i][j] = Math.max(opt[i-1][j], opt[i-1][j-1]) + triangle[i][j];
        		}
        	}
        }
        
        for(int i=0; i<opt[opt.length-1].length; i++) {
        	answer = Math.max(answer, opt[opt.length-1][i]);
        }
        return answer;
    }
}

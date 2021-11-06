package time15;

public class PM_피로도 {
	
	static int k = 80;
	static int[][] dungeons = {{80,20},{50,40},{30,10}};
	static int answer = -1;

	public static void main(String[] args) {
		boolean[] visited = new boolean[dungeons.length];
		int[][] map = new int[dungeons.length][2];
		
		per(0,dungeons,map,visited,k);
		
		
		
		
		System.out.println(answer);
		//return answer;
	}

	private static void per(int cnt,int[][] dungeons, int[][] map, boolean[] visited, int k) {
		if(cnt==dungeons.length){

			int temp=0;
	        for(int i=0;i<map.length;i++){
	            if(k>=map[i][0]){
	                k-=map[i][1];
	                temp++;
	            }
	            else{
	                break;
	            }
	        }
	    
	        answer=Math.max(answer,temp);
			
            return;
        }
        
        
        for(int i=0;i<dungeons.length;i++){
            if(!visited[i]){
                visited[i] =true;
                map[cnt]=dungeons[i];
                per(cnt+1,dungeons,map,visited,k);
                visited[i]=false;
            }
        }
	}

}

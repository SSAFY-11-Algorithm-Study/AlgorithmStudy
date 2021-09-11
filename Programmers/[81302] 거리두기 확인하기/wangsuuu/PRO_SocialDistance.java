import java.util.ArrayList;

public class PRO_SocialDistance {
	
	static class Pos{
		int x, y;
		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static ArrayList<Pos> Ps;
	static Pos[] result = new Pos[2];
	static ArrayList<Integer> answers;
	static String[][] place;
	
	public int[] solution(String[][] places) {
		
		int[] answer = new int[5];
		
		for(int tc=0; tc<5; tc++) {
			
			place = new String[5][5];
			Ps = new ArrayList<>();
			answers=new ArrayList<>();
			
			for(int i=0; i<5; i++) {
				String str = places[tc][i];
				for(int j=0; j<5; j++) {
					place[i] = str.split("");
					if(place[i][j].equals("P")) {
						Ps.add(new Pos(i, j));
					}
				}
			} //저장 완료
			
			if(Ps.size()==0 || Ps.size()==1) { //참가자가 1명 이하라면 거리두기 지킨 것이므로 1출력하고 종료
				answer[tc]=1;
			} else {
				comb(0, 0);
				boolean flag=true;
				for(int i : answers) {
					if(i==0) {
						flag=false;
					}
				}
				if(flag) answer[tc]=1;
				else answer[tc]=0;
			}//else 
		}//for
		
		return answer;
		
	}//solution
	
private static void comb(int cnt, int start) {
		
		if(cnt==2) {
			int x1 = result[0].x, x2 = result[1].x;
			int y1 = result[0].y, y2 = result[1].y;
			int distance = Math.abs(x1 - x2) + Math.abs(y1-y2);
			
			if(distance>=3) { //거리두기를 지킨 것이므로
				answers.add(1);
			} else if(distance==1) { //거리두기를 지키지 않은 것이므로
				answers.add(0);
			} else if(distance==2) { //거리가 2이면 거리두기 여부를 확인해 봐야 함
				
				//x좌표나 y좌표가 같다면, 체크해 볼 좌표가 1개
				if(x1 == x2) { //x좌표가 같다면
					int mid = (y1+y2)/2;
					if(place[x1][mid].equals("X")) { //사이에 파티션이 있어야지만 거리두기 지킨 것
						answers.add(1);
					} else {
						answers.add(0);
					}
				} else if(y1==y2) {
					int mid = (x1+x2)/2;
					if(place[mid][y1].equals("X")) {
						answers.add(1);
					}else {
						answers.add(0);
					}
				} else { //x,y 좌표가 서로 모두 다르다면 살펴봐야 할 좌표가 2개
					//둘중 y좌표가 좀 더 큰거에서 y-1 한 개랑, y좌표가 좀 더 작은 거에서 y+1한 좌표임
					
					if(y1>y2) {
						if(place[x1][y1-1].equals("X") && place[x2][y2+1].equals("X")) {
							answers.add(1);
						} else {
							answers.add(0);
						}
					} else {
						if(place[x1][y1+1].equals("X") && place[x2][y2-1].equals("X")) {
							answers.add(1);
						} else {
							answers.add(0);
						}
					}
				}
			}
			return;
		}
		
		for(int i=start; i<Ps.size(); i++) {
			result[cnt] = Ps.get(i);
			comb(cnt+1, i+1);
		}
	}
}

package week33;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ16235_Tree {
	
	static ArrayList<Integer>[][] tree_map;
	static int[][] a, nut;
	static int N, M, K;
	static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		tree_map = new ArrayList[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				tree_map[i][j] = new ArrayList<Integer>();
			}
		}
		
		a = new int[N][N];
		nut = new int[N][N];
		
		for(int i=0; i<N; i++) { //겨울에 추가될 양분 내용 저장
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				a[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) { //초기 양분은 5
			Arrays.fill(nut[i], 5);
		}
		
		for(int i=0; i<M; i++) { //나무 정보 저장
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int age = Integer.parseInt(st.nextToken());
			tree_map[x][y].add(age);
		}//입력 완료
		
		for(int i=0; i<K; i++) {
			ArrayList<int[]> died = new ArrayList<>();
			
			//spring
			for(int j=0; j<N; j++) {
				for(int k=0; k<N; k++) {
					ArrayList<Integer> cur = tree_map[j][k];
					if(cur.size()!=0) {
						Collections.sort(cur);
						
						for(int x=0; x<cur.size(); x++) {
							if(nut[j][k] >= cur.get(x)) { //양분을 먹을 수 있음
								nut[j][k] -= cur.get(x);
								cur.set(x, cur.get(x)+1);
							} else {//양분 못 먹으면 나무 죽음
								died.add(new int[] {j, k, cur.get(x) / 2}); //죽은 나무 정보 저장
								cur.remove(x);
								x--;
							}
						}
					}
				}
			}
			
			//summer : 양분 추가
			for(int [] d : died) {
				nut[d[0]][d[1]] += d[2];
			}
			
			//fall : 나무 번식
			for(int j=0; j<N; j++) {
				for(int k=0; k<N; k++) {
					ArrayList<Integer> cur = tree_map[j][k];
					if(cur.size()!=0) {
						
						for(int x=0; x<cur.size(); x++) {
							if(cur.get(x) % 5 == 0) { //나이가 5의 배수이면 -> 번식 가능
								for(int a = 0; a<8; a++) {
									int nx = j + dx[a];
									int ny = k + dy[a];
									if(nx<0 || nx>=N || ny<0 || ny>=N)
										continue;
									tree_map[nx][ny].add(1);
								}
							}
						}
					}
				}
			}
			
			//winter
			for(int j=0; j<N; j++) {
				for(int k=0; k<N; k++) {
					nut[j][k] += a[j][k];
				}
			}
		}
		
		//살아있는 나무 갯수 구하기
		int answer = 0;
		for(int i=0; i<N; i++){
			for(int j=0; j<N; j++) {
				int size = tree_map[i][j].size();
				if(size!=0)
					answer += size;
			}
		}
		System.out.println(answer);
	}

}

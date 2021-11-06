package week15;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;



public class BOJ1646_도시분할계획 {

	static class Edge implements Comparable<Edge>{
		int start,end,weight;

		public Edge(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight; //간선이 전부 양수
			//return Integer.compare(this.weight, o.weight); //간선이 음수 양수 섞였을수도있음

		}
	}


	//사이클을 검사하기위한 유니온파인드 코드
	static int [] parents; // 부모 원소를 관리(트리처럼)
	private static void make() {

		parents = new int[N+1];
		//모든 원소를 자신이 대표자가 되게 만듬
		for(int i = 0 ; i < N; i ++)
			parents[i] = i;
	}

	private static int find(int a) {
		//a가 속한 집합의 대표자 찾는 함수
		if(a == parents[a]) return a; // 자신이 대표자인 경우


		//return find(parents[a]);
		//자신이 속한 집합의 대표자를 자신의 부모로 : path compression
		return parents[a] = find(parents[a]); 
	}

	//두 원소를 하나의 집합으로 합치기
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		//알고보니 같은 집합이었던 경우
		if(aRoot == bRoot) return false; // 합칠 필요가 없음

		parents[bRoot] = aRoot; //합쳐짐(b의 대표자를 a대표자에 연결)
		return true;
	}

	static Edge [] edgeList;
	static int N,M;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		edgeList = new Edge[M];

		for(int i = 0 ; i < M ; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			int weight = sc.nextInt();
			edgeList[i] = new Edge(start, end , weight);
		}

		Arrays.sort(edgeList); //미리 정의해둔 compareTo에 의해 weight로 정렬

		make(); //모든 정점을 각각 서로소 집합으로 만들고 출발

		//간선 하나씩 시도하면서 트리 만들어가기
		int cnt = 0; 
		int result = 0;
		int biggest = 0;
		for(Edge e : edgeList) {
			//두개가 합쳐진다면 사이클이 아님
			if(union(e.start,e.end)) {
				result += e.weight;
				biggest = Math.max(biggest, e.weight);
				if(++cnt == N-1) break; //신장트리 완성
			}
		}

		System.out.println(result - biggest);

	}

}

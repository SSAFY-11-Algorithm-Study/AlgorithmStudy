package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//생각보다 노드 구성하는게 어려워서 블로그 참조했음


public class BOJ1991_트리순회 {
	static class Node {
		int left;
		int right;

		public Node(int left, int right) {
			this.left = left;
			this.right = right;

		}
	}
	static List<Node>[] list;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int n = Integer.parseInt(br.readLine());
		
		//노드들 담는리스트
		list = new ArrayList[n+1];
		for(int i=1; i<n+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=1; i<n+1; i++) {
			String[] line = br.readLine().split(" ");
			int data = line[0].charAt(0) -'A'+1;
			int left = line[1].charAt(0) -'A'+1;
			int right = line[2].charAt(0) -'A'+1;
			list[data].add(new Node(left, right));
		}
		
		preorder(1);
		sb.append("\n");
		inorder(1);
		sb.append("\n");
		postorder(1);
		System.out.println(sb.toString());
		
	}
	
	// 전위 순회
	static void preorder(int start) {
		for(Node node : list[start]) {
			int l = node.left;
			int r = node.right;
			
			sb.append((char)(start+'A'-1));
			if(l != -18) preorder(l);
			if(r != -18) preorder(r);
		}
	}
	
	// 중위 순회
	static void inorder(int start) {
		for(Node node : list[start]) {
			int l = node.left;
			int r = node.right;
			
			if(l != -18) inorder(l);
			sb.append((char)(start+'A'-1));
			if(r != -18) inorder(r);
		}
	}
	
	// 후위 순회
	static void postorder(int start) {
		for(Node node : list[start]) {
			int l = node.left;
			int r = node.right;
			
			if(l != -18) postorder(l);
			if(r != -18) postorder(r);
			sb.append((char)(start+'A'-1));
		}
	}
}
import java.util.Scanner;

//배운지 오래되서 다 까먹음ㅠ 인터넷 참고해서 품

//트리 전체와 그 서브트리들이 계속 같은 구조로 순회됨 -> 재귀 사용!!
//FLAT하게 생각!!

public class BOJ1991_TreeTraversal {
	
	static class Node{
		char node;
		Node left, right; //당장 정할 수 없으므로 생성자에 포함시키지 않는다.
		
		public Node(char node) {
			this.node = node;
		}
	}
	
	static class Tree {
		Node root;

		public void createTree(char data, char ldata, char rdata) {
			if(root==null) { //루트를 채우는 게 최우선
				
				root = new Node(data);
				
				if(ldata!='.')
					root.left = new Node(ldata);
				if(rdata != '.')
					root.right = new Node(rdata);
			} else { //루트가 채워져 있다면, 노드가 들어갈 자리를 루트부터 탐색 시작
				searchTree(root, data, ldata, rdata);
			}
		}

		private void searchTree(Node root, char data, char ldata, char rdata) { //root는 서브트리의 루트로 계속 변화
			
			if(root==null) { //탐색하려고 하는 루트가 null이면, 종료
				return;
			}
			
			if(root.node==data) { //자기 자리를 찾았으므로
				if(ldata!='.')
					root.left = new Node(ldata);
				if(rdata!='.')
					root.right = new Node(rdata);
			} else { //자기 자리를 못 찾으면 오른쪽 자식, 왼쪽 자식 탐색
				searchTree(root.left, data, ldata, rdata);
				searchTree(root.right, data, ldata, rdata);
			}
		}
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		Tree tree = new Tree();
		
		for (int i = 0; i < N; i++) {
			char data = sc.next().charAt(0);
			char ldata = sc.next().charAt(0);
			char rdata = sc.next().charAt(0);
			tree.createTree(data, ldata, rdata);
		}
		
		preorder(tree.root);
		System.out.println();
		
		inorder(tree.root);
		System.out.println();
		
		postorder(tree.root);
		System.out.println();
	}

	private static void preorder(Node root) {
		
		if(root!=null) {
			System.out.print(root.node);
			preorder(root.left);
			preorder(root.right);
		}
		
	}
	
	private static void inorder(Node root) {
		
		if(root!=null) {
			inorder(root.left);
			System.out.print(root.node);
			inorder(root.right);
		}
	}

	private static void postorder(Node root) {
		
		if(root!=null) {
			postorder(root.left);
			postorder(root.right);
			System.out.print(root.node);
		}
	}
}

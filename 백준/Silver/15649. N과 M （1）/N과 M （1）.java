import java.util.*;
public class Main {
	static int N,M; //N,M은 입력받고 바뀌지 않으므로 전역변수로 설정
	static boolean[] check; //check 배열로 i 인덱스 방문여부 결정
	static int[] result; //숫자의 조합을 위한 배열
	
	static void seqence(int N, int M, int depth) {
    	//재귀함수 종료조건
		if(depth==M) { //M길이 만큼의 숫자가 result에 요소로 들어갔다는 말.
			for(int x=0; x<result.length; x++) { //for문을 돌며 result 요소를 출력.
				System.out.print(result[x]+" ");
			}
			System.out.println(); //개행
			return;
		}
		
		for(int i=1; i<=N; i++) {
			if(check[i]) {continue;} //i 즉, 자연수 i가 방문됐던 요소라면 다음 i로 처음부터.
			
			result[depth]=i; //result에 자연수 i를 depth번째 자리로 할당.
			check[i]=true; //자연수 i를 방문처리.
			
			seqence(N,M,depth+1); //재귀호출하면서 지금까지 만든 수열 다음 자리에 1~N사이의 자연수를 넣으려고 depth에 +1 해줌.
			check[i]=false; //재귀가 종료될때마다 자연수 i에 해당하는 수는 다시 false 처리해서 방문할수 있게끔 만듦.
			
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		sc.close();
		
		result = new int[M]; //M길이 만큼의 수열을 만들어야하기 때문에 M크기 배열로 설정.
		check = new boolean[N+1]; //입력값 N은 1~N 자연수를 말하기 때문에 1부터 시작하려고 N+1로 크기 설정
		
		seqence(N,M,0); //재귀 호출
	}
}
// 3:37
import java.util.*;
import java.io.*;

public class Main
{
    static int K;
    static int N;
    static int[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    static char[] operators;
    static int[] perm;
    static boolean[] visited;
    static long max = Long.MIN_VALUE;
    static long min = Long.MAX_VALUE;
    static StringBuilder maxStr = new StringBuilder();
    static StringBuilder minStr = new StringBuilder();
    
	public static void main(String[] args) throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    K = Integer.parseInt(br.readLine()); // 부등호 개수 입력
	    // perm개수
	    N = K+1;
	    
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    
	    operators = new char[K];
		for(int i = 0; i < K; i++) {
		    operators[i] = st.nextToken().charAt(0);
		}
		
		// 조합 후보 구하기
		perm = new int[N];
		visited = new boolean[10];
		
		permutationCalc(0);
		
		System.out.println(maxStr);
		System.out.println(minStr);
	    
	}
	
	static void permutationCalc(int depth) {
	   if(depth == N) {
	       // 유효성 검증하고 최대, 최소 검증하는 역할을 한다.
	       if (isValid()) calc();
	       return;
	   }
	   for(int i = 0; i < 10; i++) {
	       if(visited[numbers[i]]) continue;
	       
	       visited[numbers[i]] = true;
	       perm[depth] = numbers[i];
	       permutationCalc(depth+1);
	       // visited는 0-~9로 이루어진 함수
	       visited[numbers[i]] = false;
	       
	   }
	}
	static boolean isValid() {
	    // 부등호가 구한 순열 순서와일치하는지 확인
	    for(int i = 0; i < K; i++) {
	        if(operators[i] == '<') {
	            if(!(perm[i] < perm[i+1])) return false;
	        } else if (operators[i] == '>') {
	            if(!(perm[i] > perm[i+1])) return false;
	        }
	    }
	    return true;
	}
	
	static void calc() {
	    // operator확인한 다음 최댓값, 최솟값 구함
	    long sum = 0;
	    for(int i = 0; i < N; i++) {
	        sum += perm[i] * Math.pow(10, N-i);
	    }
	    
	    // 갱신작업
	    if (sum > max) {
	        maxStr = new StringBuilder();
	        max = sum;
	        for(int i = 0; i < N; i++) {
	            
	            maxStr.append(perm[i]);
	        }
	        
	    }
	    if(sum < min) {
	        minStr = new StringBuilder();
	        min = sum;
	        for(int i = 0; i < N; i++) {
	            
	            minStr.append(perm[i]);
	        }
	    }
	    return;
	}
	
}
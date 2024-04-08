// 3:37 => 50분 걸림
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
    static String maxStr = "";
    static String minStr = "9999999999";
    
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
		
		permutationCalc(0, new StringBuilder());
		
		System.out.println(maxStr);
		System.out.println(minStr);
	    
	}
	
	static void permutationCalc(int depth, StringBuilder tmpStr) {
	   if(depth == N) {
	       // 이제 여기서 유효성 검증한다.
	       String tmp = tmpStr.toString();
	       if(maxStr.compareTo(tmp) < 0) {
	           maxStr = tmp;
	       }
	       if(minStr.compareTo(tmp) > 0) {
	           minStr = tmp;
	       }
	      
	       return;
	   }
	   for(int i = 0; i < 10; i++) {
	       if(visited[numbers[i]]) continue;
	       if(depth > 0 && !isValid(depth-1, numbers[i])) continue;
	       visited[numbers[i]] = true;
	       tmpStr.append(numbers[i]);
	       perm[depth] = numbers[i];
	       permutationCalc(depth+1, tmpStr);
	       // visited는 0-~9로 이루어진 함수
	       // 원복
	       visited[numbers[i]] = false;
	       tmpStr.setLength(tmpStr.length() - 1);
	       
	   }
	}
	static boolean isValid(int now, int number) {
	    // 부등호가 구한 순열 순서와일치하는지 확인
        if(operators[now] == '<') {
            if(!(perm[now] < number)) return false;
        } else if (operators[now] == '>') {
            if(!(perm[now] > number)) return false;
        }
	    return true;
	}

	
}
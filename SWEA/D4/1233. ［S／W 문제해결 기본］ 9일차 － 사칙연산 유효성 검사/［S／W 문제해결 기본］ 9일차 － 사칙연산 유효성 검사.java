import java.io.*;
import java.util.*;

/**
 * @since jdk1.8
 * - 문제 : SWEA 1233 사칙연산 유효성 검사
 * - 소요시간 : 20분 
 * - 난이도 : 중
 * - 아이디어 : 연산자는 내부 노드에만 와야 하며, 피연산자는 외부 노드에만 존재해야 함을 구현
 */
class Solution {
	public static void main(String args[]) throws Exception {
	    
	    String operator = "+-*/";
		int T = 10;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for(int test_case = 1; test_case <= T; test_case++) {
		    
		    int N = Integer.parseInt(br.readLine());
		    
		    boolean flag = true;
		    int result = 0;
            // 입력을 쭉 받아야 하기 때문에 중간에 false되더라도 break를 걸지 말자
		    for(int i = 0; i < N; i++) {
		        String[] strLine = br.readLine().split("\\s"); // 한 줄 입력
		        String tmpOpr = strLine[1] ;
		        if(operator.indexOf(tmpOpr) == -1 && strLine.length >= 3) { // 숫자가 내부 노드에 있다면 탈락
		            flag = false;
		        }
		        else if(operator.indexOf(tmpOpr) >= 0 && strLine.length <= 2) { // 연산자가 외부 노드
		            flag = false;
		        }
		        
		    }
		    result = flag ? 1 : 0;
		    System.out.printf("#%d %d%n", test_case, result);


		}
	}
}
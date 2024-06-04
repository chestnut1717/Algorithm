import java.util.*;
import java.io.*;

public class Main
{
    static int T; // test case
    static int[] numbers;
    static StringBuilder resultSb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		
		// each test
		for(int i = 0; i < T; i++) {
		    // 변수 초기화
		    int N = Integer.parseInt(br.readLine());
		    int[] operator = new int[N-1];
		    numbers = new int[N];
		    for(int j = 0; j < N; j++) {
		        numbers[j] = j+1;
		    }
		    
		    // 모든 operator계산
		    permOperator(N, operator, 0);
		    // 각 테스트 케이스 모두 계산했으면 한 줄 띄워 구분
		    resultSb.append('\n');
		    
		}
		
		bw.write(resultSb.toString());
		bw.close();
		
		// 우선 각 N마다 모든 연산자의 경우의 수를 구한다.(N-1개 연산자)
		// 연산자가 구해졌으면, 이제 수식을 만든다.
		// 그 수식대로 이제 계산을 하고, 0이 맞다면 해당 수식을 sb에 append한다.
	}
	
	static void permOperator(int N, int[] operator, int cnt) {
	    if (cnt == N-1) {
	        // 수식을 만든다.
	        String expr = makeExpr(operator);
	        // 수식을 계산한다.
	        int left = numbers[0];
	        int oper = 0;
	        int right = 0;
	        int i = 0;
	        while(i < N-1) {
	            // 공백이라면
	            if(operator[i] == 1) {
                    left = 10 * left + numbers[i+1];
                    i++;
                    continue;
                // 공백이 아니라면
	            } else {
	                oper = operator[i];
	                // 그 다음 것이 공백이라면
	                if(i+1 < N-1 && operator[i+1] == 1) {
	                    right = 10*numbers[i+1] + numbers[i+2];
	                    i+=2;
	                } else {
	                    right = numbers[i+1];
	                    i++;
	                }
	            }
	            
	            // 공백이 아닌 operator
	            switch(oper) {
	               case 2:
	                    left = left + right;
	                    break;
	               case 3:
	                   left = left - right;
	                   break;
	            }
	            
	        }
	        
	        int result = left;
	        if (result == 0) {
	           resultSb.append(expr).append('\n');
	        }
	        return;
	    }
	    operator[cnt] = 1;
	    permOperator(N, operator, cnt+1);
	    operator[cnt] = 2;
	    permOperator(N, operator, cnt+1);
	    operator[cnt] = 3;
	    permOperator(N, operator, cnt+1);
	}
	
	// 문자열 수를 만드는 경우
	static String makeExpr(int[] operator) {
	    StringBuilder sb = new StringBuilder();
	    for(int i = 0; i < operator.length; i++) {
	        sb.append(numbers[i]);
	        switch(operator[i]) {
	            case 1:
	                sb.append(" ");
	                break;
	            case 2:
	                sb.append("+");
	                break;
	            default:
	                sb.append("-");
	        }
	    }
	    // 마지막 수 더하기
	    sb.append(numbers[numbers.length-1]);
	    return sb.toString();
	}
}
import java.util.*;
import java.io.*;

public class Main
{
    static int N;
    static int[] arr;
    static char[] oper;
    static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String temp = br.readLine();
		
		// 1. 우선 숫자와 연산자를 따로 저장
		arr = new int[N/2+1];
		oper = new char[N/2];
		for(int i = 0; i < N; i++) {
		    if(i % 2 ==0) arr[i/2] = temp.charAt(i) - '0';
		    else oper[i/2] = temp.charAt(i);
		}
		
		// 2. DFS를 통해서 괄호 붙였다 붙이지 않았다 계산
		// 2 + 5 * 3
		// 2 + (5 * 3)
		dfs(0, arr[0]);
		
		System.out.println(max);
		
	}
	
	static void dfs(int nowIdx, int tmpSum) {
	    if(nowIdx == N/2) {
	        max = Math.max(max, tmpSum);
	        return;
	    }
	    
	    // 우선 정방향으로 계산해주고
	    dfs(nowIdx+1, calc(tmpSum, arr[nowIdx+1], oper[nowIdx]));
	    
	    // 그 후 바로 뒤 친구한테 괄호씌워준다.
	    if(nowIdx+2 <= N/2) {
	        int temp = calc(arr[nowIdx+1], arr[nowIdx+2], oper[nowIdx+1]);
	        dfs(nowIdx+2, calc(tmpSum, temp, oper[nowIdx]));
	    }
	    
	    return;
	    
	    
	}
	
	static int calc(int a, int b, char oper) {
	    if(oper == '+') return a + b;
	    if(oper == '-') return a - b;
	    if(oper == '*') return a * b;
	    
	    return -1;
	}
	

}
import java.util.*;
import java.io.*;

/**
 * @since jdk1.8
 * - 문제 : BJ 14888 연산자 끼워넣기
 * - 소요시간 : 1시간 20분 
 * - 난이도 : 중
 * - 아이디어 : 순열 + 중복 element의 순서 또한 고려해야 함(ex. +-- 와 +-- 은 동일함)
 * - 이를 위해 모든 연산자는 정렬 처리 되어있어야 하고
 * - 순열 계산 중에 이전 연산자(i-1)와 현재 연산자(i)가 동일하고 이전 연산자가 방문하지 않았을 때는 무시해야 함
 * - 이전 연산자가 방문하지 않았다는 것 => 이미 i-1번째에 recursion을 끝마쳤다는 의미
 */
public class Main {
    static int N;
    static int[] arr;
    static int[] operator;
    static int[] operatorCombi;
    static boolean[] visited;
    static int maxNum = Integer.MIN_VALUE;
    static int minNum = Integer.MAX_VALUE;
    
	public static void main(String[] args) throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    N = Integer.parseInt(br.readLine()); // N개의 수
	    StringTokenizer st = new StringTokenizer(br.readLine());;
	    
	    arr = new int[N]; // arr 초기화
	    for(int i = 0; i < N; i++) {
	        arr[i] = Integer.parseInt(st.nextToken());
	    }
	    
	    operator = new int[N-1];
	    // 연산자 입력
	    st = new StringTokenizer(br.readLine());;
	    int idx = 0;
	    for(int i = 0; i < 4; i++) {
	        int cnt = Integer.parseInt(st.nextToken());
	        for(int j = 0; j < cnt; j++) {
	            operator[idx] = i;
	            idx++;
	        }
	    }
	    
	    visited = new boolean[N-1]; // 순열 계산을 위한 방문여부 체크 배열 초기화
	    operatorCombi = new int[N-1]; // N-1개의 연산자를 담을 배열 초기화
	   
	   
	   combi(0); // 결과 계산
	   
	    System.out.println(maxNum);
	    System.out.println(minNum);
    
    
	}
	// 계산 결과는 항상 10억 미만이므로
	static int calc() {
	    int result = arr[0];
	    // operatorCombi에 저장된 연산자 : 0: +, 1: -, 2: *, 3: /
	    for(int i = 0; i < operatorCombi.length; i++) {
	        switch (operatorCombi[i]) {
	            case 0:
	                result += arr[i+1];
	                break;
	            case 1:
	                result -= arr[i+1];
	                break;
	            case 2:
	                result *= arr[i+1];
	                break;
	            case 3:
	                result /= arr[i+1];
	        }
	    }
	    return result;
	}
	
	// 연산자 중복 고려한 순열 생성
	static void combi(int count) {
	    if (count == N-1) { // 종료조건에 다다르면
	        for(int i = 0; i < operatorCombi.length; i++) {
	            int tmp = calc(); // 최댓값, 최솟값 갱ㅅ긴
	            maxNum = Math.max(maxNum, tmp);
	            minNum = Math.min(minNum, tmp);
	        }
	        return;
	    }
	    for(int i = 0; i < operator.length; i++) {
	        // 방문 했거나, 순열 원소간 중복체크 확인(001과 001은 동일)
            if (visited[i] || (i > 0 && operator[i-1] == operator[i] && !visited[i-1])) continue;
            visited[i] = true;
            operatorCombi[count] = operator[i];
            combi(count+1);
            visited[i] = false;
	    }
	}
}
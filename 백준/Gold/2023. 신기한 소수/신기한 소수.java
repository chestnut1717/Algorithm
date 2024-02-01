import java.util.*;
import java.io.*;

/**
 * @since jdk1.8
 * @see <a href="https://www.acmicpc.net/problem/2023">
 * - 문제 : 백준 2023 신기한 소수
 * - 소요시간 : 40분 
 * - 난이도 : 중
 * - 1. 메모리가 4MB만 할당되어 있기 때문에, 에라토스테네스의 체와 같은 소수 판별법은 무조건 시간초과가 날 수 밖에 없음
 * - 2. 따라서 신기한 소수의 특징에 맞추어 재귀를 활용해 자릿수를 한 자리씩 늘려나가 소수를 판별하도록 함
 * - 3. 중요한 점은, 최대한 범위를 줄이기 위해서 자릿수의 조건을 달아(ex. 왼쪽에서 첫번째 자리는 무조건 2, 3, 5, 7 등) 범위를 줄이도록 함
 * - 4. StringBuilder, BufferedWriter을 활용해 출력 최대한 빨리 하도록 함
 */
public class Main {
    static final int[] nums = {2, 3, 5, 7}; // 첫 번째 자리에는 다음 한 자리 소수밖에 못옴
    static int N;
    static StringBuilder sb = new StringBuilder(); // 출력 빠르게 하기 위해서, 메모리 공간 차지 안하기 위해서 사용
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		N = Integer.parseInt(br.readLine()); // 자릿수 N 입력
		
		// 첫번째 자리는 2, 3, 5, 7밖에 올 수 없음
		for(int n: nums) {
		    findAmazingPrime(n, 1);
		}
		
		bw.write(sb.toString());
		bw.close();
	}
	
	// 신기한 소수 판별
	static void findAmazingPrime(int n, int depth) { // n = 입력숫자, depth = 자릿수
	    if (depth == N) {
	        sb.append(n).append("\n"); // 신기한 소수만 담음
	        return;
	    }
	    for(int i = 1; i <= 9; i+=2) {// 홀수군만 탐색
	        int tmpNum = n * 10 + i; // 이전 숫자 3-> 다음 숫자 3*10 + 1 이런 원리로 숫자 붙여줌
	        if(isPrime(tmpNum)) findAmazingPrime(tmpNum, depth+1); // 만약 소수이면 계속 탐색하고 아니면 skip
	    }
	}
	
	// 소수를 판별하는 함수
	static boolean isPrime(int n) {
	    for(int i = 2; i*i <= n; i++) { // O(sqrt(n)) 가능하도록 함
	        if(n % i == 0) {
	            return false;
	        }
	    }
	    return true;
	}
}

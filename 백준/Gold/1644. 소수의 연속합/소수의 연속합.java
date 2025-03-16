import java.util.*;
import java.io.*;

public class Main
{
    static int N;
    static int[] notPrime = new int[4000004]; // 우선 4000만개 채우기 4byte * 16000000byte = 16mb
    static List<Integer> list = new ArrayList<>(); // 소수만 모아놓은 것
    static int result;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		// 에라스토테네스 체 구하기
		// notPrime[i] == 1은 일반수, notPrime[i] == 0은 소수
		notPrime[1] = 1;
		for(int i = 2; i <= N; i++) {
		    // 소수라면
		    if(notPrime[i] == 0) {
		        int prime = i;
		        list.add(prime);
		        for(int j = i+ prime; j <= N; j += prime) {
		            notPrime[j] = 1; // 다 소수가 아닌 걸로 처리한다.
		        }
		    }
		}
		
		
		int left = 0;
		int right = 0;
		int sum = 0;
		int limit = list.size();
		
		
		// 먼저 
		while(true) {
		    if(sum >= N) sum -= list.get(left++);
		    else if(right == limit) break;
		    else sum += list.get(right++);
		    if (sum == N) result++;

		}
		
		System.out.println(result);
		
		
	}
}
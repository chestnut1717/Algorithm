import java.util.*;
import java.io.*;

public class Main
{
    static final long x = 1000000007;
    static int N; // 항의 개수
    static long result = 0;
    
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		// 우선 map을 만든 다음
		// 각 항을 계산할 때, map을 초기화한다.
		// 이때 각 항의 modulo값을 계산한 다음 다시 modulo연산을 한 후 result에 할당
		// 모든 항이 끝날때까지 계산
		// 각 항의 modulo값을 계산할 때, 분할 정복을 쓴다.
		
		// 각 항의 연산마다 modulo 연산을 진행한다.
		// (a + b) % c = ((a%c)+(b%c)) % c
		for(int i = 0; i < N; i++) {
		    st = new StringTokenizer(br.readLine());
		    
		    long a = Integer.parseInt(st.nextToken());
		    long n = Integer.parseInt(st.nextToken());
		    long temp;
		    if(n == 0) continue; // 0이면 더할 것도 없다.
		    
		    temp = (((a * n) % x) * divConquer(n-1)) % x;
		    
		    result = (result + temp) % x;
            

		    
		}
		
		System.out.println(result);
	}
	
	static long divConquer(long n) {
	    // 분할 정복으로 처리한다.
        long temp;

        if(n == 0) {
            return 1;
        }
        if(n == 1) {
            return 2;
        }

        temp = divConquer(n/2);

	    
	    if(n%2==0) {
	        return (temp * temp) % x;
	    } else {
	        return (temp * temp * 2) % x;
	    }
 
	}
}

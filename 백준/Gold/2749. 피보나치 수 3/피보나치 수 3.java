import java.util.*;
import java.io.*;

public class Main {
    
    static final int C = 1000000;
    static long N;
    static Map<Long, Long> map = new HashMap<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Long.parseLong(br.readLine());
		
		long result = fibo(N);
		System.out.println(result);
	}
	
	// n번째 피보나치 수열을 1,000,000으로 나눈 나머지 반환
	static long fibo(long n) {
	    if(n == 0) return 0;
	    if(n <= 2) return 1;
	    // n이 홀수
	    if(n % 2 == 1) {
	        long fibo1 = -1;
	        long fibo2 = -1;
	        if(map.containsKey(n/2+1)) {
	            fibo1 = map.get(n/2+1);
	            
	        } else {
	             fibo1 = fibo(n/2+1);
	             map.put(n/2+1, fibo1);
	        }
	        
	        if(map.containsKey(n/2)) {
	            fibo2 = map.get(n/2);
	            
	        } else {
	             fibo2 = fibo(n/2);
	             map.put(n/2, fibo2);
	        }
	        return ((fibo1*fibo1) % C + (fibo2*fibo2) % C) % C;
	    // n이 짝수
	    } else {
	        long fibo1 = -1;
	        long fibo2 = -1;
	        if(map.containsKey(n/2)) {
	            fibo1 = map.get(n/2);
	            
	        } else {
	             fibo1 = fibo(n/2);
	             map.put(n/2, fibo1);
	        }
	        
	        if(map.containsKey(n/2-1)) {
	            fibo2 = map.get(n/2-1);
	            
	        } else {
	             fibo2 = fibo(n/2-1);
	             map.put(n/2-1, fibo2);
	        }
            return ((fibo1*fibo1) % C + (2*fibo1*fibo2) % C) % C;
	    }
	}
	
}
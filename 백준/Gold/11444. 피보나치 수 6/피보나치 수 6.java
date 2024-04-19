import java.util.*;
import java.io.*;

public class Main
{
    static final int mod = 1000000007;
    static long N;
    static Map<Long, Long> map = new HashMap<>();
	public static void main(String[] args) throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Long.parseLong(br.readLine());
		
		map.put(0L, 0L);
		map.put(1L, 1L);
		// 이진 탐색을 활용한다.
		System.out.println(fibo(N));
	}
	static long fibo(long N) {
	    if(map.containsKey(N)) return map.get(N);
	    
	    if(N % 2 == 0) {
	        long tmpFibo = (fibo(N/2) * (2 * fibo(N/2-1) + fibo(N/2))) % mod;
	        map.put(N, tmpFibo);
	        return tmpFibo;
	    } else {
	        long tmpFibo = (fibo(N/2+1) * fibo(N/2+1) + fibo(N/2) * fibo(N/2)) % mod;
	        map.put(N, tmpFibo);
	        return tmpFibo;
	    }
	}
}

import java.util.*;
import java.io.*;

public class Main
{
    static long N;
    static long[] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Long.parseLong(br.readLine());
		
		arr = new long[64];
		// 각각의 거듭제곱을 채워준다.
		arr[0] = 1;
		for(int i = 1; i < 64; i++) {
		    arr[i] = arr[i-1] * 2;
		}
		
		
		System.out.println(thueMorse(N));
		
	}
	
	static int thueMorse(long n) {
	    if (n == 1L) return 0;
	    for(int i = 0; i < 64; i++) {
	        if (arr[i] >= n) return 1 - thueMorse(n - arr[i-1]);
	    }
	    
	    return 0;
	}
}
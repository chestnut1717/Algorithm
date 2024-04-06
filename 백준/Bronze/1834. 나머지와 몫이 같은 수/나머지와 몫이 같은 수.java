import java.io.*;
import java.util.*;

public class Main
{
    static long N;
    static long sum;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Long.parseLong(br.readLine());
		
		// N으로 나눴을 때, 나머지와 몪이 모두 같은 자연수
		// 1*N + 1 ... (N-1)N + N-1;
		for(int a = 1; a <= N-1; a++) {
		    sum += a * N + a;
		}
		System.out.println(sum);
	}
}

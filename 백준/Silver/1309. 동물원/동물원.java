import java.util.*;
import java.io.*;

public class Main
{
    static int N;
    static long[][] arr;
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new long[N+1][3];
		arr[1][0] = 1;
		arr[1][1] = 1; // mul
		arr[1][2] = 1; // odd
		for(int i = 2; i <= N; i++) {
		    arr[i][0] = (arr[i-1][0] + arr[i-1][1] + arr[i-1][2]) % 9901;
		    arr[i][1] = (arr[i-1][0] + arr[i-1][2]) % 9901;
		    arr[i][2] = (arr[i-1][0] + arr[i-1][1]) % 9901;
		}
		
		System.out.println((arr[N][0] + arr[N][1] + arr[N][2]) % 9901);
		
	}
}

import java.util.*;
import java.io.*;

public class Main
{
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		// 우선 str을 순회하면서
		// 이전 값과 다른 것이 존재한다면, 그때 count++;
		// 최종적으로 count/2로 뒤집어야 할 수의 최솟값을 찾음
		// (예 : 1로 이루어짐 = 5개 /0으로 이루어짐=4개) 합 = 9 => 9/2 = 4(자동으로 최솟값구해짐)
		
		int prev = str.charAt(0);
		int cnt = 1; // 초깃값 반드시 1
		for(int i = 1; i < str.length(); i++) {
		    int now = str.charAt(i);
		    if(prev != now) cnt++;
		    prev = now;
		}
		
		System.out.println(cnt/2);
		
	}
}
import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] score;
    static int result;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		score = new int[N];
		
		for(int i = 0; i < N; i++) {
		    score[i] = Integer.parseInt(br.readLine());
		}
		
		
		int[] score1 = new int[N];
		int[] score2 = new int[N];
		for(int i = 0; i < N; i++) {
		    score1[i] = score[i];
		    score2[i] = score[i];
		}
		
		int tmp1 = 0;
		// 우선 앞에서부터 탐색을 한 다음
		for(int i = 1; i < N; i++) {
		    // 만약 i-1번째보다 i번째가 더 작거나 같을 경우
		    if(score1[i-1] >= score1[i]) {
		        int tmp = score1[i-1] - score1[i] + 1;
		        tmp1 += tmp;
		        score1[i] += tmp;
		    }
		    // result에 score[i] - score[i-1]+1 더해준다.
		}
		
		int tmp2 = 0;
		
		// 그리고 뒤에서도 탐색을 해준다.
		for(int i = N-1; i > 0; i--) {
		     if(score2[i-1] >= score2[i]) {
		         int tmp = score2[i-1] - score2[i] + 1;
		         tmp2 += tmp;
		         score2[i-1] -= tmp;
		     }
		}
		
		result = Math.min(tmp1, tmp2);
		System.out.println(result);
	}
}

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
		
		// 이제 이 소수 중에, 옆에서부터 line sweeping을 한다.
		int right = list.size() - 1;
		int left = right - 1;
		int tmp= 0;
		
		if(N > 1 && list.get(right) == N) {
		    result++;
		}
		
		// 먼저 
		while(left >= 0) {
		    tmp = tmp + list.get(left) + list.get(right);

		    if(tmp == N) {
		        result++;
		        tmp -= list.get(right);
		        tmp -= list.get(right-1);
		        right--;
		        left--;
		       
		    } else if(tmp > N) {
		        tmp -= list.get(right);
		        tmp -= list.get(right-1);
		        right--;
		        left--;
		    } else {
		        tmp -= list.get(right);
		        left--;
		    }
		}
		
		System.out.println(result);
		
		
	}
}
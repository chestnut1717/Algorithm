import java.util.*;
import java.io.*;


class Solution {
    static int T;
    static int[] tmpA, A;
    static int[] tmpB, B;
    static int[] num; // B가 낼 수 있는 후보군
    static boolean[] visited;
    static int win, lose;
	public static void main(String args[]) throws Exception {
        
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine()); // T 입력
        
        // 각 테스트 케이스
		for(int test_case = 1; test_case <= T; test_case++) {
		    // 1~18까지 인덱스를 가지고 카드 유무 파악하기 위함
		    tmpA = new int[19];
		    tmpB = new int[19];
		    A = new int[9];
		    B = new int[9];
		    num = new int[9];
		    visited = new boolean[9];
		    win = 0; lose = 0;
            
            // A, B 카드 입력
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    for(int i = 0; i < 9; i++)  {
                int n = Integer.parseInt(st.nextToken());
                tmpA[n] = 1;
                tmpB[n] = 1;
		    }
		    // B가 가지고 있는 카드
		    int idxA = 0;
		    int idxB = 0;
		    for(int i = 1; i <= 18; i++) {
		        if(tmpA[i] == 1) {A[idxA] = i; idxA++;}
		        if(tmpB[i] == 0) {B[idxB] = i; idxB++;}

		        
		    }
		  //  for(int i = 0; i < 9; i++) {
		  //      System.out.printf("%d ", A[i]);
		  //  }
		  //  System.out.println();
		  //  for(int i = 0; i < 9; i++) {
		  //      System.out.printf("%d ", B[i]);
		  //  }
		  //  System.out.println();
		    // 순열 계산
		    perm(9, 9, 0);
		    System.out.printf("#%d %d %d%n", test_case, win, lose);
		    
		    
		}
		
		
	}
	static void perm(int n, int r, int cnt) {
	    if(cnt == r) {
	        if (aWin() == 1) win++;
	        else if (aWin() == -1) lose++;
	    }
	    for(int i = 0; i < r; i++) {
	        if (visited[i] == false) {
	            num[cnt] = B[i];
    	        visited[i] = true;
    	        perm(n, r, cnt+1);
    	        visited[i] = false;
	        }

	    }
	}
	static int aWin() {
	    int sumA = 0;
	    int sumB = 0;
	    for(int i = 0; i < 9; i++) {
	        if(A[i] > num[i]) {
	            sumA += A[i];
	            sumA += num[i];
	        } else if (A[i] < num[i]) {
	            sumB += A[i];
	            sumB += num[i];
	        }
	    }
	    if(sumA > sumB) return 1;
	    else if (sumA == sumB) return 0;
	    else return -1;
	    
	}
}
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.*;
import java.io.*;


class Solution
{
	public static void main(String args[]) throws Exception
	{
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

		for(int test_case = 1; test_case <= T; test_case++)
		{
			List<int[][]> arrList = new ArrayList<>();
			StringBuilder sb = new StringBuilder();
//			System.out.println(br.readLine());
			int N = Integer.parseInt(br.readLine());
			int[][] arr = new int[N][N];
			
			// 입력 완
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 이제 돌리고 돌리고
			
			int[][] rotate90 = rotate(N, arr);
			int[][] rotate180 = rotate(N, rotate90);
			int[][] rotate270 = rotate(N, rotate180);
			
			arrList.add(rotate90);
			arrList.add(rotate180);
			arrList.add(rotate270);
			
		

			System.out.println("#"+test_case);
            for(int j=0; j<N; j++) {
                for(int k=0; k<N; k++) {
                    System.out.print(rotate90[j][k]);
                }
                System.out.print(" ");

                for(int k=0; k<N; k++) {
                    System.out.print(rotate180[j][k]);
                }
                System.out.print(" ");

                for(int k=0; k<N; k++) {
                    System.out.print(rotate270[j][k]);
                }
                System.out.println();
            }

		}
	}
	static int[][] rotate(int n, int[][] arr) {
		int[][] tmp = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				tmp[i][j] = arr[n-1-j][i];
			}
		}

		
		return tmp;
		
	}
}
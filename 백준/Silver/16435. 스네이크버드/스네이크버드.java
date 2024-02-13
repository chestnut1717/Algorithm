import java.util.*;
import java.io.*;
/**
 * @since jdk1.8
 * @see <a href="https://www.acmicpc.net/problem/1620">
 * - 문제 : BJ 16435 스네이크버드
 * - 소요시간 : 10분 
 * - 난이도 : 하
 * - 아이디어 : 그리디, 입력으로 들어온 과일들을 오름차순 정렬로 한 후, 앞에서부터 차례차례 먹도록 하면 된다.
 * - 그렇게 하면, 높이가 높아 먹지 못하는 경우가 발견하면, 어차피 그 다음 과일은 항상 높이가 같거나 커서 먹지 못하므로 자동 종료
 */
public class Main {
	static int N, L;
	static int[] arr;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		// 과일 입력
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 정렬
		Arrays.sort(arr);
		
		// 스네이크버드가 오름차순으로 정렬된 과일들을 하나씩 먹으면서 먹지 못하면 자동종료
		for (int i = 0; i < N; i++) {
			if (L >= arr[i])
				L++;
			else
				break;
		}

		System.out.println(L);

	}
}
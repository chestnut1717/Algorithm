import java.util.*;
import java.io.*;

/**
 * @since jdk1.8
 * - 문제 : BJ 1062 가르침
 * - 소요시간 : 1시간 
 * - 난이도 : 중
 * - K가 입력으로 들어옴 => 가능한 모든 알파벳 조합을 만들어서 들어오는 word와 비교
 * - 여기서 a, n, t, i, c는 무조건 들어오므로 조합 후보 알파벳에서도 제외
 * - 마찬가지로 들어오는 입력에서도 해당 5개 알파벳을 모두 제거하고 시작  ex. antartica => r)
 * - 그리고 문자열 내 중복된 문자가 있을 수 있음. 탐색할 필요 없이 중복제거(ex.antaxxxxtica => x);
 * - 제한시간 1초지만 최악으로는 경우의수가 21C10 * 7(문자열 최대 길이) * 50(입력 단어 최대)가 되므로 safe
 */
public class Main {
    static int N;
    static int K;
    static char[] alphabet = new char[26]; // 'a' ~ 'z'까지 담을 알파벳 모음
    static List<String> words = new ArrayList<>();
    static int result;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 0. N, K 입력
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		if (K < 5) { // K가 5보다 작으면 당연히 skip
		    System.out.println(0);
		    return;
		} else if (K == 26) { // 모든 알파벳이 주어질경우, 모든 단어 배울 수 있음
    	    System.out.println(N);
    	    return;
    	}
    	
    	// 항상 5개 문자는 일치 
	    alphabet['a' - 'a'] = 1;
        alphabet['n' - 'a'] = 1;
        alphabet['t' - 'a'] = 1;
        alphabet['i' - 'a'] = 1;
        alphabet['c' - 'a'] = 1;

	    // 1. N개의 단어 입력받기
		for(int i = 0; i < N; i++) {
		    String tmpWord = br.readLine();
		    tmpWord = tmpWord.replaceAll("[a|n|t||i|c]", ""); // antarctica => rc
		    tmpWord = onlyDistinct(tmpWord);
		    words.add(tmpWord); // 나온 최종 중복없는 값 저장
		    
		}

		// 2. 이제 이 배열가지고 조합 만들어주기

	    combi(21, K-5, 0, 0);

        // 3. 결과 출력
        System.out.println(result);

		
	}
	// 문자열 중복제거해주는 함수
	static String onlyDistinct(String str) {
        StringBuilder sb = new StringBuilder();
	    Set<Character> set = new HashSet<>();
	    for(char c: str.toCharArray()) {
	        if(set.add(c)) { // add함수 => 기존 값 있을 경우 false, 없으면 true반환
	            sb.append(c);
	        }
	    }
	    return sb.toString();
	}
	
	// 알파벳 조합 만들어서 => 기존 단어와 비교해서 얼마나 최대로 가르칠 수 있는지
	static void combi(int n, int r, int start, int count) {
	    if (count == r) {
	        // 알파벳 조합이 만들어졌으면, 기존 단어뭉치와 비교
	        result = Math.max(result, howManyLearn());
	        return;
	    }
	    // count가 r이 될때까지 알파벳 조합 만들어주기
	    for(int i = start; i < 26; i++) {
	        if (alphabet[i] == 1) { // 어차피 해당 알파벳은 안나옴
	            continue;
	        }
	        alphabet[i] = 1;
	        combi(n, r, i+1, count+1);
	        alphabet[i] = 0;
	    }
	}
	
	// 해당 알파벳 조합으로 얼마나 배울 수 있는지 찾아줌
	static int howManyLearn() {
	    int count = 0;
	    for(String word: words) { // 각 단어마다 하나씩 꺼내서
	        boolean isContain = true;
	        for(char c: word.toCharArray()) { // 알파벳 조합 배열과 비교
	            if(alphabet[c-'a'] == 0) { 
	                isContain=false;
	                break;
	            }
	        }
	        if (isContain) {
	            count++;
	        }
	    }
	    return count;
	}
	
}
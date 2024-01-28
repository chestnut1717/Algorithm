
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


    public static void main(String[] args) throws IOException {
        // 입력받을  저장할 s를 선택해준다.
        String s;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();

        // 알파벳은 26자이므로, 26개의 공간이 담겨있는 배열 선언 및 초기화
        // 이 배열의 값은 알파벳의 등장 count 저장
        int[] alpabet = new int[26];

        // 문자열의 각 문자만큼 반복문을 돌린다
        for (int i = 0; i < s.length(); i++) {
            // charAt() 활용해 특정 문자열에서 i번째 인덱스 추출
            alpabet[s.charAt(i) - 'a']++;
        }

        // alphabet에 저장된 count 출력
        for (int i = 0; i < 26; i++) {
            System.out.print(alpabet[i] + " ");

        }

    }

}
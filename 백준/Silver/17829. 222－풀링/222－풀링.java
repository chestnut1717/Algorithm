
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

class Main {
    
    static int N;
    static int [][]arr;
    static int result;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        recur(N);
        System.out.println(result);
    }
    
    static void recur(int N) {
        if(N == 1) {
            result = arr[0][0];
            return;
        }

        for(int i = 0; i < N; i+=2) {
            for(int j = 0; j < N; j+= 2){
                int tmpVal = findSecond(i, j);
                arr[i/2][j/2] = tmpVal;
            }
        }

        recur(N/2);
    }
    
    static int findSecond(int y, int x) {
        List<Integer> tmpList = new ArrayList<>();
        for(int i = y; i < y+2; i++) {
            for(int j = x; j < x+2; j++) {
                tmpList.add(arr[i][j]);
            }
        }
        Collections.sort(tmpList);
        return tmpList.get(2);
    }
}
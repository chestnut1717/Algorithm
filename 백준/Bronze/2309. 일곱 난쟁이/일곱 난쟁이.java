import java.util.Scanner;
import java.util.Arrays;

class Main {
    static int[] arr = new int[9];
    static int sum = 0;
    
    public static void main(String[] args) {
        // 1. Input N
        Scanner sc = new Scanner(System.in);
        // 2. Input 9 heights
        for(int i = 0; i < 9; i++) {
            arr[i] = sc.nextInt();
            sum += arr[i];
        }
        
        // 3. Print for ascending order
        Arrays.sort(arr);
        
        solve();
    }
    // 4. Brute-force for find max 100;
    static void solve() {
        for(int i = 0; i < 9; i++) {
            for(int j = i+1; j < 9; j++) {
                if((sum - arr[i] - arr[j]) == 100) {
                    for(int k = 0; k < 9; k++) {
                        if (k == i || k == j) continue;
                        else System.out.println(arr[k]);
                    }
                    return;
                }
            }
        }
    }
}
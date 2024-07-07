import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        String[] arr = s.split(" ");

        String a = arr[0];
        String b = arr[1];

        if (a.length() != b.length()) {
            System.out.println(0);
            return;
        }

        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if(a.charAt(i) == '8' && b.charAt(i) == '8'){
                count ++;
            }else if(a.charAt(i) == b.charAt(i)){
                continue;
            }else{
                break;
            }
        }

        System.out.println(count);
    }
}
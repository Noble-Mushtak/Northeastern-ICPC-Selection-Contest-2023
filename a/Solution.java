import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String C = st.nextToken();
        int numer = 0;
        int denom = 0;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                for (int k = 0; k < 26; k++) {
                    for (int l = 0; l < 26; l++) {
                        if ((C.charAt(0)-'a') == i) continue;
                        if ((C.charAt(1)-'a') == j) continue;
                        if ((C.charAt(2)-'a') == k) continue;
                        if ((C.charAt(3)-'a') == l) continue;
                        denom += 1;
                        if ((i != j) && (j != k) && (k != l)) {
                            numer += 1;
                        }
                    }
                }
            }
        }
        System.out.println(((double)numer)/((double)denom));
    }
}

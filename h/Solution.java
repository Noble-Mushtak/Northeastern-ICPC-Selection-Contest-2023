import java.io.*;
import java.util.*;

public class Solution {
    static final int INF = 1000000003;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int totalSum = 0;
        st = new StringTokenizer(br.readLine());
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            a.add(Integer.parseInt(st.nextToken()));
            totalSum += a.get(i);
        }
        
        st = new StringTokenizer(br.readLine());
        ArrayList<Integer> goodHeights = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            goodHeights.add(Integer.parseInt(st.nextToken()));
        }

        ArrayList<ArrayList<Integer>> dp = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            dp.add(new ArrayList<>());
            for (int j = 0; j <= totalSum; j++) {
                dp.get(i).add(INF);
            }
            
            for (int j = 0; j <= totalSum; j++) {
                int prevDpVal = 0;
                if (i > 0) prevDpVal = dp.get(i-1).get(j);
                else if (j != 0) continue;
                for (int g : goodHeights) {
                    int newSum = j+g;
                    if (newSum > totalSum) continue;
                    dp.get(i).set(newSum, Math.min(dp.get(i).get(newSum), prevDpVal+Math.abs(a.get(i)-g)));
                }
            }
        }
        int ans = dp.get(N-1).get(totalSum);
        if (ans == INF) ans = -1;
        System.out.println(ans);
    }
}

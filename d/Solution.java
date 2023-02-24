import java.io.*;
import java.util.*;

public class Solution {
    static class Interval implements Comparable<Interval>{
        int beg;
        int end;
        Interval(int b, int e) {
            beg = b;
            end = e;
        }

        @Override
        public int compareTo(Interval other) {
            if (end != other.end) return Integer.valueOf(end).compareTo(other.end);
            return Integer.valueOf(beg).compareTo(other.beg);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        ArrayList<Interval> intervals = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int beg = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            intervals.add(new Interval(beg, end));
        }
        
        Collections.sort(intervals);
        int mxSoFar = intervals.get(0).end;
        int ans = 1;
        for (int i = 1; i < N; i++) {
            if (intervals.get(i).beg > mxSoFar) {
                int cyclesNeeded = (intervals.get(i).beg-mxSoFar+K-1)/K;
                ans += cyclesNeeded;
                mxSoFar = Math.min(intervals.get(i).end, mxSoFar+cyclesNeeded*K);
            }
        }
        System.out.println(ans);
    }
}

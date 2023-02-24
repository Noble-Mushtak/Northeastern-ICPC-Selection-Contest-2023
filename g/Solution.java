import java.io.*;
import java.util.*;

public class Solution {
    static class Pair {
        int u;
        int d;
        Pair(int u_, int d_) {
            u = u_;
            d = d_;
        }
    }
    static final int MAXN = 4000;
    static long MOD;
    static ArrayList<Long> fact;
    static ArrayList<Long> factInv;

    static long invert(long n) {
        long coeff11 = 1;
        long coeff12 = 0;
        long val1 = MOD;
        long coeff21 = 0;
        long coeff22 = 1;
        long val2 = n;
        while (val2 > 0) {
            long divBy = val1/val2;
            long newCoeff1 = coeff11-divBy*coeff21;
            long newCoeff2 = coeff12-divBy*coeff22;
            long newVal = val1-divBy*val2;
            coeff11 = coeff21;
            coeff12 = coeff22;
            val1 = val2;
            coeff21 = newCoeff1;
            coeff22 = newCoeff2;
            val2 = newVal;
        }
        if (coeff12 < MOD) coeff12 += MOD;
        return coeff12;
    }

    static long choose(int A, int B) {
        long tmp = (fact.get(A)*factInv.get(B)) % MOD;
        return (tmp*factInv.get(A-B)) % MOD;
    }

    static int solve(int U, int D) {
        if (D > U) return 0;
        if (D == 0) return 1;
        long ans = choose(U+D, D)-choose(U+D, D-1);
        if (ans < 0) ans += MOD;
        return (int)ans;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        MOD = Long.parseLong(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        fact = new ArrayList<>();
        fact.add(1L);
        for (int i = 1; i <= MAXN; i++) {
            fact.add((fact.get(i-1)*i) % MOD);
        }
        factInv = new ArrayList<>();
        for (int i = 0; i <= MAXN; i++) {
            factInv.add(invert(fact.get(i)));
        }

        ArrayList<Pair> answers = new ArrayList<>();
        for (int i = 0; i < MOD; i++) {
            answers.add(null);
        }
        
        for (int U = 0; U <= 2000; U++) {
            for (int D = 0; D <= 2000; D++) {
                int res = solve(U, D);
                if (answers.get(res) == null) {
                    answers.set(res, new Pair(U, D));
                }
            }
        }

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            if (answers.get(M) == null) System.out.println(-1);
            else {
                System.out.println(answers.get(M).u+" "+answers.get(M).d);
            }
        }
    }
}

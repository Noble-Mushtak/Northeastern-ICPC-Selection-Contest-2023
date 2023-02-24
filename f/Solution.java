import java.io.*;
import java.util.*;
import java.math.*;

public class Solution {
    static class Frac {
        BigInteger n;
        BigInteger d;
        Frac(BigInteger n_, BigInteger d_) {
            n = n_;
            d = d_;
        }
        static Frac fromInt(int n) {
            return new Frac(BigInteger.valueOf(n), BigInteger.valueOf(1));
        }
    }
    static final Frac ZERO = Frac.fromInt(0);
    static final Frac TWO = Frac.fromInt(2);
    static class Point {
        Frac x;
        Frac y;
        Point(Frac x_, Frac y_) {
            x = x_;
            y = y_;
        }
    }
    static class Circle {
        Frac cx;
        Frac cy;
        Frac rad;
        Circle(Frac x, Frac y, Frac r) {
            cx = x;
            cy = y;
            rad = r;
        }
    }

    static Frac addf(Frac f1, Frac f2) {
        return new Frac(f1.n.multiply(f2.d).add(f1.d.multiply(f2.n)), f1.d.multiply(f2.d));
    }

    static Frac subf(Frac f1, Frac f2) {
        return new Frac(f1.n.multiply(f2.d).subtract(f1.d.multiply(f2.n)), f1.d.multiply(f2.d));
    }

    static Frac mulf(Frac f1, Frac f2) {
        return new Frac(f1.n.multiply(f2.n), f1.d.multiply(f2.d));
    }

    static Frac divf(Frac f1, Frac f2) {
        return new Frac(f1.n.multiply(f2.d), f1.d.multiply(f2.n));
    }

    static Frac dist2(Point p1, Point p2) {
        return addf(mulf(subf(p1.x, p2.x), subf(p1.x, p2.x)), mulf(subf(p1.y, p2.y), subf(p1.y, p2.y)));
    }

    static Point ccCenter(Point p1, Point p2, Point p3) {
        Point b = new Point(subf(p3.x, p1.x), subf(p3.y, p1.y));
        Point c = new Point(subf(p2.x, p1.x), subf(p2.y, p1.y));
        Frac factor = mulf(TWO, subf(mulf(b.x, c.y), mulf(b.y, c.x)));
        Point p = new Point(addf(p1.x, divf(subf(mulf(c.y, dist2(p1, p3)), mulf(b.y, dist2(p1, p2))), factor)), addf(p1.y, divf(subf(mulf(b.x, dist2(p1, p2)), mulf(c.x, dist2(p1, p3))), factor)));
        return p;
    }

    static boolean gtn(Frac f1, Frac f2) {
        return f1.n.multiply(f2.d).compareTo(f1.d.multiply(f2.n)) > 0;
    }

    static Circle mec(ArrayList<Point> pts) {
        Collections.shuffle(pts);
        Point o = pts.get(0);
        Frac r = ZERO;
        for (int i = 0; i < pts.size(); i++) {
            if (gtn(dist2(o, pts.get(i)), r)) {
                o = pts.get(i);
                r = ZERO;
                for (int j = 0; j < i; j++) {
                    if (gtn(dist2(o, pts.get(j)), r)) {
                        o = new Point(divf(addf(pts.get(i).x, pts.get(j).x), TWO), divf(addf(pts.get(i).y, pts.get(j).y), TWO));
                        r = dist2(o, pts.get(i));
                        for (int k = 0; k < j; k++) {
                            if (gtn(dist2(o, pts.get(k)), r)) {
                                o = ccCenter(pts.get(i), pts.get(j), pts.get(k));
                                r = dist2(o, pts.get(i));
                            }
                        }
                    }
                }
            }
        }
        return new Circle(o.x, o.y, r);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Point> oldPoints = new ArrayList<>();
        ArrayList<Point> newPoints = new ArrayList<>();
        Circle curMec = null;
        ArrayList<Integer> answers = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if (i < M) oldPoints.add(new Point(Frac.fromInt(x), Frac.fromInt(y)));
            else {
                if (curMec == null) {
                    curMec = mec(oldPoints);
                }
                if (!gtn(dist2(new Point(curMec.cx, curMec.cy), new Point(Frac.fromInt(x), Frac.fromInt(y))), curMec.rad)) {
                    answers.add(i);
                }
            }
        }

        System.out.println(answers.size());
        for (int i = 0; i < answers.size(); i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(answers.get(i)+1);
        }
        System.out.println();
    }
}

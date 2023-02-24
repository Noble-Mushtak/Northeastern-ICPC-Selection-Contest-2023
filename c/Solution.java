import java.io.*;
import java.util.*;

public class Solution {
    static final int INF = 1000000003;
    
    static class Query {
        int idx;
        int re;
        int ce;
        Query(int i, int r, int c) {
            idx = i;
            re = r;
            ce = c;
        }
    }
    static class Point {
        int r;
        int c;
        Point(int r_, int c_) {
            r = r_;
            c = c_;
        }
    }

    static void handleNeighbor(ArrayList<ArrayList<Integer>> dst, LinkedList<Point> bfsQ, int r, int c, int curDst) {
        if (dst.get(r).get(c) == INF) {
            dst.get(r).set(c, curDst+1);
            bfsQ.add(new Point(r, c));
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<ArrayList<Point>>> warps = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            warps.add(new ArrayList<>());
            for (int j = 0; j < N; j++) {
                warps.get(i).add(new ArrayList<>());
            }
        }

        ArrayList<ArrayList<ArrayList<Query>>> queries = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            queries.add(new ArrayList<>());
            for (int j = 0; j < N; j++) {
                queries.get(i).add(new ArrayList<>());
            }
        }

        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            r1 -= 1;
            c1 -= 1;
            r2 -= 1;
            c2 -= 1;
            warps.get(r1).get(c1).add(new Point(r2, c2));
        }
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int rs = Integer.parseInt(st.nextToken());
            int cs = Integer.parseInt(st.nextToken());
            int re = Integer.parseInt(st.nextToken());
            int ce = Integer.parseInt(st.nextToken());
            rs -= 1;
            cs -= 1;
            re -= 1;
            ce -= 1;
            queries.get(rs).get(cs).add(new Query(i, re, ce));
        }

        ArrayList<Integer> answers = new ArrayList<>();
        for (int i = 0; i < T; i++) answers.add(0);
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ArrayList<ArrayList<Integer>> dst = new ArrayList<>();
                for (int k = 0; k < N; k++) {
                    dst.add(new ArrayList<>());
                    for (int l = 0; l < N; l++) {
                        dst.get(k).add(INF);
                    }
                }
                dst.get(i).set(j, 0);
                LinkedList<Point> bfsQ = new LinkedList<>();
                bfsQ.add(new Point(i, j));
                while (!bfsQ.isEmpty()) {
                    Point curPoint = bfsQ.poll();
                    int curDst = dst.get(curPoint.r).get(curPoint.c);
                    if (curPoint.r > 0) handleNeighbor(dst, bfsQ, curPoint.r-1, curPoint.c, curDst);
                    if (curPoint.r < N-1) handleNeighbor(dst, bfsQ, curPoint.r+1, curPoint.c, curDst);
                    if (curPoint.c > 0) handleNeighbor(dst, bfsQ, curPoint.r, curPoint.c-1, curDst);
                    if (curPoint.c < N-1) handleNeighbor(dst, bfsQ, curPoint.r, curPoint.c+1, curDst);
                    handleNeighbor(dst, bfsQ, N-1-curPoint.c, curPoint.r, curDst);
                    for (Point p : warps.get(curPoint.r).get(curPoint.c)) {
                        handleNeighbor(dst, bfsQ, p.r, p.c, curDst);
                    }
                }

                for (Query q : queries.get(i).get(j)) {
                    answers.set(q.idx, dst.get(q.re).get(q.ce));
                }
            }
        }

        PrintWriter pw = new PrintWriter(System.out);
        for (int i = 0; i < T; i++) {
            pw.println(answers.get(i));
        }
        pw.flush();
    }
}

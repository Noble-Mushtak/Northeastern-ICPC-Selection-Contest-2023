import java.io.*;
import java.util.*;

public class Solution {
    static class Pair {
        int v;
        int w;
        Pair(int v_, int w_) {
            v = v_;
            w = w_;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Pair)) return false;
            Pair p = (Pair)o;
            return (v == p.v) && (w == p.w);
        }
        @Override
        public int hashCode() {
            return Objects.hash(v, w);
        }
    }
    static class Edge implements Comparable<Edge> {
        int u;
        int v;
        int w;
        Edge(int u_, int v_, int w_) {
            u = u_;
            v = v_;
            w = w_;
        }

        @Override
        public int compareTo(Edge e) {
            if (w != e.w) return Integer.valueOf(w).compareTo(e.w);
            if (v != e.v) return Integer.valueOf(v).compareTo(e.v);
            return Integer.valueOf(u).compareTo(e.u);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        
        ArrayList<HashSet<Pair>> neighbors = new ArrayList<>();
        for (int i = 0; i < N; i++) neighbors.add(new HashSet<>());
        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            a -= 1;
            b -= 1;
            neighbors.get(a).add(new Pair(b, w));
            neighbors.get(b).add(new Pair(a, w));
        }

        PriorityQueue<Edge> edgeQ = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            if (neighbors.get(i).size() == 1) {
                for (Pair p : neighbors.get(i)) {
                    edgeQ.add(new Edge(i, p.v, p.w));
                }
            }
        }

        PrintWriter pw = new PrintWriter(System.out);
        boolean prev = false;
        while (!edgeQ.isEmpty()) {
            Edge curEdge = edgeQ.poll();
            if (curEdge.w == N-1) break;
            
            if (prev) pw.print(" ");
            pw.print(curEdge.w);
            prev = true;
            
            neighbors.get(curEdge.v).remove(new Pair(curEdge.u, curEdge.w));
            if (neighbors.get(curEdge.v).size() == 1) {
                for (Pair p : neighbors.get(curEdge.v)) {
                    edgeQ.add(new Edge(curEdge.v, p.v, p.w));
                }
            }
        }
        pw.println();
        pw.flush();
    }
}

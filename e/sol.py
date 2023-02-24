"""INPUT TEMPLATE - I copy paste this part to the top of every solution"""
import sys

toks = (tok for tok in sys.stdin.read().split())
"""END OF TEMPLATE"""
from heapq import heappush, heappop

N = int(next(toks))
# neighbors[a] is set of form (b, w) representing edge between a and b of weight w
neighbors = [set() for _ in range(N)]
for _ in range(N-1):
    a = int(next(toks))-1
    b = int(next(toks))-1
    w = int(next(toks))
    neighbors[a].add((b, w))
    neighbors[b].add((a, w))

# edge_q is priority queue of edges of form (w, a, b) representing edge between a and b of weight w
edge_q = []
for a in range(N):
    if len(neighbors[a]) == 1:
        for b, w in neighbors[a]:
            heappush(edge_q, (w, a, b))

prev = False
while len(edge_q) > 0:
    w, a, b = heappop(edge_q)
    if w == N-1:
        break

    if prev: sys.stdout.write(" ")
    sys.stdout.write(str(w))
    prev = True

    neighbors[b].remove((a, w))
    if len(neighbors[b]) == 1:
        for c, w in neighbors[b]:
            heappush(edge_q, (w, b, c))

sys.stdout.write("\n")

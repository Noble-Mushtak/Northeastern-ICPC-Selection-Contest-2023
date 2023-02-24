"""INPUT TEMPLATE - I copy paste this part to the top of every solution"""
import sys

toks = (tok for tok in sys.stdin.read().split())
"""END OF TEMPLATE"""
from collections import deque

N = int(next(toks))
W = int(next(toks))
T = int(next(toks))

# waprs[st_x][st_y] is array of form (end_x, end_y)
warps = [[[] for _ in range(N)] for _ in range(N)]
for _ in range(W):
   r1 = int(next(toks))-1
   c1 = int(next(toks))-1
   r2 = int(next(toks))-1
   c2 = int(next(toks))-1
   warps[r1][c1].append((r2, c2))

# Model magic wand as warp for every cell:
for r1 in range(N):
   for c1 in range(N):
      warps[r1][c1].append((N-1-c1, r1))

# queries[st_x][st_y] is array of form (end_x, end_y, q_idx)
queries = [[[] for _ in range(N)] for _ in range(N)]
for i in range(T):
   r1 = int(next(toks))-1
   c1 = int(next(toks))-1
   r2 = int(next(toks))-1
   c2 = int(next(toks))-1
   queries[r1][c1].append((r2, c2, i))

dirs = [(-1, 0), (1, 0), (0, -1), (0, 1)]

answers = [None for _ in range(T)]

# Allocating memory in Python is slow -> Allocate dst and vis outside the for loops
dst = [[0 for _ in range(N)] for _ in range(N)]
vis = [[False for _ in range(N)] for _ in range(N)]

for r1 in range(N):
   for c1 in range(N):
      if len(queries[r1][c1]) == 0:
         continue
      bfs_q = deque()

      for r2 in range(N):
         for c2 in range(N):
            vis[r2][c2] = False
      
      vis[r1][c1] = True
      dst[r1][c1] = 0
      bfs_q.append((r1, c1))
      while len(bfs_q) > 0:
         cur_r, cur_c = bfs_q.popleft()
         for dr, dx in dirs:
            new_r = cur_r+dr
            new_c = cur_c+dx
            if new_r >= 0 and new_r < N and new_c >= 0 and new_c < N and not vis[new_r][new_c]:
               vis[new_r][new_c] = True
               dst[new_r][new_c] = dst[cur_r][cur_c]+1
               bfs_q.append((new_r, new_c))
         for new_r, new_c in warps[cur_r][cur_c]:
            if not vis[new_r][new_c]:
               vis[new_r][new_c] = True
               dst[new_r][new_c] = dst[cur_r][cur_c]+1
               bfs_q.append((new_r, new_c))

      for r2, c2, q_idx in queries[r1][c1]:
         answers[q_idx] = dst[r2][c2]
               
for d in answers: print(d)

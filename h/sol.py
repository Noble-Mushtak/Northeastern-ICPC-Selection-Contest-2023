"""INPUT TEMPLATE - I copy paste this part to the top of every solution"""
import sys

toks = (tok for tok in sys.stdin.read().split())
"""END OF TEMPLATE"""
from math import isinf

N = int(next(toks))
K = int(next(toks))

total_sum = 0
a = []
for _ in range(N):
    cur_height = int(next(toks))
    a.append(cur_height)
    total_sum += cur_height

good_heights = []
for _ in range(K):
    good_heights.append(int(next(toks)))

dp = [[float("inf") for _ in range(total_sum+1)] for _ in range(N)]
for i in range(N):
    for j in range(total_sum+1):
        if i == 0 and j != 0:
            break
        
        prev_dp_val = 0
        if i > 0:
            prev_dp_val = dp[i-1][j]
        for g in good_heights:
            new_sum = j+g
            if new_sum > total_sum:
                continue
            dp[i][new_sum] = min(dp[i][new_sum], prev_dp_val + abs(a[i]-g))

ans = dp[N-1][total_sum]
if isinf(ans): ans = -1
print(ans)

"""INPUT TEMPLATE - I copy paste this part to the top of every solution"""
import sys

toks = (tok for tok in sys.stdin.read().split())
"""END OF TEMPLATE"""

N = int(next(toks))
K = int(next(toks))

intervals = []
for _ in range(N):
    a = int(next(toks))
    b = int(next(toks))
    # We want to sort by end point first, and then by beginning point,
    # so we represent intervals using tuples of the form (end, beg)
    intervals.append((b, a))
intervals.sort()

mx_so_far = intervals[0][0]
ans = 1
for i in range(1, N):
    cur_end, cur_beg = intervals[i]
    if cur_beg > mx_so_far:
        cycles_needed = (cur_beg - mx_so_far + K-1) // K
        ans += cycles_needed
        mx_so_far = min(cur_end, mx_so_far+cycles_needed*K)
print(ans)

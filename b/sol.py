"""INPUT TEMPLATE - I copy paste this part to the top of every solution"""
import sys

toks = (tok for tok in sys.stdin.read().split())
"""END OF TEMPLATE"""

T = int(next(toks))
for _ in range(T):
   N = int(next(toks))
   K = int(next(toks))
   min_stickers = (N*(N+1)) // 2
   if K < min_stickers:
      print(1000000*(min_stickers - K))
   else:
      print((K - min_stickers) % N)

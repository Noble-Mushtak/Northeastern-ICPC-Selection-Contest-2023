"""INPUT TEMPLATE - I copy paste this part to the top of every solution"""
import sys

toks = (tok for tok in sys.stdin.read().split())
"""END OF TEMPLATE"""

C = next(toks)
numer = 0
denom = 0
for i in range(ord("a"), ord("z")+1):
   for j in range(ord("a"), ord("z")+1):
       for k in range(ord("a"), ord("z")+1):
           for l in range(ord("a"), ord("z")+1):
               if ord(C[0]) == i: continue
               if ord(C[1]) == j: continue
               if ord(C[2]) == k: continue
               if ord(C[3]) == l: continue
               denom += 1
               if i != j and j != k and k != l:
                   numer += 1
print(numer/denom)

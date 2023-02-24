The key idea behind this problem is brute force: There are 26^4=456976 strings consisting of four lowercase letters, so you can brute force all of them to count the number of C-good character codes (the denominator of our probability) and the number of C-good character codes with no two equal consecutive characters (the numerator of our probability).

One notable part of this problem is, the denominator is always 25^4, meaning if you multiply the numerator and denominator by 4^4, you get a fraction with denominator 10^8, in which case you can just print out `0.` followed by the numerator in order to print the probability. (We don't need to add leading zeroes to the numerator, because the probability is always at least 0.1, which means no leading zeroes are necessary.) Therefore, floating-point numbers are not necessary for this problem. A solution without floating-point numbers is demonstrated in `sol.cpp`.
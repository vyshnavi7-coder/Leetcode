import java.util.*;

class Solution {
    public String smallestNumber(String num, long t) {
        long tmp = t;
        for (int p : new int[]{2, 3, 5, 7}) {
            while (tmp % p == 0) {
                tmp /= p;
            }
        }
        if (tmp != 1) {
            return "-1";
        }

        int n = num.length();
        int[] s = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = num.charAt(i) - '0';
        }

        int zero = num.indexOf('0');
        if (zero == -1) {
            long currentProductRem = t;
            for (int i = 0; i < n; i++) {
                currentProductRem /= gcd(currentProductRem, s[i]);
            }
            if (currentProductRem == 1) {
                return num;
            }
        }

        long[] p = new long[n + 1];
        p[0] = t;
        for (int i = 0; i < n; i++) {
            p[i + 1] = p[i] / gcd(p[i], Math.max(1, s[i]));
        }

        int startPos = (zero != -1) ? zero : n - 1;
        for (int i = startPos; i >= 0; i--) {
            int startDigit = (i == zero) ? 1 : s[i] + 1;
            for (int d = startDigit; d <= 9; d++) {
                long remainingT = p[i] / gcd(p[i], d);
                int remainingLength = n - 1 - i;
                if (canForm(remainingT, remainingLength)) {
                    s[i] = d;
                    fillRemaining(s, i + 1, remainingT);
                    StringBuilder sb = new StringBuilder();
                    for (int digit : s) {
                        sb.append(digit);
                    }
                    return sb.toString();
                }
            }
        }

        int totalLen = n + 1;
        while (true) {
            if (canForm(t, totalLen)) {
                int[] ans = new int[totalLen];
                fillRemaining(ans, 0, t);
                StringBuilder sb = new StringBuilder();
                for (int digit : ans) {
                    sb.append(digit);
                }
                return sb.toString();
            }
            totalLen++;
        }
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private boolean canForm(long remainingT, int length) {
        if (remainingT == 1) {
            return true;
        }
        int c2 = 0, c3 = 0, c5 = 0, c7 = 0;
        while (remainingT % 2 == 0) { c2++; remainingT /= 2; }
        while (remainingT % 3 == 0) { c3++; remainingT /= 3; }
        while (remainingT % 5 == 0) { c5++; remainingT /= 5; }
        while (remainingT % 7 == 0) { c7++; remainingT /= 7; }
        if (remainingT != 1) return false;

        int requiredDigits = c7 + c5;
        
        int rem3 = c3 % 2;
        int pairsOf3 = c3 / 2;
        requiredDigits += pairsOf3;
        
        if (rem3 == 1) {
            if (c2 >= 1) {
                c2--;
                requiredDigits++; 
            } else {
                requiredDigits++; 
            }
        }
        
        if (c2 > 0) {
            requiredDigits += (c2 + 2) / 3;
        }
        
        return requiredDigits <= length;
    }

    private void fillRemaining(int[] s, int start, long remainingT) {
        int c2 = 0, c3 = 0, c5 = 0, c7 = 0;
        while (remainingT % 2 == 0) { c2++; remainingT /= 2; }
        while (remainingT % 3 == 0) { c3++; remainingT /= 3; }
        while (remainingT % 5 == 0) { c5++; remainingT /= 5; }
        while (remainingT % 7 == 0) { c7++; remainingT /= 7; }

        int n = s.length;
        int idx = n - 1;
        while (c7 > 0) { s[idx--] = 7; c7--; }
        while (c5 > 0) { s[idx--] = 5; c5--; }

        while (c2 >= 3) { s[idx--] = 8; c2 -= 3; }
        while (c3 >= 2) { s[idx--] = 9; c3 -= 2; }

        if (c3 == 1 && c2 == 1) {
            s[idx--] = 6;
            c2--;
            c3--;
        } else if (c3 == 1 && c2 == 2) {
            s[idx--] = 6;
            s[idx--] = 2;
            c2 -= 2;
            c3--;
        } else if (c3 == 1) {
            s[idx--] = 3;
            c3--;
        }

        while (c2 >= 2) { s[idx--] = 4; c2 -= 2; }
        while (c2 >= 1) { s[idx--] = 2; c2 -= 1; }

        while (idx >= start) {
            s[idx--] = 1;
        }
        Arrays.sort(s, start, n);
    }
}

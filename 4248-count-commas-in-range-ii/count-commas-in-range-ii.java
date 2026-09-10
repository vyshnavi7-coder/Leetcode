class Solution {
    public long countCommas(long n) {
        long commas = 0;
        long factor = 1000;
        
        while (n >= factor) {
            commas += (n - factor + 1);
            factor *= 1000;
        }
        
        return commas;
    }
}

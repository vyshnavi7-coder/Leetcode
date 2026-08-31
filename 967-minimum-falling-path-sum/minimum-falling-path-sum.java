class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        
        for (int r = 1; r < n; r++) {
            for (int c = 0; r < n && c < n; c++) {
                int mid = matrix[r - 1][c];
                int left = (c > 0) ? matrix[r - 1][c - 1] : Integer.MAX_VALUE;
                int right = (c < n - 1) ? matrix[r - 1][c + 1] : Integer.MAX_VALUE;
                
                matrix[r][c] += Math.min(mid, Math.min(left, right));
            }
        }
        
        int minSum = Integer.MAX_VALUE;
        for (int c = 0; c < n; c++) {
            minSum = Math.min(minSum, matrix[n - 1][c]);
        }
        
        return minSum;
    }
}

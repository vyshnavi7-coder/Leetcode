class Solution {
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] memo = new int[rows][cols];
        int maxLength = 0;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maxLength = Math.max(maxLength, dfs(matrix, i, j, memo));
            }
        }
        
        return maxLength;
    }
    
    private int dfs(int[][] matrix, int r, int c, int[][] memo) {
        if (memo[r][c] != 0) return memo[r][c];
        
        int max = 1;
        for (int[] dir : DIRECTIONS) {
            int nextR = r + dir[0];
            int nextC = c + dir[1];
            
            if (nextR >= 0 && nextR < matrix.length && nextC >= 0 && nextC < matrix[0].length 
                && matrix[nextR][nextC] > matrix[r][c]) {
                max = Math.max(max, 1 + dfs(matrix, nextR, nextC, memo));
            }
        }
        
        memo[r][c] = max;
        return max;
    }
}

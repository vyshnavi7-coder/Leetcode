import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int largestIsland(int[][] grid) {
        int n = grid.length;
        int islandId = 2;
        Map<Integer, Integer> islandSizes = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int size = dfs(grid, i, j, islandId);
                    islandSizes.put(islandId, size);
                    islandId++;
                }
            }
        }
        
        int maxIsland = islandSizes.getOrDefault(2, 0);
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    Set<Integer> neighborIslands = new HashSet<>();
                    for (int[] dir : dirs) {
                        int ni = i + dir[0];
                        int nj = j + dir[1];
                        if (ni >= 0 && ni < n && nj >= 0 && nj < n && grid[ni][nj] > 1) {
                            neighborIslands.add(grid[ni][nj]);
                        }
                    }
                    
                    int currentSize = 1;
                    for (int id : neighborIslands) {
                        currentSize += islandSizes.get(id);
                    }
                    maxIsland = Math.max(maxIsland, currentSize);
                }
            }
        }
        
        return maxIsland == 0 ? n * n : maxIsland;
    }
    
    private int dfs(int[][] grid, int r, int c, int id) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid.length || grid[r][c] != 1) {
            return 0;
        }
        grid[r][c] = id;
        return 1 + dfs(grid, r + 1, c, id) 
                 + dfs(grid, r - 1, c, id) 
                 + dfs(grid, r, c + 1, id) 
                 + dfs(grid, r, c - 1, id);
    }
}

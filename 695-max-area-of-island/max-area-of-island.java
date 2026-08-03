class Solution {

    public int maxAreaOfIsland(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        boolean[][] visited = new boolean[rows][cols];

        int maxArea = 0;

        for (int row = 0; row < rows; row++) {

            for (int col = 0; col < cols; col++) {

                if (grid[row][col] == 1 && !visited[row][col]) {

                    int area = dfs(row, col, grid, visited);

                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        return maxArea;
    }

    private int dfs(int row, int col, int[][] grid, boolean[][] visited) {

        if (row < 0 || row >= grid.length ||
            col < 0 || col >= grid[0].length ||
            grid[row][col] == 0 ||
            visited[row][col]) {

            return 0;
        }

        visited[row][col] = true;

        int up = dfs(row - 1, col, grid, visited);
        int down = dfs(row + 1, col, grid, visited);
        int left = dfs(row, col - 1, grid, visited);
        int right = dfs(row, col + 1, grid, visited);

        return 1 + up + down + left + right;
    }
}
class Solution {

    public int numIslands(char[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        boolean[][] visited = new boolean[rows][cols];

        int islands = 0;

        for (int row = 0; row < rows; row++) {

            for (int col = 0; col < cols; col++) {

                if (grid[row][col] == '1' && !visited[row][col]) {

                    islands++;

                    dfs(row, col, grid, visited);

                }

            }

        }

        return islands;
    }

    private void dfs(int row, int col,
                     char[][] grid,
                     boolean[][] visited) {

        if (row < 0 || row >= grid.length ||
            col < 0 || col >= grid[0].length ||
            grid[row][col] == '0' ||
            visited[row][col]) {

            return;
        }

        visited[row][col] = true;

        dfs(row - 1, col, grid, visited); // Up
        dfs(row + 1, col, grid, visited); // Down
        dfs(row, col - 1, grid, visited); // Left
        dfs(row, col + 1, grid, visited); // Right
    }
}
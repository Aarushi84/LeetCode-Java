class Solution {

    int rows;
    int cols;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {

        rows = heights.length;
        cols = heights[0].length;

        boolean[][] pacific = new boolean[rows][cols];
        boolean[][] atlantic = new boolean[rows][cols];

        // Pacific (Top Row)
        for (int col = 0; col < cols; col++) {
            dfs(0, col, heights, pacific);
        }

        // Pacific (Left Column)
        for (int row = 0; row < rows; row++) {
            dfs(row, 0, heights, pacific);
        }

        // Atlantic (Bottom Row)
        for (int col = 0; col < cols; col++) {
            dfs(rows - 1, col, heights, atlantic);
        }

        // Atlantic (Right Column)
        for (int row = 0; row < rows; row++) {
            dfs(row, cols - 1, heights, atlantic);
        }

        List<List<Integer>> ans = new ArrayList<>();

        for (int row = 0; row < rows; row++) {

            for (int col = 0; col < cols; col++) {

                if (pacific[row][col] && atlantic[row][col]) {

                    ans.add(Arrays.asList(row, col));

                }
            }
        }

        return ans;
    }

    public void dfs(int row,
                    int col,
                    int[][] heights,
                    boolean[][] visited) {

        if (visited[row][col]) {
            return;
        }

        visited[row][col] = true;

        int[][] directions = {
                {-1,0},
                {1,0},
                {0,-1},
                {0,1}
        };

        for (int[] dir : directions) {

            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if (newRow >= 0 &&
                newRow < rows &&
                newCol >= 0 &&
                newCol < cols &&
                heights[newRow][newCol] >= heights[row][col]) {

                dfs(newRow, newCol, heights, visited);
            }
        }
    }
}
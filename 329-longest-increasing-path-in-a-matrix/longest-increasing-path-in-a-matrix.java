class Solution {

    int rows;
    int cols;

    int[][] memo;

    int[][] directions = {
        {-1, 0},   // up
        {1, 0},    // down
        {0, -1},   // left
        {0, 1}     // right
    };

    public int longestIncreasingPath(int[][] matrix) {

        rows = matrix.length;
        cols = matrix[0].length;

        memo = new int[rows][cols];

        int answer = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                answer = Math.max(
                    answer,
                    dfs(row, col, matrix)
                );
            }
        }

        return answer;
    }

    private int dfs(int row, int col, int[][] matrix) {

        // Already calculated
        if (memo[row][col] != 0) {
            return memo[row][col];
        }

        int maxPath = 1;

        for (int[] dir : directions) {

            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if (newRow >= 0 &&
                newRow < rows &&
                newCol >= 0 &&
                newCol < cols &&
                matrix[newRow][newCol] > matrix[row][col]) {

                int path = 1 + dfs(newRow, newCol, matrix);

                maxPath = Math.max(maxPath, path);
            }
        }

        memo[row][col] = maxPath;

        return maxPath;
    }
}
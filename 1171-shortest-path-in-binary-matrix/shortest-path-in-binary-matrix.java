class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {

        int n = grid.length;

        // If starting or ending cell is blocked
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        }

        Queue<int[]> queue = new LinkedList<>();

        // row, column, distance
        queue.offer(new int[]{0, 0, 1});

        // Mark starting cell as visited
        grid[0][0] = 1;

        // 8 possible directions
        int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},           {0, 1},
            {1, -1},  {1, 0},  {1, 1}
        };

        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            int row = current[0];
            int col = current[1];
            int distance = current[2];

            // We reached the destination
            if (row == n - 1 && col == n - 1) {
                return distance;
            }

            // Check all 8 neighbours
            for (int[] direction : directions) {

                int newRow = row + direction[0];
                int newCol = col + direction[1];

                // Check if the new cell is valid and unvisited
                if (newRow >= 0 && newRow < n &&
                    newCol >= 0 && newCol < n &&
                    grid[newRow][newCol] == 0) {

                    queue.offer(new int[]{newRow, newCol, distance + 1});

                    // Mark as visited
                    grid[newRow][newCol] = 1;
                }
            }
        }

        return -1;
    }
}
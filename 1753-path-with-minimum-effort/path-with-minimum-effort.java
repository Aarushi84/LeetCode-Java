import java.util.*;

class Solution {
    public int minimumEffortPath(int[][] heights) {

        int rows = heights.length;
        int cols = heights[0].length;

        // effort[row][col] = minimum effort needed to reach this cell
        int[][] effort = new int[rows][cols];

        for (int[] row : effort) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // {effort, row, col}
        PriorityQueue<int[]> pq =
            new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Start from (0,0) with effort 0
        effort[0][0] = 0;
        pq.offer(new int[]{0, 0, 0});

        // Up, Down, Left, Right
        int[][] directions = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
        };

        while (!pq.isEmpty()) {

            int[] current = pq.poll();

            int currentEffort = current[0];
            int row = current[1];
            int col = current[2];

            // If we reached the destination
            if (row == rows - 1 && col == cols - 1) {
                return currentEffort;
            }

            // Check 4 directions
            for (int[] direction : directions) {

                int newRow = row + direction[0];
                int newCol = col + direction[1];

                // Check if inside the grid
                if (newRow >= 0 && newRow < rows &&
                    newCol >= 0 && newCol < cols) {

                    int difference = Math.abs(
                        heights[row][col] - heights[newRow][newCol]
                    );

                    // Maximum difference encountered on this path
                    int newEffort = Math.max(
                        currentEffort,
                        difference
                    );

                    // If this path is better
                    if (newEffort < effort[newRow][newCol]) {

                        effort[newRow][newCol] = newEffort;

                        pq.offer(new int[]{
                            newEffort,
                            newRow,
                            newCol
                        });
                    }
                }
            }
        }

        return -1;
    }
}
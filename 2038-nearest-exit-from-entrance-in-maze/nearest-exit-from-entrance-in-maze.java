class Solution {
    public int nearestExit(char[][] maze, int[] entrance) {

        int m = maze.length;
        int n = maze[0].length;

        boolean[][] visited = new boolean[m][n];

        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{
            entrance[0],
            entrance[1],
            0
        });

        visited[entrance[0]][entrance[1]] = true;

        int[][] directions = {
            {-1, 0},  // up
            {1, 0},   // down
            {0, -1},  // left
            {0, 1}    // right
        };

        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            int row = current[0];
            int col = current[1];
            int distance = current[2];

            for (int[] dir : directions) {

                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // Check if outside the maze
                if (newRow < 0 || newRow >= m ||
                    newCol < 0 || newCol >= n) {
                    continue;
                }

                // Check if it is a wall
                if (maze[newRow][newCol] == '+') {
                    continue;
                }

                // Check if already visited
                if (visited[newRow][newCol]) {
                    continue;
                }

                // Mark visited
                visited[newRow][newCol] = true;

                // Check if this new cell is an exit
                if (newRow == 0 || newRow == m - 1 ||
                    newCol == 0 || newCol == n - 1) {

                    return distance + 1;
                }

                // Add the cell to the queue
                queue.offer(new int[]{
                    newRow,
                    newCol,
                    distance + 1
                });
            }
        }

        return -1;
    }
}
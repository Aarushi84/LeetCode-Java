class Solution {
    public int[][] updateMatrix(int[][] mat) {

        int rows = mat.length;
        int cols = mat[0].length;

        Queue<int[]> queue = new LinkedList<>();

        int[][] dist = new int[rows][cols];

        // Put all 0s in queue, mark 1s as -1
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                if (mat[row][col] == 0) {
                    queue.offer(new int[]{row, col});
                } else {
                    dist[row][col] = -1;
                }
            }
        }

        int[][] directions = {
                {-1,0},
                {1,0},
                {0,-1},
                {0,1}
        };

        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            int row = current[0];
            int col = current[1];

            for (int[] dir : directions) {

                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow >= 0 &&
                    newRow < rows &&
                    newCol >= 0 &&
                    newCol < cols &&
                    dist[newRow][newCol] == -1) {

                    dist[newRow][newCol] = dist[row][col] + 1;

                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }

        return dist;
    }
} 
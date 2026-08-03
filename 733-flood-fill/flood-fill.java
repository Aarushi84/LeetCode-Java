class Solution {

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {

        int originalColor = image[sr][sc];

        if (originalColor == color)
            return image;

        dfs(sr, sc, image, originalColor, color);

        return image;
    }

    private void dfs(int row, int col, int[][] image,
                     int originalColor, int color) {

        if (row < 0 || row >= image.length ||
            col < 0 || col >= image[0].length ||
            image[row][col] != originalColor) {

            return;
        }

        image[row][col] = color;

        dfs(row - 1, col, image, originalColor, color);
        dfs(row + 1, col, image, originalColor, color);
        dfs(row, col - 1, image, originalColor, color);
        dfs(row, col + 1, image, originalColor, color);
    }
}
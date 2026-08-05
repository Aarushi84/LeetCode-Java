class Solution {

    public void solve(char[][] board) {

        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {

            if (board[i][0] == 'O')
                dfs(i, 0, board);

            if (board[i][cols - 1] == 'O')
                dfs(i, cols - 1, board);
        }

        for (int j = 0; j < cols; j++) {

            if (board[0][j] == 'O')
                dfs(0, j, board);

            if (board[rows - 1][j] == 'O')
                dfs(rows - 1, j, board);
        }

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                if (board[i][j] == 'O')
                    board[i][j] = 'X';

                else if (board[i][j] == '#')
                    board[i][j] = 'O';
            }
        }
    }

    private void dfs(int row, int col, char[][] board) {

        if (row < 0 || row >= board.length ||
            col < 0 || col >= board[0].length ||
            board[row][col] != 'O')
            return;

        board[row][col] = '#';

        dfs(row - 1, col, board);
        dfs(row + 1, col, board);
        dfs(row, col - 1, board);
        dfs(row, col + 1, board);
    }
}
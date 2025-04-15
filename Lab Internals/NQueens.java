public class NQueens {
    static final int N = 4;

    static boolean isSafe(int board[][], int row, int col) {
        for (int i = 0; i < col; i++)
            if (board[row][i] == 1)
                return false;
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;
        for (int i = row, j = col; j >= 0 && i < N; i++, j--)
            if (board[i][j] == 1)
                return false;
        return true;
    }

    static boolean solveNQUtil(int board[][], int col) {
        if (col >= N)
            return true;
        for (int i = 0; i < N; i++) {
            if (isSafe(board, i, col)) {
                board[i][col] = 1;
                if (solveNQUtil(board, col + 1))
                    return true;
                board[i][col] = 0;
            }
        }
        return false;
    }

    static void printSolution(int board[][]) {
        for (int[] row : board) {
            for (int cell : row)
                System.out.print(cell + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] board = new int[N][N];
        if (!solveNQUtil(board, 0)) {
            System.out.println("Solution does not exist");
        } else {
            printSolution(board);
        }
    }
}

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] board = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        boolean isXTurn = true;
        int movesCount = 0;

        printBoard(board);

        while (true) {
            System.out.println("Enter the coordinates: ");
            String input = scanner.nextLine();
            String[] parts = input.split(" ");
            if (parts.length != 2) {
                System.out.println("You should enter numbers!");
                continue;
            }
            
            try {
                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);
                
                if (x < 1 || x > 3 || y < 1 || y > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }
                
                x--; y--; // Adjusting for 0-based index
                
                if (board[x][y] != ' ') {
                    System.out.println("This cell is occupied! Choose another one!");
                    continue;
                }

                board[x][y] = isXTurn ? 'X' : 'O';
                movesCount++;
                printBoard(board);

                if (checkWin(board, isXTurn ? 'X' : 'O')) {
                    System.out.println((isXTurn ? 'X' : 'O') + " wins");
                    break;
                }

                if (movesCount == 9) {
                    System.out.println("Draw");
                    break;
                }

                isXTurn = !isXTurn;

            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
            }
        }
    }

    public static void printBoard(char[][] board) {
        System.out.println("---------");
        for (char[] row : board) {
            System.out.print("| ");
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static boolean checkWin(char[][] board, char player) {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true;
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) return true;
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) return true;
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) return true;
        
        return false;
    }
}

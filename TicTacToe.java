public class TicTacToe {

  public void run() {
    char[][] board = new char [3][3];

    for(int row = 0; row < board.length; row++){
      for(int col = 0; col < board[row].length; col++){
        board[row][col] = ' ';

      }
    }

    char player = 'x';
    boolean gameOver = false;
  }
  
  
  
}
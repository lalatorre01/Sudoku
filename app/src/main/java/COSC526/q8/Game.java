package COSC526.q8;

public class Game {
    private int[][] board;
    private Sudoku sudoku;

    public Game() {
        //create sudoku
        sudoku = new Sudoku();
        //set board = to generate board
        board = sudoku.generate();
    }
    public void set(int value, int x, int y){
        //this method sets the value at x, y
        board[x][y] = value;

    }
    public boolean check(int value, int x, int y){
        //this method calls the checkRow, checkcolumn, and isValidInBox methods to
        //check whether value can be placed at x, y
        //returns true or false
        return checkRow(value, x) && checkColumn(value, y) && isValidInBox(value, x, y);
    }
    private boolean checkRow(int value, int x){
        //this method checks the rows of the board to determine
        //if board == value
        //returns true or false
        for(int i = 0; i < 9; i++){
            if(board[x][i] == value){
                return false;
            }
        }
        return true;
    }
    private boolean checkColumn(int value, int y){
        //this method checks the columns of the board to determine
        //if board == value
        //returns true or false
        for (int j = 0; j < 9; j++){
            if(board[j][y] == value){
                return false;
            }
        }
        return true;
    }
    private boolean isValidInBox(int value, int x, int y){
        //this method if the box is valid of the board
        //returns true or false
        int startX = (x / 3) * 3;
        int startY = (y / 3) * 3;
        for (int i = startX; i < startX + 3; i++) {
            for (int j = startY; j < startY + 3; j++) {
                if (board[i][j] == value) {
                    return false;
                }
            }
        }
        return true;
    }
    public int[][] getBoard() {
        //this method returns the board
        return board;
    }
}

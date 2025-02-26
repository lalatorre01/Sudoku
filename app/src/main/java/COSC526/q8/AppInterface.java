package COSC526.q8;

import android.content.Context;
import android.graphics.Color;
import android.text.TextWatcher;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AppInterface extends GridLayout {
    private EditText[][] board;
    private GridLayout boardLayout;

    public AppInterface(Context context) {
        super(context);
        final int DP = (int)(getResources().getDisplayMetrics().density);
        //create board in grid layout
        boardLayout = new GridLayout(context);
        boardLayout.setRowCount(9);
        boardLayout.setColumnCount(9);

        //initialize EditText array for board
        board = new EditText[9][9];

        //create EditText for board
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                board[i][j] = new EditText(context);
                board[i][j].setTextSize(30);
                board[i][j].setTextColor(Color.parseColor("#003759"));
                board[i][j].setWidth(60 * DP);
                board[i][j].setGravity(Gravity.CENTER);
                boardLayout.addView(board[i][j]);
            }
        }

        //add grid to the layout
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.topMargin = 60 *DP;
        boardLayout.setBackgroundColor(Color.parseColor("#D3EEFF"));
        boardLayout.setLayoutParams(params);
        params.width = 540 * DP;

        //add to view
        addView(boardLayout);

    }
    public int[][] drawInitialBoard(int[][] board){
        //display contents of board
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                EditText editText = this.board[i][j];
                int value = board[i][j];
                if(value == 0){
                    //if the value = 0, display value and set background color
                    editText.setText(" ");
                    editText.setBackgroundColor(Color.parseColor("#D3EEFF"));
                }else{
                    //else display different value, set different background color, and disable the edittext
                    editText.setText(String.valueOf(board[i][j]));
                    editText.setBackgroundColor(Color.parseColor("#517891"));
                    editText.setTextColor(Color.parseColor("#D3EEFF"));
                    editText.setEnabled(false);
                }
            }

        }
        return board;
    }
    public EditText getInput(int x, int y){
        //return input at x,y
        return board[x][y];
    }
    public void clear(int x, int y){
        //display empty string at x, y
        board[x][y].setText(" ");
    }
    public void setTextChangeHandler(TextWatcher textChangeHandler, int x, int y){
        board[x][y].addTextChangedListener(textChangeHandler);
    }
}

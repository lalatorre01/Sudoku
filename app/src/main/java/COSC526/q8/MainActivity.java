package COSC526.q8;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Game game;
    private AppInterface appInterface;
    private int[][] board;
    private Sudoku sudoku;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //create game
        game = new Game();
        //create appinterface
        appInterface = new AppInterface(this);
        //set thecontentview to appinterface
        setContentView(appInterface);
        //initialize board and sudoku
        board = game.getBoard();
        sudoku = new Sudoku();
        //get initial board and display in appinterface
        appInterface.drawInitialBoard(board);
        //attach all event handler to all edittext
        for(int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                TextChangeHandler temp = new TextChangeHandler(i,j);
                appInterface.setTextChangeHandler(temp, i, j);
            }
        }
    }
    public class TextChangeHandler implements TextWatcher{
        private int x;
        private int y;

        public TextChangeHandler(int x, int y) {
            //set x and y
           this.x = x;
           this.y = y;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable e) {
            //initialize value and get input at x and y
            int value = 0;
            String input = e.toString().trim();
            //check user input
            if(input.equals("")){ //if input is ""
                game.set(0, x, y); // set 0 at x, y on board
            } else if (input.equals("0")) { // if input if "0'
                game.getBoard()[x][y] = 0; // set 0 at x, y on board
                appInterface.clear(x, y); //clear x, y on interface
            } else if (input.length() > 1) { //if input length is greater than 1
                game.set(0, x, y); //set 0 at x, y on board
                appInterface.clear(x, y); //clear x, y on interface
            }else{
                try {
                    value = Integer.parseInt(input); //convert input to integer

                }catch (NumberFormatException ex){
                    value = 0;
                }
                // if value can be placed at x, y on game board
                if(game.check(value, x, y)){
                    game.set(value, x, y); //set value at x, y on board

                }else{
                    game.set(0, x, y); //set 0 at x, y on board
                    appInterface.clear(x, y);//clear x, y on interface
                }
            }
            //if the user solves the sudoku puzzle then a toast appears
            //the toast congratulates the player on completing the game
            if(sudoku.isSolved(board)){
                String text = "You solved the puzzle!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(MainActivity.this, text, duration);
                toast.show();
            }
        }

    }
}
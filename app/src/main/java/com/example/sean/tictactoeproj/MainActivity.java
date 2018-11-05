package com.example.sean.tictactoeproj;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends Activity implements OnClickListener {

    private char mBoard[] = {'1','2','3','4','5','6','7','8','9'};
    private final int BOARD_SIZE = 9;
    private Button square1, square2, square3, square4, square5, square6, square7, square8, square9, newGame;
    private ArrayList<Button> squareList = new ArrayList<Button>();
    private TextView outputTextView;
    public static final char HUMAN_PLAYER = 'X';
    public static final char COMPUTER_PLAYER = 'O';
    char turn = HUMAN_PLAYER;
    int win = 0;

    public boolean gameOver = false;

    private Random mRand;

    private void displayText() {
        if (win == 3) {
            outputTextView.setText("Player O Wins!");
        }

        if (win == 2) {
            outputTextView.setText("Player X Wins!");
        }

        if (win == 1) {
            outputTextView.setText("It's a tie!");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRand = new Random();

        outputTextView = (TextView) findViewById(R.id.output);
        newGame = (Button) findViewById(R.id.newgame);
        square1 = (Button) findViewById(R.id.square1);
        squareList.add(square1);
        square2 = (Button) findViewById(R.id.square2);
        squareList.add(square2);
        square3 = (Button) findViewById(R.id.square3);
        squareList.add(square3);
        square4 = (Button) findViewById(R.id.square4);
        squareList.add(square4);
        square5 = (Button) findViewById(R.id.square5);
        squareList.add(square5);
        square6 = (Button) findViewById(R.id.square6);
        squareList.add(square6);
        square7 = (Button) findViewById(R.id.square7);
        squareList.add(square7);
        square8 = (Button) findViewById(R.id.square8);
        squareList.add(square8);
        square9 = (Button) findViewById(R.id.square9);
        squareList.add(square9);
 
        newGame.setOnClickListener(this);
        square1.setOnClickListener(this);
        square2.setOnClickListener(this);
        square3.setOnClickListener(this);
        square4.setOnClickListener(this);
        square5.setOnClickListener(this);
        square6.setOnClickListener(this);
        square7.setOnClickListener(this);
        square8.setOnClickListener(this);
        square9.setOnClickListener(this);

        outputTextView.setText("Player X's Turn");
    }

    private void makeHumanMove(int square) {
        if (win == 0) {
            if (mBoard[square] != HUMAN_PLAYER && mBoard[square] != COMPUTER_PLAYER) {
                if (turn == HUMAN_PLAYER) {
                    squareList.get(square).setText(Character.toString(HUMAN_PLAYER));
                    turn = COMPUTER_PLAYER;
                    outputTextView.setText("Player O's Turn");
                    mBoard[square] = HUMAN_PLAYER;
                    this.checkForGameOver();
                    this.displayText();
                    if (win == 0) {
                        this.getComputerMove();
                        turn = HUMAN_PLAYER;
                        outputTextView.setText("Player X's Turn");
                        this.checkForGameOver();
                        this.displayText();
                    }
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.newgame:
                this.startNewGame();
                break;
            case R.id.square1:
                // make human move
                this.makeHumanMove(0);
                break;
            case R.id.square2:
                this.makeHumanMove(1);
            case R.id.square3:
                this.makeHumanMove(2);
            case R.id.square4:
                this.makeHumanMove(3);
                break;
            case R.id.square5:
                this.makeHumanMove(4);
                break;
            case R.id.square6:
                this.makeHumanMove(5);
                break;
            case R.id.square7:
                this.makeHumanMove(6);
                break;
            case R.id.square8:
                this.makeHumanMove(7);
                break;
            case R.id.square9:
                this.makeHumanMove(8);
                break;

        }
    }

    private void getComputerMove() {
        int move = 0;

        // First see if there's a move O can make to win
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (mBoard[i] != HUMAN_PLAYER && mBoard[i] != COMPUTER_PLAYER) {
                char curr = mBoard[i];
                mBoard[i] = COMPUTER_PLAYER;
                if (win == 3) {
                    squareList.get(i).setText(Character.toString(COMPUTER_PLAYER));
                    return;
                } else {
                    mBoard[i] = curr;
                    win = 0;
                }
                }
        }

        // See if there's a move O can make to block x from winning
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (mBoard[i] != HUMAN_PLAYER && mBoard[i] != COMPUTER_PLAYER) {
                char curr = mBoard[i];
                mBoard[i] = HUMAN_PLAYER;
                if (win == 2) {
                    mBoard[i] = COMPUTER_PLAYER;
                    squareList.get(i).setText(Character.toString(COMPUTER_PLAYER));
                    return;
                } else {
                    mBoard[i] = curr;
                    win = 0;
                }
            }
        }

        // Generate random move
        int count = 0;
        do {
            move = mRand.nextInt(BOARD_SIZE);
            count++;
        } while (mBoard[move] == HUMAN_PLAYER || mBoard[move] == COMPUTER_PLAYER && count < 9);

        mBoard[move] = COMPUTER_PLAYER;
        squareList.get(move).setText(Character.toString(COMPUTER_PLAYER));
    }




    /** Start new game by clearing the board and set the starting values */
    private void startNewGame() {
        this.setStartingValues();
        this.clearGrid();
    }

    /** set starting values, gameOver = false, turn = 1, message = Player X’s turn, game status = “ “ */
    private void setStartingValues() {
        gameOver = false;
        turn = HUMAN_PLAYER;
        win = 0;
        outputTextView.setText("Player X's Turn");
        mBoard[0] = '1';
        mBoard[1] = '2';
        mBoard[2] = '3';
        mBoard[3] = '4';
        mBoard[4] = '5';
        mBoard[5] = '6';
        mBoard[6] = '7';
        mBoard[7] = '8';
        mBoard[8] = '9';

    }

    /** Clear the board of all X's and O's. */
    public void clearGrid() {
        for (int i = 0; i < squareList.size(); i++) {
            squareList.get(i).setText("");
        }
    }

    /**
     * Check for a winner and set gameOver to true if there is a winner or tie
     * 0 if no winner or tie yet
     * 1 if it's a tie
     * 2 if X won
     * 3 if O won
     */
    private void checkForGameOver() {

        // check horizontal wins
        for (int i = 0; i <= 6; i += 3) {
            if (mBoard[i] == HUMAN_PLAYER &&
                    mBoard[i+1] == HUMAN_PLAYER &&
                    mBoard[i+2] == HUMAN_PLAYER) {
                gameOver = true;
                outputTextView.setText("X Wins!");
                win = 2;
                return;
            }

            if (mBoard[i] == COMPUTER_PLAYER &&
                    mBoard[i+1] == COMPUTER_PLAYER &&
                    mBoard[i+2] == COMPUTER_PLAYER) {
                gameOver = true;
                outputTextView.setText("O Wins!");
                win = 3;
                return;
            }
        }

        // Check vertical wins
        for (int i = 0; i <= 2; i++) {
            if (mBoard[i] == HUMAN_PLAYER &&
                    mBoard[i+3] == HUMAN_PLAYER &&
                    mBoard[i+6] == HUMAN_PLAYER) {
                gameOver = true;
                win = 2;
                return;
            }
            if (mBoard[i] == COMPUTER_PLAYER &&
                    mBoard[i+3] == COMPUTER_PLAYER &&
                    mBoard[i+6] == COMPUTER_PLAYER) {
                gameOver = true;
                win = 3;
                return;
            }
        }

        // Check for diagonal wins
        if ((mBoard[0] == HUMAN_PLAYER &&
                mBoard[4] == HUMAN_PLAYER &&
                mBoard[8] == HUMAN_PLAYER) ||
                (mBoard[2] == HUMAN_PLAYER &&
                mBoard[4] == HUMAN_PLAYER &&
                mBoard[6] == HUMAN_PLAYER)) {
            gameOver = true;
            win = 2;
            return;
        }

        if ((mBoard[0] == COMPUTER_PLAYER &&
                mBoard[4] == COMPUTER_PLAYER &&
                mBoard[8] == COMPUTER_PLAYER) ||
                (mBoard[2] == COMPUTER_PLAYER &&
                        mBoard[4] == COMPUTER_PLAYER &&
                        mBoard[6] == COMPUTER_PLAYER)) {
            gameOver = true;
            win = 3;
            return;
        }

        // Check for tie
        for (int i = 0; i < BOARD_SIZE; i++) {
            // If we find a number, then no one has won yet
            if (mBoard[i] != HUMAN_PLAYER && mBoard[i] != COMPUTER_PLAYER) {
                win = 0;
                return;
            }
        }
        win = 3;
        return;
    }

}

package com.somavartok.connectthree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //    0 = yellow, 1 = red
    int activePlayer = 0;

    // 2 = unplayed
    int[] counterList = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(view.getTag().toString());
        if (counterList[tappedCounter] == 2) {
            counterList[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).setDuration(300);
        }
        if (checkWin() != null){
            String winnerText = checkWin() + " won!";
            LinearLayout winnerLayout = findViewById(R.id.playAgainLayout);
            TextView winnerMessage = findViewById(R.id.winnerMassage);
            winnerMessage.setText(winnerText);
            winnerLayout.setVisibility(View.VISIBLE);
        }

    }

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    public String checkWin() {
        for (int[] winningPosition : winningPositions) {
            if ((counterList[winningPosition[0]] == counterList[winningPosition[1]])
                    && (counterList[winningPosition[1]] == counterList[winningPosition[2]])
                    && (counterList[winningPosition[0]] != 2)) {
                if (counterList[winningPosition[0]] == 0) {
                    return "Yellow";

                } else {
                    return "Red";
                }
            }
        }
        return null;
    }

    public void playAgain(View view){

        LinearLayout winningLayout = findViewById(R.id.playAgainLayout);
        winningLayout.setVisibility(View.INVISIBLE);

        activePlayer = 0;
        for (int i = 0; i < counterList.length; i++) {
            counterList[i] = 2;
        }
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for(int i = 0; i < gridLayout.getChildCount(); i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

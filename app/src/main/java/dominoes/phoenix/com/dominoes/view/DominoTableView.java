package dominoes.phoenix.com.dominoes.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Random;

import dominoes.phoenix.com.dominoes.Board;
import dominoes.phoenix.com.dominoes.Domino;
import dominoes.phoenix.com.dominoes.GameConfiguration;
import dominoes.phoenix.com.dominoes.Player;

/**
 * Created by Pushpan on 01/04/15.
 */
public class DominoTableView extends View {
    private Board mBoard;
    private ArrayList<Player> mPlayers = new ArrayList<Player>();

    public DominoTableView(Context context) {
        super(context);
        initialize();
    }

    public DominoTableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public DominoTableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    public void initialize() {
        GameConfiguration.initialize(getContext());
        mBoard = new Board();
        for (int i = 0; i < 2; i++) {
            mPlayers.add(new Player());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mBoard.onDraw(canvas);
        for (Player player : mPlayers) {
            player.onDraw(canvas);
        }
    }

    public void shuffleAndDistributeDominoes() {
       mBoard.shuffleAndDistributeDominoes(mPlayers);
        invalidate();
    }
}

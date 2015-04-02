package dominoes.phoenix.com.dominoes;

import android.graphics.Canvas;

import java.util.ArrayList;

/**
 * Created by Pushpan on 01/04/15.
 */
public class Player extends Actor{
    private ArrayList<Domino> mDominoes = new ArrayList<Domino>();

    public void addDomino(Domino domino) {
        mDominoes.add(domino);
    }

    public ArrayList<Domino> getDominoes() {
        return mDominoes;
    }

    public void onDraw(Canvas canvas) {
        for (Domino domino : mDominoes) {
            domino.onDraw(canvas);
        }
    }
}

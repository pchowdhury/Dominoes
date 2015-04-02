package dominoes.phoenix.com.dominoes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Pushpan on 01/04/15.
 */
public class Board extends Actor{
    private ArrayList<Domino> mStock;
    private Paint mBoardPaint;

    public Board() {
        mStock = new ArrayList<Domino>();
        mBoardPaint = new Paint();
        mBoardPaint.setColor(Color.GREEN);
        mBoardPaint.setStyle(Paint.Style.FILL);
        populateStock();
    }

    public void populateStock() {
        Domino domino;
        int d1 = 0, d2 = 0;
        mStock.clear();
        int x, y;

        for (int i = 0; i < 28; i++) {
            if (d1 == 7)
                break;
            if (d2 == 7) {
                d1++;
                d2 = d1;
            }
            domino = new Domino(Dice.values()[d1], Dice.values()[d2++]);
            mStock.add(domino);
        }
    }

    public void onDraw(Canvas canvas) {
        RectF rectF = new RectF(mLeft, mTop, getWidth(), getHeight());
        canvas.drawRect(rectF, mBoardPaint);
    }

    @Override
    public int getWidth() {
        return GameConfiguration.getInstance().getResources().getDisplayMetrics().widthPixels;
    }

    @Override
    public int getHeight() {
        return GameConfiguration.getInstance().getResources().getDisplayMetrics().heightPixels;
    }

    public void shuffleAndDistributeDominoes(ArrayList<Player> players) {
        int count = 0, index;
        boolean twins = false;
        Random random = new Random();
        Domino domino;
        for (Player player : players) {
            count = 0;
            try {
                do {
                    index = (random.nextInt() % mStock.size());
                    if (index < 0) {
                        index = -index;
                    }
                    domino = mStock.get(index);

                    if (domino.areTwins()) {
                        twins = true;
                    }
                    //make sure at least one player has a twin
                    if (count == 6 && !twins) {
                        if (!domino.areTwins())
                            continue;
                    } else {
                        player.addDomino(domino);
                        mStock.remove(index);
                        count++;
                    }

                } while (count != GameConfiguration.getInstance().getMaxShare());
            } catch (Exception e) {
                if (GameConfiguration.DEBUG)
                    System.out.println("Error in board shuffleAndDistributeDominoes");
            }
        }
    }
}

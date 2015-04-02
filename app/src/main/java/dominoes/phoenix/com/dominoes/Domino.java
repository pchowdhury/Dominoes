package dominoes.phoenix.com.dominoes;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by Pushpan on 01/04/15.
 */
public class Domino extends Actor{
    private Dice mDice1;
    private Dice mDice2;
    private Gesture mGesture;

    private static float mCornerRadius = -1;
    private static int mSubDominoWidth = -1;
    private static int mSubDominoHeight = -1;
    private static int mPointRadius = -1;

    private Paint mPaintDomino = new Paint();
    private Bitmap mBitmap;

    public Domino() {
        init();
    }

    public Domino(Dice dice1, Dice dice2) {
        init();
        mDice1 = dice1;
        mDice2 = dice2;
    }

    public void init() {
        mDice1 = mDice2 = Dice.DICE_0;
        mGesture = Gesture.VERTICAL_STAND;
        mLeft = mTop = 0;
        mPaintDomino.setStrokeWidth(GameConfiguration.getInstance().getResources().getDimension(R.dimen.domino_stroke_width));
        mPaintDomino.setAntiAlias(true);
        if (mSubDominoHeight == -1) {
            mSubDominoHeight = (int) GameConfiguration.getInstance().getResources().getDimension(R.dimen.sub_domino_height);
        }
        if (mSubDominoWidth == -1) {
            mSubDominoWidth = mSubDominoHeight + (int) (mSubDominoHeight * 0.05);
        }

        if (mCornerRadius == -1) {
            mCornerRadius = (int) (mSubDominoHeight * 0.20);
        }


        if (mPointRadius == -1) {
            mPointRadius = (int) (mSubDominoHeight * 0.12);
        }

        initDomino();
    }

    private void initDomino() {
        mBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mBitmap);
        RectF rectF = new RectF(mLeft, mTop, mLeft + getWidth(), mTop + getHeight());
        mPaintDomino.setStyle(Paint.Style.FILL);
        mPaintDomino.setColor(Color.WHITE);
        float halfStrokeWidth = mPaintDomino.getStrokeWidth() / 2;
        canvas.drawRoundRect(rectF, mCornerRadius, mCornerRadius, mPaintDomino);
        rectF = new RectF(mLeft + halfStrokeWidth, mTop + halfStrokeWidth, mLeft + getWidth() - halfStrokeWidth, mTop + getHeight() - halfStrokeWidth);
        mPaintDomino.setStyle(Paint.Style.STROKE);
        mPaintDomino.setColor(Color.BLACK);
        canvas.drawRoundRect(rectF, mCornerRadius, mCornerRadius, mPaintDomino);
        int x1 = mLeft, y1 = mTop, x2 = 0, y2 = 0;
        int padding = 0;
        switch (mGesture) {
            case VERTICAL_STAND:
                padding = (int) ((getSingleWidth() * (0.02f)) + mPaintDomino.getStrokeWidth());
                x1 += padding;
                y1 = getSingleHeight();
                x2 = (mLeft + getWidth()) - padding;
                y2 = getSingleHeight();
                break;
            case HORIZENTAL_STAND:
                padding = (int) ((getSingleHeight() * (0.02f)) + mPaintDomino.getStrokeWidth());
                y1 += padding;
                x1 = getSingleWidth();
                x2 = getSingleWidth();
                y2 = mTop + getHeight() - padding;
                break;

        }
        mDice1 = Dice.DICE_5;
        mDice2 = Dice.DICE_6;
        canvas.drawLine(x1, y1, x2, y2, mPaintDomino);
        drawPoints(canvas, mDice1, true);
        drawPoints(canvas, mDice2, false);
        mGesture=Gesture.HORIZENTAL_STAND;
    }

    private void drawPoints(Canvas canvas, Dice dice, boolean isOnTop) {
        mPaintDomino.setColor(Color.BLACK);
        mPaintDomino.setStyle(Paint.Style.FILL);
        int top = isOnTop ? mTop : (getSingleHeight() + mTop);
        switch (dice) {
            case DICE_0:
                break;
            case DICE_1:
                canvas.drawCircle(mLeft + (getWidth() / 2), top + (getSingleHeight() / 2), mPointRadius, mPaintDomino);
                break;
            case DICE_2:
                canvas.drawCircle(mLeft + (getWidth() / 4), top + (getSingleHeight() / 4), mPointRadius, mPaintDomino);
                canvas.drawCircle(mLeft + getWidth() - (getWidth() / 4), top + getSingleHeight() - (getSingleHeight() / 4), mPointRadius, mPaintDomino);
                break;
            case DICE_3:
                canvas.drawCircle(mLeft + (getWidth() / 4), top + (getSingleHeight() / 4), mPointRadius, mPaintDomino);
                canvas.drawCircle(mLeft + (getWidth() / 2), top + (getSingleHeight() / 2), mPointRadius, mPaintDomino);
                canvas.drawCircle(mLeft + getWidth() - (getWidth() / 4), top + getSingleHeight() - (getSingleHeight() / 4), mPointRadius, mPaintDomino);
                break;
            case DICE_4:
                canvas.drawCircle(mLeft + (getWidth() / 4), top + (getSingleHeight() / 4), mPointRadius, mPaintDomino);
                canvas.drawCircle(mLeft + getWidth() - (getWidth() / 4), top + (getSingleHeight() / 4), mPointRadius, mPaintDomino);
                canvas.drawCircle(mLeft + getWidth() - (getWidth() / 4), top + getSingleHeight() - (getSingleHeight() / 4), mPointRadius, mPaintDomino);
                canvas.drawCircle(mLeft + (getWidth() / 4), top + getSingleHeight() - (getSingleHeight() / 4), mPointRadius, mPaintDomino);
                break;
            case DICE_5:
                canvas.drawCircle(mLeft + (getWidth() / 4), top + (getSingleHeight() / 4), mPointRadius, mPaintDomino);
                canvas.drawCircle(mLeft + getWidth() - (getWidth() / 4), top + (getSingleHeight() / 4), mPointRadius, mPaintDomino);
                canvas.drawCircle(mLeft + (getWidth() / 2), top + (getSingleHeight() / 2), mPointRadius, mPaintDomino);
                canvas.drawCircle(mLeft + getWidth() - (getWidth() / 4), top + getSingleHeight() - (getSingleHeight() / 4), mPointRadius, mPaintDomino);
                canvas.drawCircle(mLeft + (getWidth() / 4), top + getSingleHeight() - (getSingleHeight() / 4), mPointRadius, mPaintDomino);
                break;
            case DICE_6:
                canvas.drawCircle(mLeft + (getWidth() / 4), top + (getSingleHeight() / 4), mPointRadius, mPaintDomino);
                canvas.drawCircle(mLeft + getWidth() - (getWidth() / 4), top + (getSingleHeight() / 4), mPointRadius, mPaintDomino);
                canvas.drawCircle(mLeft + (getWidth() / 4), top + (getSingleHeight() / 2), mPointRadius, mPaintDomino);
                canvas.drawCircle(mLeft + getWidth() - (getWidth() / 4), top + (getSingleHeight() / 2), mPointRadius, mPaintDomino);
                canvas.drawCircle(mLeft + getWidth() - (getWidth() / 4), top + getSingleHeight() - (getSingleHeight() / 4), mPointRadius, mPaintDomino);
                canvas.drawCircle(mLeft + (getWidth() / 4), top + getSingleHeight() - (getSingleHeight() / 4), mPointRadius, mPaintDomino);
                break;
        }
    }

    @Override
    public int getWidth() {
        return mGesture == Gesture.VERTICAL_STAND ? (mSubDominoWidth) : (mSubDominoHeight * 2);
    }

    @Override
    public int getHeight() {
        return mGesture == Gesture.VERTICAL_STAND ? (mSubDominoHeight * 2) : (mSubDominoWidth);
    }

    public int getSingleWidth() {
        return mSubDominoWidth;
    }

    public int getSingleHeight() {
        return mSubDominoHeight;
    }

    public void onDraw(Canvas canvas) {
        switch (mGesture) {
            case VERTICAL_STAND:
                canvas.drawBitmap(mBitmap, mLeft, mTop, mPaintDomino);
                break;
            case HORIZENTAL_STAND:
                Matrix matrix = new Matrix();
                matrix.reset();
                matrix.postTranslate(-mBitmap.getWidth() / 2, -mBitmap.getHeight() / 2); // Centers image
                matrix.postRotate(90);
                matrix.postTranslate(mLeft + getWidth() / 2, mTop + getHeight() / 2);
                canvas.drawBitmap(mBitmap, matrix, mPaintDomino);
                break;
        }
    }

    public boolean areTwins() {
        return mDice1.equals(mDice2);
    }
}

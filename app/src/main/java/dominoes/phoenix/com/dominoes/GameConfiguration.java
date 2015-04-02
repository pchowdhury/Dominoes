package dominoes.phoenix.com.dominoes;

import android.content.Context;
import android.content.res.Resources;

/**
 * Created by Pushpan on 01/04/15.
 */
public class GameConfiguration {
    public static final boolean DEBUG = true;

    public static GameConfiguration mInstance = null;
    private Context mContext;

    public static void initialize(Context context) {
        mInstance = new GameConfiguration(context);
    }

    public static void destroy() {
        mInstance = null;
    }

    public static GameConfiguration getInstance() {
        return mInstance;
    }

    public GameConfiguration(Context context) {
        this.mContext = context;
    }

    public float getDisplayDensity() {
        return mContext.getResources().getDisplayMetrics().density;
    }

    public Resources getResources() {
        return mContext.getResources();
    }

    public int getMaxShare() {
        return  mContext.getResources().getInteger(R.integer.max_share);
    }
}

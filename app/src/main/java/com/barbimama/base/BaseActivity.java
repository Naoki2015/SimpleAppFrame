package com.barbimama.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.barbimama.R;
import com.barbimama.manager.ActivityManager;
import com.library.utils.SmartBarUtils;
import com.library.view.dialog.LoadingDialog;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by yiwei on 16/4/5.
 */
public class BaseActivity extends AppCompatActivity {

    protected static String TAG = "";
    /**
     * context
     */
    protected Context context = null;
    /**
     * Screen information
     */
    protected int screenWidth = 0;
    protected int screenHeight = 0;
    protected float screenDensity = 0.0f;

    //protected Toolbar toolbar;
    protected LoadingDialog loadingDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TAG = getClass().getSimpleName();
        context = this;
        ActivityManager.getInstance().addActivity(this);

        //状态栏透明
        SmartBarUtils.hide(getWindow().getDecorView());
        setTranslucentStatus(isApplyStatusBarTranslucency());
        setSystemBarTintDrawable(isApplyKitKatTranslucency());

        measureScreenDensity();
    }

    protected boolean isApplyStatusBarTranslucency() {
        return true;
    }

    protected boolean isApplyKitKatTranslucency() {
        return true;
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        //toolbar = (Toolbar)findViewById(R.id.common_toolbar);
//        if (null != toolbar) {
//            setSupportActionBar(toolbar);
//            getSupportActionBar().setHomeButtonEnabled(true);
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }
    }

    private void measureScreenDensity() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        screenDensity = displayMetrics.density;
        screenHeight = displayMetrics.heightPixels;
        screenWidth = displayMetrics.widthPixels;
    }

    /**
     * set status bar translucency
     *
     * @param on
     */
    protected void setTranslucentStatus(boolean on) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);
        }
    }

    /**
     * use SytemBarTintManager
     *
     * @param isApplyKitKatTranslucency
     */
    protected void setSystemBarTintDrawable(boolean isApplyKitKatTranslucency) {
        if (isApplyKitKatTranslucency && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager mTintManager = new SystemBarTintManager(this);
            Drawable tintDrawable = getResources().getDrawable(R.drawable.sr_primary);
            if (tintDrawable != null) {
                mTintManager.setStatusBarTintEnabled(true);
                mTintManager.setTintDrawable(tintDrawable);
            } else {
                mTintManager.setStatusBarTintEnabled(false);
                mTintManager.setTintDrawable(null);
            }
        }

    }

    @Override
    protected void onDestroy() {
        ActivityManager.getInstance().removeActivity(this);
        super.onDestroy();
    }

    protected void showToast(String msg, int lenght){
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(context, msg, lenght).show();
        }
    }

    protected void showLoading(String message){
        if (null == loadingDialog) {
            loadingDialog = new LoadingDialog(context);
        }
        loadingDialog.setMessage(message);
        loadingDialog.show();
    }

    protected void hideLoading(){
        if (null != loadingDialog) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }
}

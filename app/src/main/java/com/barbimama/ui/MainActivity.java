package com.barbimama.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.barbimama.R;
import com.barbimama.base.BaseActivity;
import com.barbimama.base.BbmmApplication;
import com.barbimama.mvp.presenter.MainPresenter;
import com.barbimama.mvp.view.MainView;
import com.library.view.vary.VaryViewHelperController;

public class MainActivity extends BaseActivity implements MainView {

    private static long DOUBLE_CLICK_TIME = 0L;
    private TextView contentTv;
    private MainPresenter presenter;
    private VaryViewHelperController viewHelperController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        contentTv = (TextView)findViewById(R.id.contentTv);
        presenter = new MainPresenter(this);

        viewHelperController = new VaryViewHelperController(contentTv);
        presenter.loadLocationCache();
        presenter.loadLocation();
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.loadLocation();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.cancleRequest();
    }

    @Override
    public void updateLoadLocation(String result) {
        if(!TextUtils.isEmpty(result)){
            contentTv.setVisibility(View.VISIBLE);
            contentTv.setText(result);
            //恢复数据界面
            viewHelperController.restore();
        }else{
            //数据为空，显示空界面
            viewHelperController.showEmpty(clickListener);
        }
    }

    @Override
    public void showLoading(String msg) {
        //默认的loading
        //showBaseLoading(msg);
        viewHelperController.showLoading();
    }

    @Override
    public void hideLoading() {
        //默认的loading
        //hideBaseLoading();
    }

    @Override
    public void showError(String errorMsg) {
        //显示默认错误界面
        viewHelperController.showError(clickListener);
        //显示自定义错误界面
        //viewHelperController.showCustomView(customView);
        //viewHelperController.showCustomView(customView, targetView);
        showToast(errorMsg, Toast.LENGTH_SHORT);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - DOUBLE_CLICK_TIME) > 2000) {
                showToast(getString(R.string.double_click_exit), Toast.LENGTH_SHORT);
                DOUBLE_CLICK_TIME = System.currentTimeMillis();
            } else {
                ((BbmmApplication)getApplication()).exitApp();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}

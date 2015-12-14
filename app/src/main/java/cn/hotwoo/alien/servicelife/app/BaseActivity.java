package cn.hotwoo.alien.servicelife.app;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jude.beam.bijection.BeamAppCompatActivity;
import com.jude.beam.bijection.RequiresPresenter;
import com.umeng.analytics.MobclickAgent;

import cn.hotwoo.alien.servicelife.R;

/**
 * Created by alien on 2015/7/27.
 */
@RequiresPresenter(BasePresenter.class)
public class BaseActivity<T extends BasePresenter> extends BeamAppCompatActivity<T> {

    private Toolbar toolbar;
    private MaterialDialog materialDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setToolbar(boolean homeAble) {
        toolbar = $(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(homeAble);
        }
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        setToolbar(true);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        setToolbar(true);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setToolbar(true);
    }

    public void showProgress() {
        materialDialog = new MaterialDialog.Builder(this)
                .content("请稍候")
                .progress(true, 100)
                .show();
    }

    public void showProgress(String title) {
        materialDialog = new MaterialDialog.Builder(this)
                .title(title)
                .content("请稍候")
                .progress(true, 100)
                .show();
    }

    public void dismissProgress() {
        materialDialog.dismiss();
    }

    public <T extends View> T $(int layout) {
        return (T) findViewById(layout);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}

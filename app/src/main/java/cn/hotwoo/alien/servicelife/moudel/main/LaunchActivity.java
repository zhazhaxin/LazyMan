package cn.hotwoo.alien.servicelife.moudel.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.hotwoo.alien.servicelife.R;
import cn.hotwoo.alien.servicelife.app.BaseActivity;

/**
 * Created by Administrator on 2015/10/20.
 */
public class LaunchActivity extends BaseActivity {

    @Bind(R.id.bg)
    SimpleDraweeView bg;
    public static final String IMG_URL = "http://alien.hotwoo.cn/img/launch_bg.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_launch);
        ButterKnife.bind(this);
        bg.setImageURI(Uri.parse(IMG_URL));
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                finish();
            }
        }, 2000);

    }
}

package cn.hotwoo.alien.servicelife.moudel.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jude.beam.bijection.RequiresPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.hotwoo.alien.servicelife.R;
import cn.hotwoo.alien.servicelife.app.BaseActivity;

/**
 * Created by alien on 2015/8/17.
 */
@RequiresPresenter(SetPresenter.class)
public class SetActivity extends BaseActivity<SetPresenter> {


    @Bind(R.id.outLogin)
    TextView outLogin;
    @Bind(R.id.clearData)
    TextView clearData;
    @Bind(R.id.feedback)
    TextView feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);
        ButterKnife.bind(this);

        outLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().outLogin();
            }
        });

        clearData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().clearData();
            }
        });
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}

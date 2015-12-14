package cn.hotwoo.alien.servicelife.moudel.user;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.beam.bijection.RequiresPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.hotwoo.alien.servicelife.R;
import cn.hotwoo.alien.servicelife.app.BaseActivity;
import cn.hotwoo.alien.servicelife.model.bean.User;
import cn.hotwoo.alien.servicelife.util.RecentTimeFormat;
import cn.hotwoo.alien.servicelife.util.TimeFormat;

/**
 * Created by alien on 2015/8/14.
 */
@RequiresPresenter(UserInfoDetailPresenter.class)
public class UserInfoDetailActivity extends BaseActivity<UserInfoDetailPresenter> {

    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.gender)
    TextView gender;
    @Bind(R.id.sign)
    TextView sign;
    @Bind(R.id.school)
    TextView school;
    @Bind(R.id.face)
    SimpleDraweeView face;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsingToolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.birth)
    TextView birth;
    @Bind(R.id.age)
    TextView age;
    @Bind(R.id.modifyData)
    FloatingActionButton modifyData;
    @Bind(R.id.major)
    TextView major;
    @Bind(R.id.phone)
    TextView phone;
    @Bind(R.id.qq)
    TextView qq;
    @Bind(R.id.intro)
    TextView intro;

    public static final int MODIFY_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbar.setTitle("个人信息");
        modifyData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(new Intent(UserInfoDetailActivity.this, ModifyInfoActivity.class)), MODIFY_REQUEST_CODE);
            }
        });
    }

    public void setData(User data) {
        if (data.getFace() != null)
            face.setImageURI(Uri.parse(data.getFace()));
        name.setText(data.getName());
        sign.setText(data.getSign());
        school.setText(data.getSchool());
        if (data.getGender() == 0)
            gender.setText("女");
        else gender.setText("男");
        age.setText(data.getAge() + "");
        birth.setText(new TimeFormat().toString(new RecentTimeFormat(), data.getBirth() * 1000));
        major.setText(data.getMajor());
        phone.setText(data.getPhone()+"");
        qq.setText(data.getQq()+"");
        intro.setText(data.getIntro());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}

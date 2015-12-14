package cn.hotwoo.alien.servicelife.moudel.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.DynamicPagerAdapter;
import com.umeng.fb.FeedbackAgent;
import com.umeng.update.UmengUpdateAgent;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.hotwoo.alien.servicelife.R;
import cn.hotwoo.alien.servicelife.app.BaseActivity;
import cn.hotwoo.alien.servicelife.model.bean.Banner;
import cn.hotwoo.alien.servicelife.model.bean.News;
import cn.hotwoo.alien.servicelife.moudel.news.NewsActivity;
import cn.hotwoo.alien.servicelife.widget.HotMessagesView;
import cn.hotwoo.alien.servicelife.widget.LinearWrapContentRecyclerView;

@RequiresPresenter(MainPresenter.class)
public class MainActivity extends BaseActivity<MainPresenter> {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.banner)
    RollPagerView banner;
    @Bind(R.id.moreMessage)
    TextView moreMessage;
    @Bind(R.id.hotMessage)
    HotMessagesView hotMessage;
    @Bind(R.id.dawerLyout)
    DrawerLayout dawerLyout;
    @Bind(R.id.relaxRecycler)
    LinearWrapContentRecyclerView relaxRecycler;
    @Bind(R.id.studyRecycler)
    LinearWrapContentRecyclerView studyRecycler;
    @Bind(R.id.moreRelax)
    Button moreRelax;
    @Bind(R.id.moreStudy)
    Button moreStudy;
    private FeedbackAgent agent;
    private RecommendAdapter relaxAdapter;
    private RecommendAdapter studyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        UmengUpdateAgent.update(this);
        agent = new FeedbackAgent(this);
        agent.sync();

        setTitle("懒人");
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dawerLyout, getToolbar(), 0, 0);
        toggle.syncState();
        dawerLyout.setDrawerListener(toggle);
        moreMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NewsActivity.class));
            }
        });
        relaxAdapter = new RecommendAdapter(this);
        studyAdapter = new RecommendAdapter(this);

        relaxRecycler.setAdapter(relaxAdapter);
        studyRecycler.setAdapter(studyAdapter);

        moreRelax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RelaxActivity.class));
            }
        });

        moreStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StudyActivity.class));
            }
        });
    }

    public void setBanner(final Banner[] data) {
        banner.setAdapter(new DynamicPagerAdapter() {
            @Override
            public View getView(ViewGroup container, int position) {
                SimpleDraweeView img = new SimpleDraweeView(MainActivity.this);
                img.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                img.setImageURI(Uri.parse(data[position].getImg()));
                return img;
            }

            @Override
            public int getCount() {
                return data.length;
            }
        });
    }

    public void setHotMessage(News[] data) {
        hotMessage.setData(data);
    }

    public void setRelaxData(News[] data) {
        relaxAdapter.addAll(data);
    }

    public void setStudyData(News[] data) {
        studyAdapter.addAll(data);
    }

}

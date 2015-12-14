package cn.hotwoo.alien.servicelife.moudel.news;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.jude.beam.bijection.RequiresPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.hotwoo.alien.servicelife.R;
import cn.hotwoo.alien.servicelife.app.BaseActivity;

/**
 * Created by Administrator on 2015/10/18.
 */
@RequiresPresenter(NewsPresenter.class)
public class NewsActivity extends BaseActivity<NewsPresenter> {

    @Bind(R.id.tab)
    TabLayout tab;
    @Bind(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity);
        ButterKnife.bind(this);

        viewPager.setAdapter(new Adapter(getSupportFragmentManager()));
        tab.setupWithViewPager(viewPager);
    }

    class Adapter extends FragmentPagerAdapter {

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return getPresenter().setItem(position);
        }

        @Override
        public int getCount() {
            return getPresenter().setCount();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getPresenter().setTitile(position);
        }
    }
}

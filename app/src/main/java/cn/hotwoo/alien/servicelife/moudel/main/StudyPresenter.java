package cn.hotwoo.alien.servicelife.moudel.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import cn.hotwoo.alien.servicelife.app.BasePresenter;
import cn.hotwoo.alien.servicelife.moudel.news.NewsListFragment;

/**
 * Created by Administrator on 2015/10/18.
 */
public class StudyPresenter extends BasePresenter<StudyActivity> {

    private String[] titiles;

    @Override
    protected void onCreate(StudyActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        titiles = new String[]{
                "博客", "问题", "常识"
        };
    }

    public String setTitile(int position) {
        return titiles[position];
    }

    public int setCount() {
        return 3;
    }

    public Fragment setItem(int position) {
        Fragment f = new ListFragment();
        Bundle b = new Bundle();
        b.putString("type", titiles[position]);
        f.setArguments(b);
        return f;
    }
}

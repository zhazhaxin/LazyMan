package cn.hotwoo.alien.servicelife.moudel.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import cn.hotwoo.alien.servicelife.app.BasePresenter;

/**
 * Created by Administrator on 2015/10/18.
 */
public class NewsPresenter extends BasePresenter<NewsActivity> {

    private String[] titiles;

    @Override
    protected void onCreate(NewsActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        titiles=new String[]{
                "头条","社会","娱乐","科技"
        };
    }

    public String setTitile(int position) {
        return titiles[position];
    }

    public int setCount(){
        return 4;
    }

    public Fragment setItem(int position){
        Fragment f = new NewsListFragment();
        Bundle b = new Bundle();
        b.putString("type", titiles[position]);
        f.setArguments(b);
        return f;
    }
}

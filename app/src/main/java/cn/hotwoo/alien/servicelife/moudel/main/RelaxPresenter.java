package cn.hotwoo.alien.servicelife.moudel.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import cn.hotwoo.alien.servicelife.app.BasePresenter;

/**
 * Created by Administrator on 2015/10/18.
 */
public class RelaxPresenter extends BasePresenter<RelaxActivity> {

    private String[] titiles;

    @Override
    protected void onCreate(RelaxActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        titiles=new String[]{
                "周边","旅游","游戏","段子"
        };
    }

    public String setTitile(int position) {
        return titiles[position];
    }

    public int setCount(){
        return 4;
    }

    public Fragment setItem(int position){
        Fragment f = new ListFragment();
        Bundle b = new Bundle();
        b.putString("type", titiles[position]);
        f.setArguments(b);
        return f;
    }
}

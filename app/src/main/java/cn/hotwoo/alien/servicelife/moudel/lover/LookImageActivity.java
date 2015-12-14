package cn.hotwoo.alien.servicelife.moudel.lover;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.hotwoo.alien.servicelife.R;
import cn.hotwoo.alien.servicelife.app.BaseActivity;

/**
 * Created by alien on 2015/9/3.
 */
@RequiresPresenter(LookImagePresenter.class)
public class LookImageActivity extends BaseActivity<LookImagePresenter> {

    @Bind(R.id.rollPagerView)
    RollPagerView rollPagerView;

    private String[] imgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lover_activity_lookimg);
        ButterKnife.bind(this);
        imgs=getPresenter().getImgs();
        rollPagerView.setAdapter(new ImageAdapter());
    }

    class ImageAdapter extends StaticPagerAdapter {

        @Override
        public View getView(ViewGroup container, int position) {
            SimpleDraweeView imageView = new SimpleDraweeView(LookImageActivity.this);
            AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            imageView.setLayoutParams(layoutParams);
            imageView.setAspectRatio(1.0f);
            imageView.setImageURI(Uri.parse(imgs[position]));
            return imageView;
        }

        @Override
        public int getCount() {
            return imgs.length;
        }
    }

}

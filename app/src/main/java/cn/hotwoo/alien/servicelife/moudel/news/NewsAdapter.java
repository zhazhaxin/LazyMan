package cn.hotwoo.alien.servicelife.moudel.news;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.hotwoo.alien.servicelife.R;
import cn.hotwoo.alien.servicelife.model.bean.News;
import cn.hotwoo.alien.servicelife.util.RecentTimeFormat;
import cn.hotwoo.alien.servicelife.util.TimeFormat;
import cn.hotwoo.alien.servicelife.util.Utils;

/**
 * Created by alien on 2015/7/28.
 */
public class NewsAdapter extends RecyclerArrayAdapter<News> {
    public NewsAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup viewGroup, int i) {
        return new NewsViewHolder(viewGroup);
    }


    class NewsViewHolder extends BaseViewHolder<News> {

        @Bind(R.id.img)
        SimpleDraweeView img;
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.intro)
        TextView intro;
        @Bind(R.id.time)
        TextView time;
        @Bind(R.id.ripple)
        MaterialRippleLayout ripple;

        public NewsViewHolder(ViewGroup parent) {
            super(parent, R.layout.news_item);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(final News data) {
            super.setData(data);
            img.setImageURI(Uri.parse(data.getImgUrl()));
            title.setText(data.getTitle());
            intro.setText(data.getContent());
            time.setText(new TimeFormat().toString(new RecentTimeFormat(), data.getTs()));
            ripple.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), DetailActivity.class);
                    intent.putExtra("url", "http://api.zeroling.com"+data.getH5Url());
                    Utils.Log("url:" + "http://api.zerolong.com" + data.getH5Url());
                    intent.putExtra("title",data.getTitle());
                    getContext().startActivity(intent);
                }
            });
        }
    }
}

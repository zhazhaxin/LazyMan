package cn.hotwoo.alien.servicelife.moudel.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.hotwoo.alien.servicelife.R;
import cn.hotwoo.alien.servicelife.config.API;
import cn.hotwoo.alien.servicelife.model.bean.News;
import cn.hotwoo.alien.servicelife.moudel.news.DetailActivity;

/**
 * Created by Administrator on 2015/10/18.
 */
public class RecommendAdapter extends RecyclerArrayAdapter<News> {

    public RecommendAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendViewHolder(parent);
    }

    class RecommendViewHolder extends BaseViewHolder<News> {

        @Bind(R.id.img)
        SimpleDraweeView img;
        @Bind(R.id.title)
        TextView title;

        public RecommendViewHolder(ViewGroup parent) {
            super(parent, R.layout.view_item_reommed);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(final News data) {
            super.setData(data);
            title.setText(data.getTitle());
            img.setImageURI(Uri.parse(data.getImgUrl()));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), DetailActivity.class);
                    intent.putExtra("isRecommed", "true");
                    if (data.getH5Url().startsWith("http")) {
                        intent.putExtra("url", data.getH5Url());
                    } else {
                        intent.putExtra("url", API.PublicBaseUrl + data.getH5Url());
                    }
                    intent.putExtra("title", data.getTitle());
                    getContext().startActivity(intent);
                }
            });

        }
    }
}

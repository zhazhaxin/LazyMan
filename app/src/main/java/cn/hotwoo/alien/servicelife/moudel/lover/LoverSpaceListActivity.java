package cn.hotwoo.alien.servicelife.moudel.lover;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.melnykov.fab.FloatingActionButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.hotwoo.alien.servicelife.R;
import cn.hotwoo.alien.servicelife.app.BaseActivity;
import cn.hotwoo.alien.servicelife.model.bean.Love;
import cn.hotwoo.alien.servicelife.util.RecentTimeFormat;
import cn.hotwoo.alien.servicelife.util.TimeFormat;
import cn.hotwoo.alien.servicelife.util.Utils;
import cn.hotwoo.alien.servicelife.widget.WrapperGridView;

/**
 * Created by alien on 2015/9/1.
 */
@RequiresPresenter(LoverSpaceListPresenter.class)
public class LoverSpaceListActivity extends BaseActivity<LoverSpaceListPresenter> {

    public static final int PUBLISH_REQUST_CODE = 200;
    public static final String IMGS = "showimg";

    @Bind(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    @Bind(R.id.publish)
    FloatingActionButton publish;

    private LoveAdapter adapter;
    private String[] imgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lover_activity_spacelist);
        ButterKnife.bind(this);
        publish.attachToRecyclerView(recyclerView.getRecyclerView());
        adapter = new LoveAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapterWithProgress(adapter);
        recyclerView.showEmpty();
        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(LoverSpaceListActivity.this, LovePublishActivity.class), PUBLISH_REQUST_CODE);
            }
        });
        recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().getData();
            }
        });
    }

    public void showEmpty(String hint) {
        if (hint.isEmpty())
            recyclerView.showEmpty();
        else ((TextView) recyclerView.getEmptyView()).setText(hint);
    }

    public void setData(Love[] data) {
        adapter.clear();
        adapter.addAll(data);
    }

    class LoveAdapter extends RecyclerArrayAdapter<Love> {

        public LoveAdapter(Context context) {
            super(context);
        }

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup viewGroup, int i) {
            return new LoveViewHolder(viewGroup);
        }
    }

    class LoveViewHolder extends BaseViewHolder<Love> {

        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.author)
        TextView author;
        @Bind(R.id.content)
        TextView content;
        @Bind(R.id.time)
        TextView time;
        @Bind(R.id.loveImg)
        SimpleDraweeView loveImg;
        @Bind(R.id.showimg)
        WrapperGridView showimg;
        @Bind(R.id.viewImgs)
        LinearLayout viewImgs;

        public LoveViewHolder(ViewGroup parent) {
            super(parent, R.layout.lover_item_list);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(Love data) {
            super.setData(data);
            ImageAdapter imgAdapter = new ImageAdapter(LoverSpaceListActivity.this);
            imgAdapter.setMaxCount(9);
            imgAdapter.clear();
            title.setText(data.title);
            author.setText(data.author);
            content.setText(data.content);
            loveImg.setImageURI(Uri.parse(data.loveImg));
            time.setText(new TimeFormat().toString(new RecentTimeFormat(), data.time * 1000));
            if (!data.showImg.isEmpty()) {
                imgs = data.showImg.split("-----");
                imgAdapter.addAll(imgs);
                showimg.setAdapter(imgAdapter);
            } else
                showimg.setVisibility(View.GONE);
            viewImgs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utils.Log("click");
                    Intent intent = new Intent(new Intent(LoverSpaceListActivity.this, LookImageActivity.class));
                    intent.putExtra(IMGS, intent);
                    startActivity(intent);
                }
            });
        }
    }


}

package cn.hotwoo.alien.servicelife.moudel.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.beam.bijection.BeamFragment;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

import cn.hotwoo.alien.servicelife.R;
import cn.hotwoo.alien.servicelife.model.bean.News;

/**
 * Created by alien on 2015/7/28.
 */
@RequiresPresenter(NewsListPresenter.class)
public class NewsListFragment extends BeamFragment<NewsListPresenter> {

    private EasyRecyclerView recyclerView;
    private NewsAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter=new NewsAdapter(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.new_fragment_list,container,false);
        recyclerView= (EasyRecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapterWithProgress(adapter);
        recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().refresh();
            }
        });

        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                getPresenter().loadMore();
            }
        });
        return view;
    }
    public void showEmpty(String hint){
        if(hint.isEmpty())
            recyclerView.showEmpty();
        else ((TextView)recyclerView.getEmptyView()).setText(hint);
    }

    public void setRefreshData(List<News> data) {
        adapter.clear();
        adapter.addAll(data);
    }

    public void addData(List<News> data){
        adapter.addAll(data);
    }

    public void stopMore(){
        adapter.stopMore();
    }
}

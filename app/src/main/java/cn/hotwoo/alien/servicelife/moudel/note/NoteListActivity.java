package cn.hotwoo.alien.servicelife.moudel.note;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.hotwoo.alien.servicelife.R;
import cn.hotwoo.alien.servicelife.app.BaseActivity;
import cn.hotwoo.alien.servicelife.model.bean.Note;
import cn.hotwoo.alien.servicelife.util.RecentTimeFormat;
import cn.hotwoo.alien.servicelife.util.TimeFormat;

/**
 * Created by alien on 2015/8/29.
 */
@RequiresPresenter(NoteListPresenter.class)
public class NoteListActivity extends BaseActivity<NoteListPresenter> {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.addNote)
    com.melnykov.fab.FloatingActionButton addNote;

    private NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_activity_list);
        ButterKnife.bind(this);

        adapter = new NoteAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        addNote.attachToRecyclerView(recyclerView);
        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NoteListActivity.this, NotePublishActivity.class));
            }
        });
    }

    public void setAdapterData(List<Note> data) {
        adapter.clear();
        adapter.addAll(data);
    }

    class NoteAdapter extends RecyclerArrayAdapter<Note> {

        public NoteAdapter(Context context) {
            super(context);
        }

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup viewGroup, int i) {
            return new NoteViewHolder(viewGroup);
        }
    }

    public void removeData(Note data) {
        adapter.remove(data);
    }

    class NoteViewHolder extends BaseViewHolder<Note> {

        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.content)
        TextView content;
        @Bind(R.id.publishTime)
        TextView publishTime;

        public NoteViewHolder(ViewGroup parent) {
            super(parent, R.layout.note_item_list);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(final Note data) {
            super.setData(data);
            title.setText(data.title);
            content.setText(data.content);
            publishTime.setText(new TimeFormat().toString(new RecentTimeFormat(), data.time));
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    return true;
                }
            });
        }
    }
}

package cn.hotwoo.alien.servicelife.moudel.dictionary;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.hotwoo.alien.servicelife.R;
import cn.hotwoo.alien.servicelife.app.BaseActivity;
import cn.hotwoo.alien.servicelife.model.bean.DBWord;

/**
 * Created by alien on 2015/8/18.
 */
@RequiresPresenter(DictionaryPresenter.class)
public class DictionaryActivity extends BaseActivity<DictionaryPresenter> {

    @Bind(R.id.word)
    EditText word;
    @Bind(R.id.query)
    Button query;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.result)
    TextView result;

    private WordAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dictionary_activity);
        ButterKnife.bind(this);
        adapter = new WordAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = word.getText().toString();
                if (str != null)
                    getPresenter().query(str);
            }
        });
    }

    public void setResult(String word, String explains) {
        recyclerView.setVisibility(View.GONE);
        result.setVisibility(View.VISIBLE);
        String all = word + "\n" + explains;
        result.setText(all);
    }

    public void setData(List<DBWord> data) {
        adapter.clear();
        adapter.addAll(data);
    }

    class WordAdapter extends RecyclerArrayAdapter<DBWord> {

        public WordAdapter(Context context) {
            super(context);
        }

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup viewGroup, int i) {
            return new WordViewHolder(viewGroup);
        }
    }

    class WordViewHolder extends BaseViewHolder<DBWord> {

        @Bind(R.id.query)
        TextView query;
        @Bind(R.id.result)
        TextView result;

        public WordViewHolder(ViewGroup parent) {
            super(parent, R.layout.dictionary_item_record);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(final DBWord data) {
            super.setData(data);
            query.setText(data.getQuery());
            result.setText(data.getResult());

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {


                    return true;
                }
            });
        }
    }

    public void removeRecord(DBWord dbWord){
        adapter.remove(dbWord);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dictionary_record, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.record) {
            getPresenter().refreshData();
            recyclerView.setVisibility(View.VISIBLE);
            result.setVisibility(View.GONE);
        }
        return super.onOptionsItemSelected(item);
    }
}

package cn.hotwoo.alien.servicelife.moudel.lbs;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.jude.beam.bijection.RequiresPresenter;

import cn.hotwoo.alien.servicelife.R;
import cn.hotwoo.alien.servicelife.app.BaseActivity;

/**
 * Created by alien on 2015/9/1.
 */
@RequiresPresenter(SearchSourcePresenter.class)
public class SearchSourceActivity extends BaseActivity<SearchSourcePresenter> implements View.OnClickListener{

    private EditText keyword;
    private Button search;
    private GridView gridView;

    private SearchAdapter adapter;

    public static final String SEARCH_CONTENT="search_content";
    public static final int SEARCH_RESULT_CODE=520;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lbs_activity_searchcontent);

        keyword= (EditText) findViewById(R.id.keyword);
        search= (Button) findViewById(R.id.search);
        gridView= (GridView) findViewById(R.id.gridView);
        adapter=new SearchAdapter(this);
        gridView.setAdapter(adapter);

        search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        intent.putExtra(SEARCH_CONTENT,keyword.getText().toString());
        setResult(SEARCH_RESULT_CODE,intent);
        finish();
    }

    class SearchAdapter extends ArrayAdapter<String> {

        public SearchAdapter(Context context) {
            super(context, R.layout.abc_action_bar_title_item);
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            Button button=new Button(SearchSourceActivity.this);
            button.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            button.setTextSize(14);
            button.setBackground(getResources().getDrawable(R.drawable.bg_more));
            button.setTextColor(getResources().getColor(R.color.green));
            button.setText(getPresenter().getData()[position]);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent();
                    intent.putExtra(SEARCH_CONTENT,getPresenter().getData()[position]);
                    setResult(SEARCH_RESULT_CODE,intent);
                    finish();
                }
            });
            return button;
        }

        @Override
        public int getCount() {
            return 15;
        }

    }
}

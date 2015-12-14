package cn.hotwoo.alien.servicelife.moudel.news;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.jude.beam.bijection.RequiresPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.hotwoo.alien.servicelife.R;
import cn.hotwoo.alien.servicelife.app.BaseActivity;
import cn.hotwoo.alien.servicelife.util.Utils;

/**
 * Created by alien on 2015/8/14.
 */
@RequiresPresenter(DetailPresenter.class)
public class DetailActivity extends BaseActivity<DetailPresenter> {

    @Bind(R.id.article)
    WebView article;
    @Bind(R.id.progress)
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity_article);
        ButterKnife.bind(this);
        if (getIntent().getStringExtra("isRecommed") != null) {
            setTitle("详情");
        }

        //webview的基本设置
        WebSettings settings = article.getSettings();
        settings.setSupportZoom(true);  //设置缩放
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);  //当setUseWideViewPort(true)时候，适应屏幕的宽度，默认是false
        settings.setDefaultTextEncodingName("GBK"); //默认是UTF-8
        settings.setLoadsImagesAutomatically(true);   //是否自动加载图片资源，默认是true


        article.getSettings().setJavaScriptEnabled(true);//设置是否支持javascript
        article.addJavascriptInterface(new APPJs(),"androidjs");
    }

    public void setArticle(String url) {
        if (article != null) {
            article.loadUrl(url);
            article.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String u) {
                    article.loadUrl(u);  //webview打开，不用浏览器打开
                    return true;
                }
            });
            article.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onProgressChanged(WebView view, int newProgress) {

                    if (newProgress == 100) {
                        progress.setVisibility(View.GONE);
                    } else {
                        if (progress.getVisibility() == View.GONE) {
                            progress.setVisibility(View.VISIBLE);
                        }
                    }
                    progress.setProgress(newProgress);
                }
            });
        }

    }

    class  APPJs{
        @JavascriptInterface
        public void toast(String toast){
            Utils.Toast(toast);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (article.canGoBack()) {
                article.goBack();
            } else finish();
            return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_news_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.share) {
            getPresenter().shareNews();
        }
        return super.onOptionsItemSelected(item);
    }
}

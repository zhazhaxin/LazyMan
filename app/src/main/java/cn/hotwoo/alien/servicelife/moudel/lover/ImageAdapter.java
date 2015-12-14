package cn.hotwoo.alien.servicelife.moudel.lover;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;

import com.facebook.drawee.view.SimpleDraweeView;

import cn.hotwoo.alien.servicelife.R;

class ImageAdapter extends ArrayAdapter<String> {

    private int maxCount = 0;

    public ImageAdapter(Context context) {
        super(context, R.layout.lover_item_list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SimpleDraweeView imageView = new SimpleDraweeView(getContext());
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(layoutParams);
        imageView.setAspectRatio(1.0f);
        imageView.setImageURI(Uri.parse(getItem(position)));
        return imageView;
    }

    @Override
    public int getCount() {
        if (maxCount == 0)
            return super.getCount();
        else
            return super.getCount() > maxCount ? maxCount : super.getCount();
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }
}
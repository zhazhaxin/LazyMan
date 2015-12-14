package cn.hotwoo.alien.servicelife.widget;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.facebook.drawee.view.SimpleDraweeView;

import cn.hotwoo.alien.servicelife.R;
import cn.hotwoo.alien.servicelife.model.bean.News;

/**
 * Created by Administrator on 2015/10/18.
 */
public class HotRelaxationsView extends RelativeLayout {
    private View container1;
    private TextView title1;
    private SimpleDraweeView image1;
    private MaterialRippleLayout ripple1;

    private View container2;
    private TextView title2;
    private SimpleDraweeView image2;
    private MaterialRippleLayout ripple2;

    private View container3;
    private TextView title3;
    private SimpleDraweeView image3;
    private MaterialRippleLayout ripple3;

    private View container4;
    private TextView title4;
    private SimpleDraweeView image4;
    private MaterialRippleLayout ripple4;

    public HotRelaxationsView(Context context) {
        this(context, null);
    }

    public HotRelaxationsView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HotRelaxationsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_hotrelaxtions, this,false);
        container1 = view.findViewById(R.id.container1);
        title1 = (TextView) view.findViewById(R.id.title1);
        image1 = (SimpleDraweeView) view.findViewById(R.id.image1);
        ripple1 = (MaterialRippleLayout) view.findViewById(R.id.ripple1);
        ripple1.setRippleColor(ripple1.getContext().getResources().getColor(R.color.Gray));

        container2 = view.findViewById(R.id.container2);
        title2 = (TextView) view.findViewById(R.id.title2);
        image2 = (SimpleDraweeView) view.findViewById(R.id.image2);
        ripple2 = (MaterialRippleLayout) view.findViewById(R.id.ripple2);
        ripple2.setRippleColor(ripple2.getContext().getResources().getColor(R.color.Gray));

        container3 = view.findViewById(R.id.container3);
        title3 = (TextView) view.findViewById(R.id.title3);
        image3 = (SimpleDraweeView) view.findViewById(R.id.image3);
        ripple3 = (MaterialRippleLayout) view.findViewById(R.id.ripple3);
        ripple3.setRippleColor(ripple3.getContext().getResources().getColor(R.color.Gray));

        container4 = view.findViewById(R.id.container4);
        title4 = (TextView) view.findViewById(R.id.title4);
        image4 = (SimpleDraweeView) view.findViewById(R.id.image4);
        ripple4 = (MaterialRippleLayout) view.findViewById(R.id.ripple4);
        ripple4.setRippleColor(ripple3.getContext().getResources().getColor(R.color.Gray));
    }

    public void setData(final News[] topics) {
        if (topics.length > 0) {
            container1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent i = new Intent(v.getContext(), TopicDetailActivity.class);
//                    i.putExtra("topic", topics[0]);
//                    v.getContext().startActivity(i);
                }
            });
            title1.setText(topics[0].getTitle());
            image1.setImageURI(Uri.parse(topics[0].getImgUrl()));
        }

        if (topics.length > 1) {
            container2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent i = new Intent(v.getContext(), TopicDetailActivity.class);
//                    i.putExtra("topic",topics[1]);
//                    v.getContext().startActivity(i);
                }
            });
            title2.setText(topics[1].getTitle());
            image2.setImageURI(Uri.parse(topics[1].getImgUrl()));
        }


        if (topics.length > 2) {
            container3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent i = new Intent(v.getContext(), TopicDetailActivity.class);
//                    i.putExtra("topic", topics[2]);
//                    v.getContext().startActivity(i);
                }
            });
            title3.setText(topics[2].getTitle());
            image3.setImageURI(Uri.parse(topics[2].getImgUrl()));
        }

        if (topics.length > 3) {
            container4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent i = new Intent(v.getContext(), TopicDetailActivity.class);
//                    i.putExtra("topic", topics[2]);
//                    v.getContext().startActivity(i);
                }
            });
            title4.setText(topics[3].getTitle());
            image4.setImageURI(Uri.parse(topics[3].getImgUrl()));
        }

    }
}
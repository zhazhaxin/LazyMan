package cn.hotwoo.alien.servicelife.moudel.user;

import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.beam.bijection.RequiresPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.hotwoo.alien.servicelife.R;
import cn.hotwoo.alien.servicelife.app.BaseActivity;
import cn.hotwoo.alien.servicelife.model.UserModel;

/**
 * Created by alien on 2015/8/30.
 */
@RequiresPresenter(ModifyFacePresenter.class)
public class ModifyFaceActivity extends BaseActivity<ModifyFacePresenter> {

    @Bind(R.id.face)
    SimpleDraweeView face;
    @Bind(R.id.album)
    TextView album;
    @Bind(R.id.camera)
    TextView camera;
    @Bind(R.id.net)
    TextView net;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_modifyface);
        ButterKnife.bind(this);
        if(UserModel.getInstance().getUserFromFile().getFace()!=null){
            face.setImageURI(Uri.parse(UserModel.getInstance().getUserFromFile().getFace()));
        }
        album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().getImageFromAlbum();
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().getImageFromCamera();
            }
        });
        net.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().getImageFromNet();
            }
        });
    }

    public void showImage(String path){
        face.setImageURI(Uri.parse(path));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user_updateface,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== R.id.submit){
            getPresenter().uploadFace();
        }
        return super.onOptionsItemSelected(item);
    }
}

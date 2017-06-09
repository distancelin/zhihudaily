package com.distancelin.zhihudaily.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.distancelin.zhihudaily.R;
import com.distancelin.zhihudaily.retrofit.DownloadImage;
import com.distancelin.zhihudaily.retrofit.RetrofitManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class PictureClickActivity extends AppCompatActivity {
    @BindView(R.id.imageViewDetail)
    ImageView mImageView;
    private String mUrl;
    private String mFilename;
    String mFileStoreDir = Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"zhihudailyDownload";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_click);
        ButterKnife.bind(this);
        mUrl=getIntent().getStringExtra("url");
        mFilename=mUrl.split("-")[1];
        Glide.with(this).load(mUrl).centerCrop().into(mImageView);
        getSupportActionBar().setTitle("原图查看");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @OnClick(R.id.downLoad)
    public void downloadPicture(){
        DownloadImage downloadImage= RetrofitManager.convert("http://www.baidu.com/", DownloadImage.class,false);
        downloadImage.downloadImage(mUrl)
                .subscribeOn(Schedulers.io())
                .doOnNext(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(@NonNull ResponseBody responseBody) throws Exception {
                        saveToDisk(responseBody);
                    }
                })
                .map(new Function<ResponseBody, String>() {
                    @Override
                    public String apply(@NonNull ResponseBody responseBody) throws Exception {
                        return "下载成功";
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(PictureClickActivity.this,"图片已下载到"+mFileStoreDir,Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void saveToDisk(ResponseBody responseBody) {

        Log.d("H","目录"+ mFileStoreDir);
        InputStream is;
        File dir = new File(mFileStoreDir);
        File file = new File(dir+File.separator+mFilename);
        byte[] buf = new byte[2048];
        int len;
        FileOutputStream fos;
        try {
            is = responseBody.byteStream();
            if (!dir.exists()) {
                //mkdirs可以在外部文件夹不存在的时候也进行创建
                dir.mkdirs();
            }
            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
            is.close();
            fos.close();
            MediaStore.Images.Media.insertImage(getContentResolver(),file.getAbsolutePath(),mFilename,null);
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://"+file.getAbsolutePath())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

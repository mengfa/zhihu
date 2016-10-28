package com.shine.zhihu;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

public class ZhiHuApplication extends Application {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate();
        initImageLoader(getApplicationContext());
    }

    private void initImageLoader(Context context) {

        File cacheDir = StorageUtils.getCacheDirectory(context);
        ImageLoaderConfiguration confit = ImageLoaderConfiguration.createDefault(context);
        ImageLoader.getInstance().init(confit);
    }
}

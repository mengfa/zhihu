package com.shine.zhihu.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.ResponseHandlerInterface;

public class HttpUtils {

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get (String url, ResponseHandlerInterface responseHandler)
    {
        client.get(Constant.BASEURL+url,responseHandler);
    }
    public static void getImage(String url, ResponseHandlerInterface responseHandler)
    {
        client.get(url,responseHandler);

    }

    public static boolean isNetworkConnecter(Context context){
        if (context != null){
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if(mNetworkInfo != null){
                return mNetworkInfo.isAvailable();
            }

        }
        return  false;



    }
}

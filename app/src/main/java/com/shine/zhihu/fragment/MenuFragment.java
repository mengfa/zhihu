package com.shine.zhihu.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.shine.zhihu.R;
import com.shine.zhihu.activity.MainActivity;
import com.shine.zhihu.model.NewListItem;
import com.shine.zhihu.util.Constant;
import com.shine.zhihu.util.HttpUtils;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import static com.shine.zhihu.R.*;

public class MenuFragment extends BaseFragment implements View.OnClickListener {
    private ListView lv_item;
    private TextView tv_download, tv_main, tv_backup, tv_login;
    private LinearLayout ll_menu;

    private List<NewListItem> items;
    private Handler handle = new Handler();
    private boolean isLight;
    private NewsTypeAdapter mAdapter;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ll_menu = (LinearLayout) view.findViewById(id.ll_menu);
        tv_login = (TextView) view.findViewById(R.id.tv_login);
        tv_backup = (TextView) view.findViewById(R.id.tv_backup);
        tv_download = (TextView) view.findViewById(R.id.tv_download);
        tv_download.setOnClickListener(this);
        tv_main = (TextView) view.findViewById(R.id.tv_main);
        tv_main.setOnClickListener(this);
        lv_item = (ListView) view.findViewById(R.id.lv_item);


        return view;
    }

    @Override
    protected void initData() {
        super.initData();

        items = new ArrayList<NewListItem>();
        if (HttpUtils.isNetworkConnecter(mActivity)){
            HttpUtils.get(Constant.THEMES,new JsonHttpResponseHandler(){
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    String json = response.toString();


                    parseJson(response);



                }
            });
        }

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_main:
//                ((MainActivity) mActivity).loadLatest();
//                ((MainActivity) mActivity).closeMenu();
                break;
        }
    }private void parseJson(JSONObject response) {

        try {
            JSONArray itemsArray = response.getJSONArray("others");
            for (int i = 0;i <itemsArray.length();i++){
                NewListItem newListItem = new NewListItem();
                JSONObject itemsObject = itemsArray.getJSONObject(i);
                newListItem.setId(itemsObject.getString("id"));
                newListItem.setTitle(itemsObject.getString("name"));

                items.add(newListItem);


            }
            mAdapter = new NewsTypeAdapter();
            lv_item.setAdapter(mAdapter);


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public class NewsTypeAdapter extends BaseAdapter{



        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.menu_item,parent,false);

            }
            TextView tv_item = (TextView) convertView.findViewById(id.tv_item);
            tv_item.setTextColor(getResources().getColor(color.light_menu_listview_textcolor));
            tv_item.setText(items.get(position).getTitle());
            return convertView;
        }
    }


}

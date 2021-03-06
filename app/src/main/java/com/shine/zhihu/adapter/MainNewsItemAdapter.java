package com.shine.zhihu.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shine.zhihu.R;
import com.shine.zhihu.model.StoriesEntity;
import com.shine.zhihu.util.Constant;

import java.util.ArrayList;
import java.util.List;

public class MainNewsItemAdapter extends BaseAdapter {

    private List<StoriesEntity> entities;
    private Context context;
    private ImageLoader mImageloader;
    private DisplayImageOptions options;
    private boolean isLight;

    public MainNewsItemAdapter(Context context) {
        this.context = context;
        this.entities = new ArrayList<>();
        mImageloader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
//        isLight = ((MainActivity) context).isLight();
    }
    public void addList(List<StoriesEntity> items){
        this.entities.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return entities.size();
    }

    @Override
    public Object getItem(int position) {
        return entities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.main_news_item,parent,false);
            viewHolder.iv_title = (ImageView) convertView.findViewById(R.id.iv_title);
            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.tv_topic = (TextView) convertView.findViewById(R.id.tv_topic);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        StoriesEntity entity = entities.get(position);
        ((FrameLayout) viewHolder.tv_topic.getParent()).setBackgroundResource( R.drawable.item_background_selector_light);
        if (entity.getType() == Constant.TOPIC) {
            ((FrameLayout) viewHolder.tv_topic.getParent()).setBackgroundColor(Color.TRANSPARENT);
            viewHolder.tv_title.setVisibility(View.GONE);
            viewHolder.iv_title.setVisibility(View.GONE);
            viewHolder.tv_topic.setVisibility(View.VISIBLE);
            viewHolder.tv_topic.setText(entity.getTitle());
        } else {
            ((FrameLayout) viewHolder.tv_topic.getParent()).setBackgroundResource(isLight ? R.drawable.item_background_selector_light : R.drawable.item_background_selector_dark);
            viewHolder.tv_topic.setVisibility(View.GONE);
            viewHolder.tv_title.setVisibility(View.VISIBLE);
            viewHolder.iv_title.setVisibility(View.VISIBLE);
            viewHolder.tv_title.setText(entity.getTitle());
            mImageloader.displayImage(entity.getImages().get(0), viewHolder.iv_title, options);
        }


        return convertView;
    }

    public static class ViewHolder {
        TextView tv_topic;
        TextView tv_title;
        ImageView iv_title;
    }
}

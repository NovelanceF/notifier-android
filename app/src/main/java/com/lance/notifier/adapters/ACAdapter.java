package com.lance.notifier.adapters;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.lance.notifier.R;
import com.lance.notifier.model.Announcement;
import com.lance.notifier.network.BitmapLruCache;

import java.util.List;

/**
 * Created by Novelance on 2/19/14.
 */
public class ACAdapter extends BaseAdapter {

  private List<Announcement> mList;
  private LayoutInflater mInflater;
  private ImageLoader mImageLoader;
  private Activity mActivity;
  private Typeface typeface;

  public ACAdapter(Activity c, List<Announcement> l) {
    mInflater = LayoutInflater.from(c);
    mList = l;
    RequestQueue requestQueue = Volley.newRequestQueue(c);
    mImageLoader = new ImageLoader(requestQueue, new BitmapLruCache());
    mActivity = c;
    typeface = Typeface.createFromAsset(mActivity.getAssets(), "font/Roboto-Light.ttf");
  }
  @Override
  public int getCount() {
    return mList.size();
  }

  @Override
  public Object getItem(int position) {
    return mList.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  public void refresh(List<Announcement> list) {
    mList = list;
    notifyDataSetChanged();
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    if(convertView == null) {
      convertView = mInflater.inflate(R.layout.item_ac, parent, false);
    }

    NetworkImageView acImage = ViewHolder.get(convertView, R.id.ac_image);
    TextView title = ViewHolder.get(convertView, R.id.ac_title);
    TextView body = ViewHolder.get(convertView, R.id.ac_body);
    TextView time = ViewHolder.get(convertView, R.id.ac_time);

    if(position == 0) {
      int padding_in_dp = 48;  // 48 dps
      final float scale = mActivity.getResources().getDisplayMetrics().density;
      int padding_in_px = (int) (padding_in_dp * scale + 0.5f);
      convertView.setPadding(0,padding_in_px , 0, 0);
    }

    acImage.setImageUrl(mList.get(position).getCropped(), mImageLoader);
    title.setText(mList.get(position).getTitle());
    body.setText(mList.get(position).getDetail());
    time.setText(mList.get(position).getPubDate());

    return convertView;
  }
}

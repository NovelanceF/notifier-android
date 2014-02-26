package com.lance.notifier.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lance.notifier.R;
import com.lance.notifier.model.Notification;

import net.tsz.afinal.FinalDb;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Novelance on 2/20/14.
 */
public class NFAdapter extends BaseAdapter {

  public Activity mActivity;
  public LayoutInflater mInflater;
  public List<Notification> mList = new ArrayList<Notification>();

  public NFAdapter(Activity a, List<Notification> l) {
    mActivity = a;
    mInflater = LayoutInflater.from(a);
    mList = l;
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

  public void refresh(List<Notification> list) {
    mList = list;
    notifyDataSetChanged();
  }

  @Override
  public View getView(final int position, View convertView, ViewGroup parent) {
    if (convertView == null) {
      convertView = mInflater.inflate(R.layout.item_nf, parent, false);
    }

    TextView sender = ViewHolder.get(convertView, R.id.nf_sender);
    TextView body = ViewHolder.get(convertView, R.id.nf_body);
    TextView time = ViewHolder.get(convertView, R.id.nf_time);
    Button tag = ViewHolder.get(convertView, R.id.nf_taged);

    sender.setText(mList.get(position).getSender());
    body.setText(mList.get(position).getDetail());
    time.setText(mList.get(position).getPubDate());

    if(position == 0) {
      int padding_in_dp = 48;  // 48 dps
      final float scale = mActivity.getResources().getDisplayMetrics().density;
      int padding_in_px = (int) (padding_in_dp * scale + 0.5f);
      convertView.setPadding(0,padding_in_px , 0, 0);
    } else {
      convertView.setPadding(0, 0 , 0, 0);
    }

    tag.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Notification nf;
        nf = (Notification) getItem(position);
        FinalDb db = FinalDb.create(mActivity);
        if(!existed(nf.getPubDate(), db)) {
          db.save(nf);
          Toast.makeText(mActivity, "Taged", 2000).show();
        } else {
          Toast.makeText(mActivity, "Already Taged", 2000).show();
        }
      }
    });
    return convertView;
  }

  public boolean existed(String s, FinalDb db) {
    List<Notification> list = db.findAll(Notification.class);
    for(Notification item : list) {
      if(item.getPubDate().equals(s)) {
        return true;
      }
    }
    return false;
  }
}

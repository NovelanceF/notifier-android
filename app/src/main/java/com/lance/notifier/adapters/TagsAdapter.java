package com.lance.notifier.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lance.notifier.R;
import com.lance.notifier.model.Notification;
import com.lance.notifier.ui.activity.LoadingActivity;

import net.tsz.afinal.FinalDb;

import java.util.List;

/**
 * Created by Novelance on 2/20/14.
 */
public class TagsAdapter extends NFAdapter {

  public TagsAdapter(Activity a, List<Notification> l) {
    super(a, l);
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

    sender.setText(mList.get(position).getSender() + position);
    body.setText(mList.get(position).getDetail());
    time.setText(mList.get(position).getPubDate());

    tag.setText("－");

    tag.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Notification nf;
        nf = (Notification) getItem(position);
        FinalDb db = FinalDb.create(mActivity);
        nf.setId(db.findAll(Notification.class).get(position).getId());
        db.delete(nf);

        Intent intent = new Intent(mActivity, LoadingActivity.class);
        mActivity.startActivity(intent);
        mActivity.finish();
        mActivity.overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
      }
    });
    return convertView;
  }
}

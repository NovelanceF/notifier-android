package com.lance.notifier.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import com.lance.notifier.R;
import com.lance.notifier.adapters.TagsAdapter;
import com.lance.notifier.model.Notification;

import net.tsz.afinal.FinalDb;

public class Taged extends ActionBarActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_taged);

    ListView taged = (ListView)findViewById(R.id.nf_taged_list);
    FinalDb db = FinalDb.create(this);
    TagsAdapter adapter = new TagsAdapter(this, db.findAll(Notification.class));
    taged.setAdapter(adapter);
  }

}

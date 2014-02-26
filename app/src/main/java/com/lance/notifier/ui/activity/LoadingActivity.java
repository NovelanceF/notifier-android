package com.lance.notifier.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;

import com.lance.notifier.R;

public class LoadingActivity extends ActionBarActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_loading);
    new Handler().postDelayed(new Runnable() {

      @Override
      public void run() {
        Intent intent = new Intent(LoadingActivity.this, Taged.class);
        startActivity(intent);
        LoadingActivity.this.finish();
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
      }
    }, 1000);
  }

}

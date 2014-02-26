package com.lance.notifier.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.lance.notifier.R;

public class SettingsActivity extends PreferenceActivity {

  @Override
  protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);

    setupSimplePreferencesScreen();
  }

  private void setupSimplePreferencesScreen() {
    addPreferencesFromResource(R.xml.pref_general);
  }

  @Override
  public void onBackPressed() {
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
    overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    super.onBackPressed();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
  }
}

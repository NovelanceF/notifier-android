package com.lance.notifier.ui.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lance.notifier.R;
import com.lance.notifier.ui.activity.SettingsActivity;
import com.lance.notifier.ui.activity.Taged;

/**
 * Created by Novelance on 2/20/14.
 */
public class Drawer extends Fragment implements View.OnClickListener{

  private Typeface typefaceLight;
  private Typeface typefaceThin;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_drawer, container, false);
    typefaceLight = Typeface.createFromAsset(getActivity().getAssets(), "font/Roboto-Light.ttf");
    typefaceThin = Typeface.createFromAsset(getActivity().getAssets(), "font/Roboto-Thin.ttf");
    Button setting = (Button)rootView.findViewById(R.id.drawer_button_setting);
    setting.setTypeface(typefaceLight);
    Button taged = (Button)rootView.findViewById(R.id.drawer_button_taged);
    taged.setTypeface(typefaceLight);
    TextView grade = (TextView)rootView.findViewById(R.id.drawer_grade);
    grade.setTypeface(typefaceThin);
    Button about = (Button)rootView.findViewById(R.id.drawer_button_about);
    about.setTypeface(typefaceLight);
    TextView gradeLabel = (TextView)rootView.findViewById(R.id.drawer_grade_label);
    gradeLabel.setTypeface(typefaceLight);
    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
    grade.setText(preferences.getString("grade_list", "0"));
    setting.setOnClickListener(this);
    taged.setOnClickListener(this);
    return rootView;
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()){
      case R.id.drawer_button_setting: {
        Intent intent = new Intent(getActivity(), SettingsActivity.class);
        getActivity().startActivity(intent);
        getActivity().overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        getActivity().finish();
      }
      break;
      case R.id.drawer_button_taged: {
        Intent intent = new Intent(getActivity(), Taged.class);
        getActivity().startActivity(intent);
        getActivity().overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
      }
    }
  }
}

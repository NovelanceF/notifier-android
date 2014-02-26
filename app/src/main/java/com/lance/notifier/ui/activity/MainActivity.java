package com.lance.notifier.ui.activity;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.igexin.slavesdk.MessageManager;
import com.lance.notifier.R;
import com.lance.notifier.ui.animation.ZoomOutPageTransformer;
import com.lance.notifier.ui.fragment.ACFragment;
import com.lance.notifier.ui.fragment.Drawer;
import com.lance.notifier.ui.fragment.NFFragment;
import com.lance.notifier.views.PagerSlidingTabStrip;


public class MainActivity extends ActionBarActivity {

  private ViewPager contentPager;
  private static RequestQueue requestQueue;
  private DrawerLayout mDrawerLayout;
  private ActionBarDrawerToggle mDrawerToggle;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    requestQueue = Volley.newRequestQueue(this);
    setPager();
    MessageManager.getInstance().initialize(this.getApplicationContext());
    mDrawerLayout = (DrawerLayout)findViewById(R.id.content_drawer);
    mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
    mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
            R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {
      public void onDrawerClosed(View view) {
      }

      public void onDrawerOpened(View drawerView) {
      }
    };
    mDrawerLayout.setDrawerListener(mDrawerToggle);
    getSupportFragmentManager().beginTransaction()
            .replace(R.id.left_drawer, new Drawer())
            .commit();

    getActionBar().setDisplayHomeAsUpEnabled(true);
    getActionBar().setHomeButtonEnabled(true);
    getActionBar().setBackgroundDrawable(this.getBaseContext().getResources().getDrawable(R.drawable.action_bar_back));
    getActionBar().setIcon(R.drawable.ic_title);
    setActionbarText();
  }

  private void setActionbarText() {
    int titleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
    TextView textView = (TextView) findViewById(titleId);
    textView.setTypeface(Typeface.createFromAsset(getAssets(), "font/Roboto-Light.ttf"));
    textView.setTextColor(0xFF434343);
    //textView.setTextSize(14);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_settings) {
      setPager();
      return true;
    }

    if (mDrawerToggle.onOptionsItemSelected(item)) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    mDrawerToggle.syncState();
  }

  private void setPager() {
    contentPager = (ViewPager)findViewById(R.id.content_pager);
    mPagerAdapter adapter = new mPagerAdapter(getSupportFragmentManager());
    contentPager.setPageTransformer(true, new ZoomOutPageTransformer());
    contentPager.setAdapter(adapter);
    PagerSlidingTabStrip tabs = (PagerSlidingTabStrip)findViewById(R.id.tabs);
    tabs.setViewPager(contentPager);
    //contentPager.setCurrentItem(2);
  }

  private class mPagerAdapter extends FragmentStatePagerAdapter {

    String Title[] = {"ANNOUNCEMENT", "NOTIFICATION"};
    public mPagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int i) {
      if(i == 0) {
        return new ACFragment(requestQueue);
      } else if (i ==1) {
        return new NFFragment(requestQueue);
      }
      return null;
    }

    @Override
    public int getCount() {
      return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
      return Title[position];
    }
  }

}

package com.lance.notifier.ui.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.lance.notifier.R;
import com.lance.notifier.adapters.NFAdapter;
import com.lance.notifier.network.API;
import com.lance.notifier.network.Data;
import com.lance.notifier.views.Footer;

/**
 * Created by Novelance on 2/20/14.
 */
public class NFFragment extends Fragment {

  private RequestQueue requestQueue;
  private int page = 1;

  public NFFragment(RequestQueue r) {
    requestQueue = r;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_nf, container, false);
    final ListView nfListView = (ListView)rootView.findViewById(R.id.nf_list_view);
    final Data data = new Data(getActivity(), requestQueue);
    final NFAdapter adapter = new NFAdapter(getActivity(), data.getNFList());
    final Footer mFooter = new Footer(getActivity());
    nfListView.addFooterView(mFooter.getView());
    nfListView.setAdapter(adapter);
    data.getNFData(API.getNFByPage(1, getCurGrade()), adapter, mFooter);
    nfListView.setOnScrollListener(new AbsListView.OnScrollListener() {
      @Override
      public void onScrollStateChanged(AbsListView view, int scrollState) {
      }

      @Override
      public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (mFooter.getState() == Footer.State.Loading
                || mFooter.getState() == Footer.State.TheEnd) {
          return;
        }
        if (firstVisibleItem + visibleItemCount >= totalItemCount
                && totalItemCount != 0
                && totalItemCount != nfListView.getHeaderViewsCount()
                + nfListView.getFooterViewsCount() && adapter.getCount() > 0) {
          mFooter.setState(Footer.State.Loading);
          data.getNFData(API.getNFByPage(++page, getCurGrade()), adapter, mFooter);
        }
      }
    });

    return rootView;
  }

  private String getCurGrade() {
    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
    if("All".equals(preferences.getString("grade_list", "All"))){
      return 0 + "";
    } else {
      return preferences.getString("grade_list", "All");
    }
  }
}

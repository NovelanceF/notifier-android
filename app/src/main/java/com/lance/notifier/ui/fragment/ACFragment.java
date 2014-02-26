package com.lance.notifier.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.lance.notifier.R;
import com.lance.notifier.adapters.ACAdapter;
import com.lance.notifier.network.API;
import com.lance.notifier.network.Data;

/**
 * Created by Novelance on 2/19/14.
 */
public class ACFragment extends Fragment {

  private Data data;
  private RequestQueue requestQueue;

  public ACFragment(RequestQueue r) {
    requestQueue = r;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_ac, container, false);

    data = new Data(getActivity(), requestQueue);

    ListView listView = (ListView)rootView.findViewById(R.id.ac_list_view);

    ACAdapter adapter = new ACAdapter(getActivity(), data.getACList());
    listView.setAdapter(adapter);

    if(data.getACList().size() == 0) {
      data.getACData(API.FETCH_AC, adapter);
    }

    return rootView;
  }
}

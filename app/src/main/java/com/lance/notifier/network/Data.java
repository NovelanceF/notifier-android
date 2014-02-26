package com.lance.notifier.network;

import android.app.Activity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.lance.notifier.adapters.ACAdapter;
import com.lance.notifier.adapters.NFAdapter;
import com.lance.notifier.model.Announcement;
import com.lance.notifier.model.Notification;
import com.lance.notifier.views.Footer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Novelance on 2/19/14.
 */
public class Data {

  private RequestQueue mRequestQueue;
  private List<Announcement> ACList = new ArrayList<Announcement>();
  private List<Notification> NFList = new ArrayList<Notification>();

  public Data(Activity c, RequestQueue r) {
    mRequestQueue = r;
  }

  public List<Announcement> getACList() {
    return ACList;
  }

  public List<Notification> getNFList() {
    return NFList;
  }

  public void getACData(final String url, final ACAdapter adapter) {
    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
            new Response.Listener<JSONObject>() {
              @Override
              public void onResponse(JSONObject jsonObject) {
                try {
                  initACList(jsonObject, adapter);
                } catch (JSONException e) {
                  e.printStackTrace();
                }
              }
            }, new Response.ErrorListener() {

      @Override
      public void onErrorResponse(VolleyError volleyError) {
      }
    }
    );
    mRequestQueue.add(jsonObjectRequest);
  }

  public void getNFData(final String url, final NFAdapter adapter, final Footer f) {
    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
            new Response.Listener<JSONObject>() {
              @Override
              public void onResponse(JSONObject jsonObject) {
                try {
                  initNFList(jsonObject, adapter, f);
                } catch (JSONException e) {
                  e.printStackTrace();
                }
              }
            }, new Response.ErrorListener() {

      @Override
      public void onErrorResponse(VolleyError volleyError) {
      }
    }
    );
    mRequestQueue.add(jsonObjectRequest);
  }

  private void initACList(JSONObject j, ACAdapter adapter) throws JSONException {
    JSONArray array = j.getJSONArray("list");

    for (int i = 0; i < 3; i++) {
      Announcement ac = new Announcement();
      JSONObject json = array.getJSONObject(i);
      ac.setTitle(json.getString("title"));
      ac.setImageUrl(json.getString("imageURL"));
      if (json.getString("imageURL").length() != 0) {
        ac.setCropped(getCroppedUrl(json.getString("imageURL")));
      }
      ac.setDetail(json.getString("detail"));
      ac.setPubDate(json.getString("date"));
      getACList().add(ac);
    }

    adapter.refresh(ACList);

  }

  private void initNFList(JSONObject j, NFAdapter adapter, Footer f) throws JSONException {
    JSONArray array = j.getJSONArray("list");
    int perPage = j.getInt("per_page");
    int curPage = j.getInt("page");
    int total = j.getInt("total");

    f.setState(Footer.State.Idle);

    for (int i = 0; i < perPage; i++) {
      Notification nf = new Notification();
      JSONObject json = array.getJSONObject(i);
      nf.setSender(json.getString("name"));
      nf.setPubDate(json.getString("date"));
      nf.setGrade(json.getString("grade"));
      nf.setDetail(json.getString("detail"));
      nf.setId(i);
      getNFList().add(nf);

      if((curPage - 1) * perPage + i + 1 >= total) {
        f.setState(Footer.State.TheEnd);
        break;
      }
    }

    adapter.refresh(getNFList());
  }

  private String getCroppedUrl(String s) {
    return API.BASE_URL + s.split("\\.")[0] + "cropped." + s.split("\\.")[1];
  }
}

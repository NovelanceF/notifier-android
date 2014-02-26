package com.lance.notifier.network;

/**
 * Created by Novelance on 2/19/14.
 */
public class API {

  public static final String BASE_URL = "http://10.170.14.233:80";

  public static final String FETCH_AC = BASE_URL + "/getACList/";

  public static final String FETCH_NF = BASE_URL + "/getNFList/";

  public static final String getNFByPage(int page, String grade) {
    return BASE_URL + "/getNFList/?page=" + page + "&grade=" + grade;
  }
}

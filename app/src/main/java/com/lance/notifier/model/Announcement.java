package com.lance.notifier.model;

/**
 * Created by Novelance on 2/19/14.
 */
public class Announcement {

  String title;
  String Detail;
  String pubDate;
  String grade;

  public String getCropped() {
    return cropped;
  }

  public void setCropped(String cropped) {
    this.cropped = cropped;
  }

  String sender;
  String cropped;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDetail() {
    return Detail;
  }

  public void setDetail(String detail) {
    Detail = detail;
  }

  public String getPubDate() {
    return pubDate;
  }

  public void setPubDate(String pubDate) {
    this.pubDate = pubDate;
  }

  public String getGrade() {
    return grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }

  public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  String imageUrl;
  int id;

}

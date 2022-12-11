package az.elixir.experise.dto;

import az.elixir.experise.model.AboutEntity;

public class AboutView {

  private String title;
  private String text;
  private String photo;

  public AboutView() {}

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public void mapper(AboutEntity a) {
    this.text = a.getText();
    this.title = a.getTitle();
    this.photo = a.getPhoto();
  }
}

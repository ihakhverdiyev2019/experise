package az.elixir.experise.dto;

import az.elixir.experise.model.DegreesAndCredentialsEntity;

public class DegreesAndCredentialsView {

  private String photoUrl;

  private String name;

  private String text;

  public DegreesAndCredentialsView() {}

  public String getPhotoUrl() {
    return photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public void mapper(DegreesAndCredentialsEntity a) {
    this.name = a.getName();
    this.photoUrl = a.getPhotoUrl();
    this.text = a.getText();
  }
}

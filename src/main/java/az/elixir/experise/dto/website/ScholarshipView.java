package az.elixir.experise.dto.website;

import az.elixir.experise.model.ScholarshipEntity;

public class ScholarshipView {

  private String photoUrl;

  private String name;

  private String text;

  public ScholarshipView() {}

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

  public void mapper(ScholarshipEntity a) {
    this.name = a.getTitle();
    this.photoUrl = a.getPhoto();
    this.text = a.getText();
  }
}

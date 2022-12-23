package az.elixir.experise.dto.website;

import az.elixir.experise.model.BlogEntity;

public class BlogView {

  private String photoUrl;

  private String name;

  private String text;

  public BlogView() {}

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

  public void mapper(BlogEntity a) {
    this.name = a.getTitle();
    this.photoUrl = a.getPhoto();
    this.text = a.getText();
  }
}

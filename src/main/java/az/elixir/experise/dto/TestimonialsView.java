package az.elixir.experise.dto;

import az.elixir.experise.model.TestimonialsEntity;

public class TestimonialsView {

  private String photoUrl;

  private String name;

  private String position;

  private String text;

  public TestimonialsView() {}

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

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public void mapper(TestimonialsEntity a) {
    this.name = a.getName();
    this.position = a.getPosition();
    this.photoUrl = a.getPhoto();
    this.text = a.getText();
  }
}

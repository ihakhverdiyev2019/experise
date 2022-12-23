package az.elixir.experise.dto.website;

import az.elixir.experise.model.BannerEntity;

public class BannerView {

  private String title;

  private String text;

  private String shapePhoto;

  private String photo;

  public BannerView() {}

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

  public String getShapePhoto() {
    return shapePhoto;
  }

  public void setShapePhoto(String shapePhoto) {
    this.shapePhoto = shapePhoto;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public void mapper(BannerEntity a) {
    this.photo = a.getPhoto();
    this.shapePhoto = a.getShapePhoto();
    this.text = a.getText();
    this.title = a.getTitle();
  }
}

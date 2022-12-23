package az.elixir.experise.dto.website;

import az.elixir.experise.model.BlogEntity;

public class AllBlogsView {

  private int id;

  private String photoUrl;

  private String name;

  public AllBlogsView() {}

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

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

  public void mapper(BlogEntity a) {
    this.id = a.getId();
    this.name = a.getTitle();
    this.photoUrl = a.getPhotoFront();
  }
}

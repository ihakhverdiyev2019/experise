package az.elixir.experise.model;

import javax.persistence.*;

@Entity
@Table(name = "degrees_and_credentials")
public class DegreesAndCredentialsEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  private String photoUrl; // (780X410)

  private String frontPhotoUrl; // (400X417)

  private String category;

  @Column(columnDefinition = "text")
  private String text;

  private int langId;

  private boolean isFooter;

  public DegreesAndCredentialsEntity() {}

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhotoUrl() {
    return photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }

  public String getFrontPhotoUrl() {
    return frontPhotoUrl;
  }

  public void setFrontPhotoUrl(String frontPhotoUrl) {
    this.frontPhotoUrl = frontPhotoUrl;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public int getLangId() {
    return langId;
  }

  public void setLangId(int langId) {
    this.langId = langId;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public boolean getIsFooter() {
    return isFooter;
  }

  public void setIsFooter(boolean footer) {
    isFooter = footer;
  }
}

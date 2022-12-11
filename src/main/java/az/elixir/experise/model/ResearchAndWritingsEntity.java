package az.elixir.experise.model;

import javax.persistence.*;

@Entity
@Table(name = "research_and_writings")
public class ResearchAndWritingsEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  @Column(columnDefinition = "text")
  private String text;

  private String frontPhotoUrl;

  private String photoUrl;

  private int langId;

  public ResearchAndWritingsEntity() {}

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

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getFrontPhotoUrl() {
    return frontPhotoUrl;
  }

  public void setFrontPhotoUrl(String frontPhotoUrl) {
    this.frontPhotoUrl = frontPhotoUrl;
  }

  public String getPhotoUrl() {
    return photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }

  public int getLangId() {
    return langId;
  }

  public void setLangId(int langId) {
    this.langId = langId;
  }
}

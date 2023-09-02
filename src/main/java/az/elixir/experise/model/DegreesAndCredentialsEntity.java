package az.elixir.experise.model;

import java.time.Instant;

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

  private int referId;

  private int createdBy;
  private int updatedBy;

  private Instant createdAt;

  private Instant updatedAt;

  @Column(columnDefinition = "text")
  private String seoKey;

  @Column(columnDefinition = "text")
  private String seoDescription;

  @Column(columnDefinition = "text")
  private String seoTitle;

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

  public boolean isFooter() {
    return isFooter;
  }

  public void setFooter(boolean footer) {
    isFooter = footer;
  }

  public int getReferId() {
    return referId;
  }

  public void setReferId(int referId) {
    this.referId = referId;
  }

  public int getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(int createdBy) {
    this.createdBy = createdBy;
  }

  public int getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(int updatedBy) {
    this.updatedBy = updatedBy;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Instant updatedAt) {
    this.updatedAt = updatedAt;
  }

  public String getSeoKey() {
    return seoKey;
  }

  public void setSeoKey(String seoKey) {
    this.seoKey = seoKey;
  }

  public String getSeoDescription() {
    return seoDescription;
  }

  public void setSeoDescription(String seoDescription) {
    this.seoDescription = seoDescription;
  }

  public String getSeoTitle() {
    return seoTitle;
  }

  public void setSeoTitle(String seoTitle) {
    this.seoTitle = seoTitle;
  }
}

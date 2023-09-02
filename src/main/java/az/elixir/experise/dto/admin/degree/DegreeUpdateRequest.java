package az.elixir.experise.dto.admin.degree;

public class DegreeUpdateRequest {

  private String photo;

  private String photoFront;

  private String title;
  private String text;
  private String seoKey;
  private String seoTitle;
  private String seoDescription;

  private String categoryKey;
  private int degreeId;
  private String langCode;
  private int referId;

  private String isfooter;

  public DegreeUpdateRequest() {}

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public String getPhotoFront() {
    return photoFront;
  }

  public void setPhotoFront(String photoFront) {
    this.photoFront = photoFront;
  }

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

  public String getSeoKey() {
    return seoKey;
  }

  public void setSeoKey(String seoKey) {
    this.seoKey = seoKey;
  }

  public String getSeoTitle() {
    return seoTitle;
  }

  public void setSeoTitle(String seoTitle) {
    this.seoTitle = seoTitle;
  }

  public String getSeoDescription() {
    return seoDescription;
  }

  public void setSeoDescription(String seoDescription) {
    this.seoDescription = seoDescription;
  }

  public String getCategoryKey() {
    return categoryKey;
  }

  public void setCategoryKey(String categoryKey) {
    this.categoryKey = categoryKey;
  }

  public int getDegreeId() {
    return degreeId;
  }

  public void setDegreeId(int degreeId) {
    this.degreeId = degreeId;
  }

  public String getLangCode() {
    return langCode;
  }

  public void setLangCode(String langCode) {
    this.langCode = langCode;
  }

  public int getReferId() {
    return referId;
  }

  public void setReferId(int referId) {
    this.referId = referId;
  }

  public String getIsfooter() {
    return isfooter;
  }

  public void setIsfooter(String isfooter) {
    this.isfooter = isfooter;
  }
}

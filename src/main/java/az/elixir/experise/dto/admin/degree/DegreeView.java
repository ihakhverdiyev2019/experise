package az.elixir.experise.dto.admin.degree;

public class DegreeView {
  private int id;
  private String title;
  private String text;

  private String isFooter;

  private String photo;

  private String photoFront;

  private String seoKey;
  private String seoDescription;
  private String seoTitle;

  private String categoryKey;

  private int referId;

  private String langCode;

  public DegreeView() {}

  public String getIsFooter() {
    return isFooter;
  }

  public void setIsFooter(String isFooter) {
    this.isFooter = isFooter;
  }

  public String getPhotoFront() {
    return photoFront;
  }

  public void setPhotoFront(String photoFront) {
    this.photoFront = photoFront;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
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

  public int getReferId() {
    return referId;
  }

  public void setReferId(int referId) {
    this.referId = referId;
  }

  public String getLangCode() {
    return langCode;
  }

  public void setLangCode(String langCode) {
    this.langCode = langCode;
  }

  public String getCategoryKey() {
    return categoryKey;
  }

  public void setCategoryKey(String categoryKey) {
    this.categoryKey = categoryKey;
  }
}

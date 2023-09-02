package az.elixir.experise.dto.admin.course;

public class CoursesUpdateRequest {

  private String photo;
  private String title;
  private String text;
  private String seoKey;
  private String seoTitle;
  private String seoDescription;
  private int courseId;
  private String langCode;
  private int referId;

  public CoursesUpdateRequest() {}

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
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

  public int getCourseId() {
    return courseId;
  }

  public void setCourseId(int courseId) {
    this.courseId = courseId;
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
}

package az.elixir.experise.dto.admin.about;

public class AboutUpdateRequest {

  private String photo;
  private String text;

  private int aboutId;

  private String langCode;

  public AboutUpdateRequest() {}

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public int getAboutId() {
    return aboutId;
  }

  public void setAboutId(int aboutId) {
    this.aboutId = aboutId;
  }

  public String getLangCode() {
    return langCode;
  }

  public void setLangCode(String langCode) {
    this.langCode = langCode;
  }
}

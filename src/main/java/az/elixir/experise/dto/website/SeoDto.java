package az.elixir.experise.dto.website;

public class SeoDto {

  private String title;

  private String key;

  private String description;

  public SeoDto() {}

  public SeoDto(String title, String key, String description) {
    this.title = title;
    this.key = key;
    this.description = description;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}

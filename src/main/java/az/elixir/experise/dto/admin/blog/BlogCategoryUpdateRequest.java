package az.elixir.experise.dto.admin.blog;

public class BlogCategoryUpdateRequest {
  private String category;
  private int blogCategoryId;
  private String langCode;
  private int referId;

  public BlogCategoryUpdateRequest() {}

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public int getBlogCategoryId() {
    return blogCategoryId;
  }

  public void setBlogCategoryId(int blogCategoryId) {
    this.blogCategoryId = blogCategoryId;
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

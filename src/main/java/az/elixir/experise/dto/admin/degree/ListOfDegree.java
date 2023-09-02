package az.elixir.experise.dto.admin.degree;

public class ListOfDegree {

  private int id;

  private String title;

  private String category;

  private String isFooter;

  public ListOfDegree() {}

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

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getIsFooter() {
    return isFooter;
  }

  public void setIsFooter(String isFooter) {
    this.isFooter = isFooter;
  }
}

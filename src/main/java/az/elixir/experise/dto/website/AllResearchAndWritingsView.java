package az.elixir.experise.dto.website;

import az.elixir.experise.model.ResearchAndWritingsEntity;

public class AllResearchAndWritingsView {

  private int id;

  private String name;

  public AllResearchAndWritingsView() {}

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

  public void mapper(ResearchAndWritingsEntity a) {
    this.id = a.getId();
    this.name = a.getName();
  }
}

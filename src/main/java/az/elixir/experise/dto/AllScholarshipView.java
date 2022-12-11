package az.elixir.experise.dto;

import az.elixir.experise.model.ResearchAndWritingsEntity;
import az.elixir.experise.model.ScholarshipEntity;

public class AllScholarshipView {

    private int id;

    private String name;

    public AllScholarshipView() {
    }

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

    public void mapper(ScholarshipEntity a){
        this.id= a.getId();
        this.name =a.getTitle();
    }
}

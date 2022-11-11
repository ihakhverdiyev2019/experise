package az.elixir.experise.dto;

import az.elixir.experise.model.DegreesAndCredentialsEntity;
import az.elixir.experise.model.ResearchAndWritingsEntity;

public class AllResearchAndWritingsView {

    private int id;

    private String photoUrl;

    private String name;

    public AllResearchAndWritingsView() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void mapper(ResearchAndWritingsEntity a){
        this.id= a.getId();
        this.name =a.getName();
        this.photoUrl=a.getFrontPhotoUrl();
    }
}

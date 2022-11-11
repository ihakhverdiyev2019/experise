package az.elixir.experise.dto;

import az.elixir.experise.model.DegreesAndCredentialsEntity;

public class AllDegreesAndCredentialsView {

    private int id;

    private String photoUrl;

    private String name;

    private String category;

    public AllDegreesAndCredentialsView() {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void mapper(DegreesAndCredentialsEntity a){
        this.id= a.getId();
        this.name =a.getName();
        this.photoUrl=a.getFrontPhotoUrl();
        this.category=a.getCategory();
    }
}

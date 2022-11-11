package az.elixir.experise.dto;

import az.elixir.experise.model.CoursesEntity;

public class AllCoursesView {

    private int id;

    private String photoUrl;

    private String name;

    public AllCoursesView() {
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

    public void mapper(CoursesEntity a){
        this.id= a.getId();
        this.name =a.getTitle();
        this.photoUrl=a.getPhotoFront();
    }
}

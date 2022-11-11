package az.elixir.experise.dto;

import az.elixir.experise.model.CoursesEntity;
import az.elixir.experise.model.ScholarshipEntity;

public class CoursesView {

    private String photoUrl;

    private String name;

    private String text;

    public CoursesView() {
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void mapper(CoursesEntity a){
        this.name =a.getTitle();
        this.photoUrl=a.getPhoto();
        this.text = a.getText();
    }
}

package az.elixir.experise.model;

import javax.persistence.*;

@Entity
@Table(name = "scholarship")
public class ScholarshipEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String text;

    private String photoFront;

    private String photo;

    public ScholarshipEntity() {
    }

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPhotoFront() {
        return photoFront;
    }

    public void setPhotoFront(String photoFront) {
        this.photoFront = photoFront;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}

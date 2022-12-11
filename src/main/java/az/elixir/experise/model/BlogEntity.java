package az.elixir.experise.model;

import javax.persistence.*;

@Entity
@Table(name = "blog")
public class BlogEntity {

    @Id
    @GeneratedValue
    private int id;

    private String title;

    @Column(columnDefinition="text")
    private String text;

    private String photoFront;

    private String photo;

    private int blogCategory;

    private boolean switchButton;

    public BlogEntity() {
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

    public int getBlogCategory() {
        return blogCategory;
    }

    public void setBlogCategory(int blogCategory) {
        this.blogCategory = blogCategory;
    }

    public boolean isSwitchButton() {
        return switchButton;
    }

    public void setSwitchButton(boolean switchButton) {
        this.switchButton = switchButton;
    }
}

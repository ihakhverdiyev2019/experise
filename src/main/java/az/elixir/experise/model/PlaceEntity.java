package az.elixir.experise.model;

import javax.persistence.*;

@Entity
@Table(name = "places")
public class PlaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String placePhoto;

    public PlaceEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlacePhoto() {
        return placePhoto;
    }

    public void setPlacePhoto(String placePhoto) {
        this.placePhoto = placePhoto;
    }
}

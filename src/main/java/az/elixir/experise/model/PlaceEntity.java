package az.elixir.experise.model;

import javax.persistence.*;

@Entity
@Table(name = "places")
public class PlaceEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String placePhoto;

  private int langId;

  public PlaceEntity() {}

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

  public int getLangId() {
    return langId;
  }

  public void setLangId(int langId) {
    this.langId = langId;
  }
}

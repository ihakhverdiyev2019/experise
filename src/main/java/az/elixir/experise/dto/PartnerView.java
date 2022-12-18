package az.elixir.experise.dto;

import az.elixir.experise.model.PartnerEntity;

public class PartnerView {

  private String photo;

  public PartnerView() {}

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public void mapper(PartnerEntity a) {
    this.photo = a.getPhoto();
  }
}
